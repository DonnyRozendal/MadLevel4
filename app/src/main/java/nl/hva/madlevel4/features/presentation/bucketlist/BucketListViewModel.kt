package nl.hva.madlevel4.features.presentation.bucketlist

import androidx.lifecycle.MutableLiveData
import nl.hva.madlevel4.core.interactor.UseCase
import nl.hva.madlevel4.core.platform.BaseViewModel
import nl.hva.madlevel4.features.data.models.BucketListItem
import nl.hva.madlevel4.features.domain.ClearBucketListUseCase
import nl.hva.madlevel4.features.domain.DeleteBucketListItemUseCase
import nl.hva.madlevel4.features.domain.GetBucketListUseCase

class BucketListViewModel(
        private val getBucketListUseCase: GetBucketListUseCase,
        private val deleteBucketListItemUseCase: DeleteBucketListItemUseCase,
        private val clearBucketListUseCase: ClearBucketListUseCase
) : BaseViewModel() {

    val bucketList = MutableLiveData<Array<BucketListItem>>()
    val itemDeleted = MutableLiveData<Boolean>()
    val listCleared = MutableLiveData<Boolean>()

    fun getBucketList() {
        getBucketListUseCase(UseCase.None()) {
            it.either(::handleFailure) { result ->
                bucketList.setValue(result)
            }
        }
    }

    fun deleteBucketListItem(bucketListItem: BucketListItem) {
        deleteBucketListItemUseCase(DeleteBucketListItemUseCase.Params(bucketListItem)) {
            it.either(::handleFailure) { result ->
                itemDeleted.setValue(result)
            }
        }
    }

    fun clearBucketList() {
        clearBucketListUseCase(UseCase.None()) {
            it.either(::handleFailure) { result ->
                listCleared.setValue(result)
            }
        }
    }

}