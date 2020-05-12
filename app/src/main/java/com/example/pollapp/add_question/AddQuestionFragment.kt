package com.example.pollapp.question

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider

import com.example.pollapp.R
import com.example.pollapp.add_question.AddQuestionViewModelFactory
import com.example.pollapp.database.PollDatabase
import com.example.pollapp.databinding.AddQuestionFragmentBinding

class AddQuestionFragment : Fragment() {

    companion object {
        fun newInstance() = AddQuestionFragment()
    }

    private lateinit var viewModelFactory: AddQuestionViewModelFactory
    private lateinit var viewModel: AddQuestionViewModel

    private lateinit var binding: AddQuestionFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.add_question_fragment,
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
        viewModelFactory = AddQuestionViewModelFactory(dataSource)
        viewModel = ViewModelProvider(this, viewModelFactory).get(AddQuestionViewModel::class.java)

        binding.viewModel = viewModel

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.save_question_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.save_button) {
            viewModel.insertQuestion()
            activity?.onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

}
