package nl.hva.madlevel4.features.data.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import nl.hva.madlevel4.features.data.models.BucketListItem

@Dao
interface BucketListDao {

    @Insert
    fun insert(bucketListItem: BucketListItem)

    @Query("SELECT * FROM bucketListItemTable")
    fun getAll(): Array<BucketListItem>?

    @Delete
    fun delete(bucketListItem: BucketListItem): Int

    @Query("DELETE FROM bucketListItemTable")
    fun clearTable()

}