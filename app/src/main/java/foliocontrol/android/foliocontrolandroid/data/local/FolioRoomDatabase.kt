package foliocontrol.android.foliocontrolandroid.data.local

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import foliocontrol.android.foliocontrolandroid.data.local.dao.PartnershipDao
import foliocontrol.android.foliocontrolandroid.data.local.dao.PropertyDao
import foliocontrol.android.foliocontrolandroid.data.local.dao.UserDao
import foliocontrol.android.foliocontrolandroid.data.local.schema.PartnershipRoomEntity
import foliocontrol.android.foliocontrolandroid.data.local.schema.PropertyRoomEntity
import foliocontrol.android.foliocontrolandroid.data.local.schema.UserRoomEntity
import kotlinx.coroutines.CoroutineScope

/**
 * RoomDatabase class for the Folio application, representing the local database.
 *
 * @property partnershipDao DAO for Partnership entities.
 * @property propertyDao DAO for Property entities.
 * @property userDao DAO for User entities.
 */
@Database(
    entities = [PartnershipRoomEntity::class, PropertyRoomEntity::class, UserRoomEntity::class],
    // change this to version+1 when you change the schema
    version = 4,
    exportSchema = false,
)
abstract class FolioRoomDatabase : RoomDatabase() {
    abstract fun partnershipDao(): PartnershipDao

    abstract fun propertyDao(): PropertyDao

    abstract fun userDao(): UserDao

    companion object {
        @Volatile
        private var Instance: FolioRoomDatabase? = null

        /**
         * Retrieves an instance of the FolioRoomDatabase.
         *
         * @param context The application context.
         * @param scope The CoroutineScope.
         * @return An instance of the FolioRoomDatabase.
         */
        fun getDatabase(
            context: Context,
            scope: CoroutineScope,
        ): FolioRoomDatabase {
            Log.d("FolioRoomDB", "Creating database")
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(
                    context, FolioRoomDatabase::class.java, "Folio_database",
                ).addCallback(object : Callback() {}).fallbackToDestructiveMigration().build()
                    .also { Instance = it }
            }
        }
    }
}
