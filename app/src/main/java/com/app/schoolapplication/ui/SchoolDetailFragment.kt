package com.app.schoolapplication.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.app.schoolapplication.databinding.FragmentSchoolDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SchoolDetailFragment : Fragment() {

    private var _binding: FragmentSchoolDetailBinding? = null
    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!
    private val schoolArg : SchoolDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Retrieve and inflate the layout for this fragment
        _binding = FragmentSchoolDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding?.apply {
            school = schoolArg.school
        }
    }

    /**
    * Frees the binding object when the Fragment is destroyed.
    */
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}