package io.threethirtythree.ricknmorty.database

import android.icu.text.IDNA.Info
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

/** Our Db model for Result data */

/** The root object of the character response. */
data class CharacterResponse(
    @SerializedName("info")
    @Ignore
    val info: Info,

    @SerializedName("results")
    val character: List<Character>
)

/** The part of the response we'll use to store in the database.*/
@Entity(tableName = "char_table")
data class Character(
    @PrimaryKey
    @SerializedName("id")
    val id: Long,

    @SerializedName("name")
    val name: String,

    @SerializedName("species")
    val species: String,

    @SerializedName("image")
    val image: String,

    @SerializedName("gender")
    val gender: String
)