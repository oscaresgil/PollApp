package com.example.pollapp.poll

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController

import com.example.pollapp.R
import com.example.pollapp.database.PollDatabase
import com.example.pollapp.databinding.PollFragmentBinding

class PollFragment : Fragment() {

    companion object {
        fun newInstance() = PollFragment()
    }

    private lateinit var viewModelFactory: PollViewModelFactory
    private lateinit var viewModel: PollViewModel

    private lateinit var binding: PollFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.poll_fragment,
            container,
            false
        )

        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.lifecycleOwner = this

        val application = requireNotNull(this.activity).application
        val dataSource = PollDatabase.getInstance(application).pollDatabaseDao
        viewModelFactory = PollViewModelFactory(dataSource)
        viewModel = ViewModelProvider(this, viewModelFactory).get(PollViewModel::class.java)


        binding.viewModel = viewModel

        viewModel.registerComplete.observe(viewLifecycleOwner, Observer {
            if (it) {
                requireView().findNavController().navigate(PollFragmentDirections.actionQuestionFragmentToResultsFragment())
                viewModel.finishRegister()
            }
        })

        viewModel.questions.observe(viewLifecycleOwner, Observer {
            viewModel.initialize(it)
            (activity as AppCompatActivity).supportActionBar?.title =
                getString(R.string.title_android_question, viewModel.questionCount, viewModel.totalCount)
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.question_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.next_question) {
            viewModel.updateCurrentQuestion()
        }
        return super.onOptionsItemSelected(item)
    }

}
