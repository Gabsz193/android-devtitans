package com.example.plaintext.data.repository

import com.example.plaintext.data.dao.PasswordDao
import com.example.plaintext.data.model.Password
import kotlinx.coroutines.flow.Flow

interface PasswordDBStore {
    fun getList(): Flow<List<Password>>
    suspend fun add(password: Password): Long
    suspend fun update(password: Password)
    fun get(id: Int): Password?
    suspend fun save(password: Password)
    suspend fun isEmpty(): Flow<Boolean>
}

class LocalPasswordDBStore(
    private val passwordDao : PasswordDao
): PasswordDBStore {
    override fun getList(): Flow<List<Password>> {
        return passwordDao.getAll()
    }

    override suspend fun add(password: Password): Long {
        return passwordDao.insert(password)
    }

    override suspend fun update(password: Password) {
        return passwordDao.update(password)
    }

    override fun get(id: Int): Password? {
        TODO("asdasdsa")
    }

    override suspend fun save(password: Password) {
        passwordDao.insert(password)
    }

    override suspend fun isEmpty(): Flow<Boolean> {
        TODO("Not yet implemented")
    }
}