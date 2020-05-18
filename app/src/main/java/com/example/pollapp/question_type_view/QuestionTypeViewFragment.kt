package com.example.pollapp.question_type_view

import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.example.pollapp.R
import com.example.pollapp.database.PollDatabase
import com.example.pollapp.databinding.QuestionTypeViewFragmentBinding
import com.example.pollapp.question_type.*

class QuestionTypeViewFragment : Fragment() {

    companion object {
        fun newInstance() = QuestionTypeFragment()
    }

    private lateinit var viewModelFactory: QuestionTypeViewViewModelFactory
    private lateinit var viewModel: QuestionTypeViewViewModel

    private lateinit var binding: QuestionTypeViewFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.question_type_view_fragment,
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

        val questionTypeViewFragmentArgs by navArgs<QuestionTypeViewFragmentArgs>()

        viewModelFactory = QuestionTypeViewViewModelFactory(dataSource, questionTypeViewFragmentArgs.typeId)
        viewModel = ViewModelProvider(this, viewModelFactory).get(QuestionTypeViewViewModel::class.java)

        binding.viewModel = viewModel

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.delete_question_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.delete_button) {
            viewModel.deleteQuestionType()
            activity?.onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }
}