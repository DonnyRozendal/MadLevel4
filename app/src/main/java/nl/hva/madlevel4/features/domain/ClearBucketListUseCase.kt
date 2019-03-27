package nl.hva.madlevel4.features.domain

import nl.hva.madlevel4.core.interactor.UseCase
import nl.hva.madlevel4.features.data.repositories.BucketListRepository

class ClearBucketListUseCase(private val bucketListRepository: BucketListRepository) :
        UseCase<Boolean, UseCase.None>() {

    override suspend fun run(params: None) = bucketListRepository.clearBucketList()

}