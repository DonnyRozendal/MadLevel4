package nl.hva.madlevel4.features.domain

import nl.hva.madlevel4.core.interactor.UseCase
import nl.hva.madlevel4.features.data.models.BucketListItem
import nl.hva.madlevel4.features.data.repositories.BucketListRepository

class InsertBucketListItemUseCase(private val bucketListRepository: BucketListRepository) :
        UseCase<Boolean, InsertBucketListItemUseCase.Params>() {

    override suspend fun run(params: Params) = bucketListRepository.saveBucketListItem(params.bucketListItem)

    data class Params(val bucketListItem: BucketListItem)

}