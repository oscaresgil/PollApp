package com.example.pollapp.question

import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController

import com.example.pollapp.R
import com.example.pollapp.database.PollDatabase
import com.example.pollapp.databinding.QuestionFragmentBinding

class QuestionFragment : Fragment() {

    companion object {
        fun newInstance() = QuestionFragment()
    }

    private lateinit var viewModelFactory: QuestionViewModelFactory
    private lateinit var viewModel: QuestionViewModel

    private lateinit var binding: QuestionFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.question_fragment,
            container,
            false
        )

        binding.fab.setOnClickListener {
            requireView().findNavController().navigate(QuestionFragmentDirections.actionQuestionFragmentToAddQuestionFragment())
        }

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.lifecycleOwner = this

        val application = requireNotNull(this.activity).application
        val dataSource = PollDatabase.getInstance(application).questionDatabaseDao
        viewModelFactory = QuestionViewModelFactory(dataSource)
        viewModel = ViewModelProvider(this, viewModelFactory).get(QuestionViewModel::class.java)

        binding.viewModel = viewModel

    }

}
