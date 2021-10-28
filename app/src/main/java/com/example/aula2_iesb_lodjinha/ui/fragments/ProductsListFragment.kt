package com.example.aula2_iesb_lodjinha.ui.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.aula2_iesb_lodjinha.R
import com.example.aula2_iesb_lodjinha.databinding.FragmentHomeBinding
import com.example.aula2_iesb_lodjinha.databinding.FragmentProductsListBinding
import com.example.aula2_iesb_lodjinha.ui.MainActivity
import com.example.aula2_iesb_lodjinha.ui.NavigationDelegate


class ProductsListFragment : Fragment() {

    private var _binding: FragmentProductsListBinding? = null
    private val binding: FragmentProductsListBinding get() = _binding!!

    private val args: ProductsListFragmentArgs by lazy {
        ProductsListFragmentArgs.fromBundle(requireArguments())
    }

    private var listener: NavigationDelegate? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is NavigationDelegate) {
            listener = context
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProductsListBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        listener?.setToolbarTitle(args.title)


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}