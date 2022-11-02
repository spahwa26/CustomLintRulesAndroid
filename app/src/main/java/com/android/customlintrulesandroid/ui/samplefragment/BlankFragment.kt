package com.android.customlintrulesandroid.ui.samplefragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.android.customlintrulesandroid.databinding.FragmentBlankBinding

class BlankFragment : Fragment() {

    private var _binding: FragmentBlankBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentBlankBinding.inflate(inflater, container, false)

        //TODO: remove this button and listener if you are NOT USING ~~~~~~~ LOCAL DATABASE ~~~~~~~
        binding.btnCallCategories.setOnClickListener {
            findNavController().navigate(BlankFragmentDirections.actionCategoryGridFragment())
        }

        //TODO: remove this button and listener if you are USING ~~~~~~~ LOCAL DATABASE ~~~~~~~
        binding.btnCallCategoriesRemote.setOnClickListener {
            findNavController().navigate(BlankFragmentDirections.actionCategoryGridFragmentRemote())
        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
