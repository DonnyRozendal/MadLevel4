package nl.hva.madlevel4.features.presentation.additem

import androidx.lifecycle.MutableLiveData
import nl.hva.madlevel4.core.platform.BaseViewModel
import nl.hva.madlevel4.features.data.models.BucketListItem
import nl.hva.madlevel4.features.domain.InsertBucketListItemUseCase

class AddItemViewModel(private val insertBucketListItemUseCase: InsertBucketListItemUseCase) : BaseViewModel() {

    val itemAdded = MutableLiveData<Boolean>()

    fun insertBucketListItem(bucketListItem: BucketListItem) {
        insertBucketListItemUseCase(InsertBucketListItemUseCase.Params(bucketListItem)) {
            it.either(::handleFailure) { result ->
                itemAdded.setValue(result)
            }
        }
    }

}