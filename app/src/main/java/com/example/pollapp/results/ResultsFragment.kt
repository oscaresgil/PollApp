package com.example.pollapp.results

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController

import com.example.pollapp.R
import com.example.pollapp.data.QuestionUser
import com.example.pollapp.databinding.ResultsFragmentBinding

class ResultsFragment : Fragment() {

    companion object {
        fun newInstance() = ResultsFragment()
    }

    private lateinit var viewModelFactory: ResultsViewModelFactory
    private lateinit var viewModel: ResultsViewModel

    private lateinit var binding: ResultsFragmentBinding

    private lateinit var questionUser: QuestionUser

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.results_fragment,
            container,
            false
        )

        binding.resultsButton.setOnClickListener {
            Toast.makeText(this.context, viewModel.resultsText.value, Toast.LENGTH_SHORT).show()
        }

        binding.restartButton.setOnClickListener {
            questionUser.initialize()
            requireView().findNavController().navigate(ResultsFragmentDirections.actionResultsFragmentToQuestionFragment())
        }

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModelFactory = ResultsViewModelFactory(questionUser.questions)
        viewModel = ViewModelProvider(this, viewModelFactory).get(ResultsViewModel::class.java)

        binding.viewModel = viewModel

        binding.lifecycleOwner = this
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            questionUser = context as QuestionUser
        } catch (castException: ClassCastException) {
            /** The activity does not implement the listener.  */
        }
    }

}
