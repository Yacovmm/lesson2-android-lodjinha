package com.example.aula2_iesb_lodjinha.ui.components.filterbottomsheet

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.aula2_iesb_lodjinha.databinding.BottomSheetFilterBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import java.io.Serializable


class FilterBottomSheetDialog : BottomSheetDialogFragment() {

	private var _binding: BottomSheetFilterBinding? = null
	private val binding: BottomSheetFilterBinding get() = _binding!!

	private var list: ArrayList<String> = arrayListOf()
	private var onSelectionFinished: ((String, Int) -> Unit)? = null

	// TODO CREATE ADAPTER
	// private lateinit var adapter: FilterAdapter

	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {
		_binding = BottomSheetFilterBinding.inflate(inflater, container, false)
		return binding.root
	}

	override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
		arguments?.let {
			list = ArrayList(it.getStringArrayList(ITEMS_KEY))
			onSelectionFinished = it.getSerializable(ON_SELECTION_FINISHED_KEY) as? ((String, Int) -> Unit)?
		}

		return super.onCreateDialog(savedInstanceState)
	}


	override fun onStart() {
		super.onStart()

		val dialog = requireView().parent as View
		dialog.layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT

		val behavior = BottomSheetBehavior.from(dialog)
		behavior.state = BottomSheetBehavior.STATE_SETTLING
		behavior.isDraggable = true


		// TODO: Setar o adapter
	}

	companion object {
		private const val ITEMS_KEY = "Items"
		private const val ON_SELECTION_FINISHED_KEY = "OnSelectionFinished"

		fun Fragment.openBottomSheetDialog(
			items: ArrayList<String>,
			onSelectionFinished: (String, Int) -> Unit
		) {
			val bundle = Bundle().apply {
				putStringArrayList(ITEMS_KEY, items)
				putSerializable(ON_SELECTION_FINISHED_KEY, onSelectionFinished as Serializable)
			}

			val bottomSheetFragment = FilterBottomSheetDialog()
			bottomSheetFragment.arguments = bundle
			bottomSheetFragment.show(parentFragmentManager, "BOTTOMSHHET")
		}
	}
}
