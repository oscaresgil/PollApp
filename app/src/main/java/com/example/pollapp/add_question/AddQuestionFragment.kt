package com.example.pollapp.add_question

import android.os.Bundle
import android.view.*
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

import com.example.pollapp.R
import com.example.pollapp.database.PollDatabase
import com.example.pollapp.database.QuestionType
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
        val dataSource = PollDatabase.getInstance(application).questionDatabaseDao
        val dataSourceType = PollDatabase.getInstance(application).questionTypeDatabaseDao
        viewModelFactory = AddQuestionViewModelFactory(dataSource, dataSourceType)
        viewModel = ViewModelProvider(this, viewModelFactory).get(AddQuestionViewModel::class.java)

        binding.viewModel = viewModel

        val items = arrayListOf<QuestionType>()
        val arrayAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, items)
        binding.typesList.adapter = arrayAdapter

        viewModel.typesList.observe(viewLifecycleOwner, Observer {
            items.clear()
            items.addAll(it)
            if (it.isNotEmpty()) binding.typesList.setSelection(0)
            arrayAdapter.notifyDataSetChanged()
        })

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.save_question_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.save_button) {
            viewModel.insertQuestion(binding.typesList.selectedItem)
            activity?.onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

}
