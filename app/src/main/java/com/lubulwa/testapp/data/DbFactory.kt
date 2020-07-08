package com.lubulwa.testapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.lubulwa.testapp.data.dao.ExpensesDao
import com.lubulwa.testapp.model.Expense

@Database(entities = [Expense::class], version = 1)
abstract class DbFactory : RoomDatabase() {

    abstract fun getExpensesDao(): ExpensesDao

    companion object {

        @Volatile
        private var DbINSTANCE: DbFactory? = null

        fun getInstance(context: Context): DbFactory {

            val tempInstance = DbINSTANCE
            if (tempInstance != null) {
                return tempInstance
            }

            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DbFactory::class.java,
                    "FarmerDB"
                )
                    .build()

                DbINSTANCE = instance
                return instance
            }
        }
    }

}