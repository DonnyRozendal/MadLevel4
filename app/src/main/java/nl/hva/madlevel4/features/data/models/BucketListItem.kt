package nl.hva.madlevel4.features.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bucketListItemTable")
data class BucketListItem(
    @PrimaryKey(autoGenerate = true) val id: Int?,
    val title: String,
    val description: String
)