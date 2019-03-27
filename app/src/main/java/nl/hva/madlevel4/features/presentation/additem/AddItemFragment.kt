package nl.hva.madlevel4.features.presentation.additem

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_add_item.*
import nl.hva.madlevel4.R
import nl.hva.madlevel4.core.extension.observe
import nl.hva.madlevel4.core.platform.BaseFragment
import nl.hva.madlevel4.features.data.models.BucketListItem
import org.koin.androidx.viewmodel.ext.android.viewModel

class AddItemFragment : BaseFragment() {

    private val viewModel by viewModel<AddItemViewModel>()

    override fun layoutId() = R.layout.fragment_add_item

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.apply {
            observe(itemAdded) { findNavController().navigateUp() }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        buttonCreate.setOnClickListener {
            val title = textInputEditTextTitle.text.toString()
            val description = textInputEditTextDescription.text.toString()
            val bucketListItem = BucketListItem(null, title, description)
            viewModel.insertBucketListItem(bucketListItem)
        }
    }

}