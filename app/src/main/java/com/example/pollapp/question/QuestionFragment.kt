package com.example.pollapp.question

import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController

import com.example.pollapp.R
import com.example.pollapp.data.QuestionUser
import com.example.pollapp.databinding.QuestionFragmentBinding

class QuestionFragment : Fragment() {

    companion object {
        fun newInstance() = QuestionFragment()
    }

    private lateinit var viewModel: QuestionViewModel

    private lateinit var binding: QuestionFragmentBinding

    private lateinit var questionUser : QuestionUser

    private var questionIndex: Int = -1

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

        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.lifecycleOwner = this

        viewModel = ViewModelProvider(this).get(QuestionViewModel::class.java)

        updateVisibleQuestion()

        binding.viewModel = viewModel

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.question_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.next_question) {
            questionUser.questions[questionIndex].answer = viewModel.question.value!!.answer
            if (!updateVisibleQuestion()){
                requireView().findNavController().navigate(QuestionFragmentDirections.actionQuestionFragmentToResultsFragment())
            }
        }
        return super.onOptionsItemSelected(item)
    }

    fun updateVisibleQuestion() : Boolean {
        questionIndex ++
        if (questionUser.questions.size > questionIndex){
            viewModel.updateQuestion(questionUser.questions[questionIndex])
            (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.title_android_question, questionIndex + 1, questionUser.questions.size)
            return true
        }
        return false
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
