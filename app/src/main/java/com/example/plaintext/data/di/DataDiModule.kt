package com.example.plaintext.data.di

import android.content.Context
import androidx.room.Room
import com.example.plaintext.data.PlainTextDatabase
import com.example.plaintext.data.dao.PasswordDao
import com.example.plaintext.data.repository.LocalPasswordDBStore
import com.example.plaintext.data.repository.PasswordDBStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataDiModule {
    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): PlainTextDatabase =
        Room.databaseBuilder(context, PlainTextDatabase::class.java, "plaintext.db")
            .build()

    @Provides
    @Singleton
    fun providePasswordDao(
        database: PlainTextDatabase
    ): PasswordDao = database.passwordDao()


    @Provides
    @Singleton
    fun providePasswordDBStore(
        passwordDao: PasswordDao
    ): PasswordDBStore = LocalPasswordDBStore(passwordDao)
}