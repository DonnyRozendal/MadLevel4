package nl.hva.madlevel4.features.data.repositories

import nl.hva.madlevel4.core.exception.Failure
import nl.hva.madlevel4.core.functional.Either
import nl.hva.madlevel4.features.data.models.BucketListItem
import nl.hva.madlevel4.features.data.room.BucketListDao

interface BucketListRepository {

    fun saveBucketListItem(bucketListItem: BucketListItem): Either<Failure, Boolean>

    fun getAllBucketListItems(): Either<Failure, Array<BucketListItem>>

    fun deleteBucketListItem(bucketListItem: BucketListItem): Either<Failure, Boolean>

    fun clearBucketList(): Either<Failure, Boolean>

    class Network(private val bucketListDao: BucketListDao) : BucketListRepository {

        override fun saveBucketListItem(bucketListItem: BucketListItem): Either<Failure, Boolean> {
            bucketListDao.insert(bucketListItem)
            return Either.Right(true)
        }

        override fun getAllBucketListItems(): Either<Failure, Array<BucketListItem>> {
            return Either.Right(bucketListDao.getAll() ?: emptyArray())
        }

        override fun deleteBucketListItem(bucketListItem: BucketListItem): Either<Failure, Boolean> {
            val amountDeletedItems = bucketListDao.delete(bucketListItem)
            return if (amountDeletedItems == 1) {
                Either.Right(true)
            } else {
                Either.Left(Failure.ItemNotFoundError())
            }
        }

        override fun clearBucketList(): Either<Failure, Boolean> {
            bucketListDao.clearTable()
            return Either.Right(true)
        }

    }

}