package nl.hva.madlevel4.features.domain

import nl.hva.madlevel4.core.interactor.UseCase
import nl.hva.madlevel4.features.data.models.BucketListItem
import nl.hva.madlevel4.features.data.repositories.BucketListRepository

class GetBucketListUseCase(private val bucketListRepository: BucketListRepository) :
        UseCase<Array<BucketListItem>, UseCase.None>() {

    override suspend fun run(params: None) = bucketListRepository.getAllBucketListItems()

}