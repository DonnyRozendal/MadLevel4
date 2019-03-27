package nl.hva.madlevel4.features.presentation.bucketlist

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_bucket_list.*
import nl.hva.madlevel4.R
import nl.hva.madlevel4.core.extension.observe
import nl.hva.madlevel4.core.platform.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class BucketListFragment : BaseFragment() {

    private val viewModel by viewModel<BucketListViewModel>()
    private val adapter = BucketListAdapter { viewModel.deleteBucketListItem(it) }

    override fun layoutId() = R.layout.fragment_bucket_list

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.apply {
            observe(bucketList) { adapter.submitList(it.toList()) }
            observe(itemDeleted) { if (it) viewModel.getBucketList() }
            observe(listCleared) { if (it) viewModel.getBucketList() }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter
        recyclerView.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        viewModel.getBucketList()

        activity?.iconDelete?.setOnClickListener {
            viewModel.clearBucketList()
        }
        buttonAdd.setOnClickListener {
            findNavController().navigate(R.id.action_bucketListFragment_to_addItemFragment)
        }
    }

}