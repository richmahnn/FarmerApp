package com.lubulwa.testapp.data

import androidx.annotation.MainThread
import androidx.annotation.WorkerThread
import com.lubulwa.testapp.utils.State
import kotlinx.coroutines.flow.*
import java.lang.Exception

/**
 * A repository which provides resource from local database.
 *
 * [RESULT] represents the type for database.
 * [REQUEST] represents the type for database.
 */
abstract class LocalBoundRepository<RESULT, REQUEST> {

    fun asFlow() = flow<State<RESULT>> {

        // Emit Loading State
        emit(State.loading())

        try {

            // Emit Database content
            if (fetchFromLocal() != null) {
                val result = fetchFromLocal()!!.first()
                emit(State.success(result))
            } else {
                // Save data into the persistence storage
                val data = dataToSave()
                emit(State.success(saveData(data!!)!!))
            }

        } catch (e: Exception) {
            // Exception occurred! Emit error
            emit(State.error("Database Error: $e"))
            e.printStackTrace()
        }

    }

    /**
     * Saves into the persistence storage.
     */
    @WorkerThread
    protected abstract suspend fun saveData(data: REQUEST): RESULT?

    /**
     * Retrieves all data from persistence storage.
     */
    @MainThread
    protected abstract fun fetchFromLocal(): Flow<RESULT>?

    @MainThread
    protected abstract suspend fun dataToSave(): REQUEST?

}