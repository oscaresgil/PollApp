package com.example.pollapp.question_type

import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController

import com.example.pollapp.R
import com.example.pollapp.database.PollDatabase
import com.example.pollapp.databinding.QuestionTypeFragmentBinding

class QuestionTypeFragment : Fragment() {

    companion object {
        fun newInstance() = QuestionTypeFragment()
    }

    private lateinit var viewModelFactory: QuestionTypeViewModelFactory
    private lateinit var viewModel: QuestionTypeViewModel

    private lateinit var binding: QuestionTypeFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.question_type_fragment,
            container,
            false
        )

        binding.fab.setOnClickListener {
            requireView().findNavController().navigate(QuestionTypeFragmentDirections.actionQuestionTypeFragmentToAddQuestionTypeFragment())
        }

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.lifecycleOwner = this

        val application = requireNotNull(this.activity).application
        val dataSource = PollDatabase.getInstance(application).questionTypeDatabaseDao
        viewModelFactory = QuestionTypeViewModelFactory(dataSource)
        viewModel = ViewModelProvider(this, viewModelFactory).get(QuestionTypeViewModel::class.java)

        binding.viewModel = viewModel

    }

}
