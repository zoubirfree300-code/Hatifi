package com.example.data

import com.example.model.Phone
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

sealed interface SyncStatus {
    object Idle : SyncStatus
    object Syncing : SyncStatus
    data class Success(val timestamp: Long, val count: Int, val isRemote: Boolean) : SyncStatus
    data class Fallback(val message: String, val count: Int, val timestamp: Long) : SyncStatus
}

/**
 * Clean swappable repository interface for phone data, backed by Room persistence.
 */
interface PhoneRepository {
    val phonesFlow: StateFlow<List<Phone>>
    val favoritesFlow: Flow<List<Phone>>
    val syncStatusFlow: StateFlow<SyncStatus>

    suspend fun refresh(force: Boolean = false)
    suspend fun toggleFavorite(phoneId: String)
    suspend fun loadMorePage(page: Int): Int
    fun getApiUrl(): String
    fun setApiUrl(url: String)
    fun getLastSyncTimestamp(): Long
}
