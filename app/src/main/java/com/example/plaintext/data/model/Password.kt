package com.example.plaintext.data.model

import androidx.compose.runtime.Immutable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "passwords"
)
@Immutable
data class Password(
    @PrimaryKey
    @ColumnInfo(name = "id") val id: Int,
    val name: String,
    val login: String,
    val password: String,
    val notes: String? = null,
)