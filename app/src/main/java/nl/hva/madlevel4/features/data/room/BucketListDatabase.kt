package nl.hva.madlevel4.features.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import nl.hva.madlevel4.features.data.models.BucketListItem

@Database(entities = [BucketListItem::class], version = 1)
abstract class BucketListDatabase : RoomDatabase() {

    abstract fun bucketListDao(): BucketListDao

}