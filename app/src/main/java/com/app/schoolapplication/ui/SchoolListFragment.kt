package com.app.schoolapplication.ui

import android.os.Bundle
import android.view.*
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.app.schoolapplication.R
import com.app.schoolapplication.databinding.FragmentSchoolListBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collectLatest

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class SchoolListFragment: Fragment() {

    private val schoolViewModel by viewModels<SchoolViewModel>()

    private lateinit var viewDataBinding: FragmentSchoolListBinding
    private lateinit var schoolListItemAdapter: SchoolListItemAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        viewDataBinding = FragmentSchoolListBinding.inflate(inflater)
        viewDataBinding.apply {
            viewModel = schoolViewModel
        }
        return viewDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Set the lifecycle owner to the lifecycle of the view
        viewDataBinding.lifecycleOwner = this.viewLifecycleOwner
        setupListAdapter()
        setupContextMenu()

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                schoolViewModel.viewState.collect { uiState ->
                    if (uiState.schools.isNotEmpty()) {
                        viewDataBinding.apply {
                            stocksList.visibility = View.VISIBLE
                            progressBar.visibility = View.GONE
                            retryButton.visibility = View.GONE
                            emptyListText.visibility = View.GONE
                        }
                        schoolListItemAdapter.submitList(uiState.schools)
                    } else if (uiState.isLoading) {
                        viewDataBinding.apply {
                            progressBar.visibility = View.VISIBLE
                            retryButton.visibility = View.GONE
                            emptyListText.visibility = View.GONE
                        }
                    } else {
                        viewDataBinding.apply {
                            progressBar.visibility = View.GONE
                            stocksList.visibility = View.GONE
                            retryButton.visibility = View.VISIBLE
                            retryButton.setOnClickListener { schoolViewModel.getSchoolsList(true) }
                            emptyListText.text = uiState.errorMessage
                            emptyListText.visibility = View.VISIBLE
                        }
                    }
                }
            }
        }
    }


    private fun setupListAdapter() {
        schoolListItemAdapter = SchoolListItemAdapter(
            SchoolClickListener { school ->
                val action =
                    SchoolListFragmentDirections.actionSchoolsListFragmentToSchoolDetailFragment(
                        school)
                findNavController().navigate(action)
            }
        )
        viewDataBinding.stocksList.adapter = schoolListItemAdapter
    }

    private fun setupContextMenu() {
        (requireActivity() as MenuHost).addMenuProvider(object : MenuProvider {

            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.school_sort_menu, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return when (menuItem.itemId) {
                    R.id.sort_name_ascending -> {
                        schoolViewModel.sortByNameAscending()
                        true
                    }
                    R.id.sort_name_descending -> {
                        schoolViewModel.sortByNameDescending()
                        true
                    }
                    else -> {
                        false
                    }
                }
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }
}