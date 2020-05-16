package com.example.pollapp.add_question_type

import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider

import com.example.pollapp.R
import com.example.pollapp.database.PollDatabase
import com.example.pollapp.databinding.AddQuestionTypeFragmentBinding

class AddQuestionTypeFragment : Fragment() {

    companion object {
        fun newInstance() = AddQuestionTypeFragment()
    }

    private lateinit var viewModelFactory: AddQuestionTypeViewModelFactory
    private lateinit var viewModel: AddQuestionTypeViewModel

    private lateinit var binding: AddQuestionTypeFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.add_question_type_fragment,
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
        val dataSource = PollDatabase.getInstance(application).questionTypeDatabaseDao
        viewModelFactory = AddQuestionTypeViewModelFactory(dataSource)
        viewModel = ViewModelProvider(this, viewModelFactory).get(AddQuestionTypeViewModel::class.java)

        binding.viewModel = viewModel

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.save_question_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.save_button) {
            viewModel.insertQuestionType()
            activity?.onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

}
