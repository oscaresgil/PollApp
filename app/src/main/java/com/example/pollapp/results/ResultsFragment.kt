package com.example.pollapp.results

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController

import com.example.pollapp.R
import com.example.pollapp.database.PollDatabase
import com.example.pollapp.databinding.ResultsFragmentBinding

class ResultsFragment : Fragment() {

    companion object {
        fun newInstance() = ResultsFragment()
    }

    private lateinit var viewModelFactory: ResultsViewModelFactory
    private lateinit var viewModel: ResultsViewModel

    private lateinit var binding: ResultsFragmentBinding

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

        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.lifecycleOwner = this

        val application = requireNotNull(this.activity).application
        val dataSource = PollDatabase.getInstance(application).questionDatabaseDao
        viewModelFactory = ResultsViewModelFactory(dataSource)
        viewModel = ViewModelProvider(this, viewModelFactory).get(ResultsViewModel::class.java)

        binding.viewModel = viewModel

        viewModel.restartRegister.observe(viewLifecycleOwner, Observer { isRestarted ->
            if (isRestarted) {
                requireView().findNavController().navigate(ResultsFragmentDirections.actionResultsFragmentToQuestionFragment())
                viewModel.restartComplete()
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.share_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.share_poll) {
//            Share intent
        }
        return super.onOptionsItemSelected(item)
    }

}
