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
import kotlin.jvm.Volatile
import kotlinx.coroutines.CoroutineScope

@Database(
    entities = [PartnershipRoomEntity::class, PropertyRoomEntity::class, UserRoomEntity::class],
    // change this to version+1 when you change the schema
    version = 4, exportSchema = false
)
abstract class FolioRoomDatabase : RoomDatabase() {
    abstract fun partnershipDao(): PartnershipDao
    abstract fun propertyDao(): PropertyDao
    abstract fun userDao(): UserDao

    companion object {
        @Volatile
        private var Instance: FolioRoomDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): FolioRoomDatabase {
            Log.d("FolioRoomDB", "Creating database")
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(
                    context, FolioRoomDatabase::class.java, "Folio_database"
                ).addCallback(object : Callback() {}).fallbackToDestructiveMigration().build()
                    .also { Instance = it }
            }
        }
    }
}
