package foliocontrol.android.foliocontrolandroid.data.local

import android.content.Context
import foliocontrol.android.foliocontrolandroid.data.local.database.AccountDatabase
import foliocontrol.android.foliocontrolandroid.data.local.database.AccountDatabaseImpl
import foliocontrol.android.foliocontrolandroid.data.local.database.PartnershipDatabase
import foliocontrol.android.foliocontrolandroid.data.local.database.PartnershipDatabaseImpl
import foliocontrol.android.foliocontrolandroid.data.local.database.PropertyDatabase
import foliocontrol.android.foliocontrolandroid.data.local.database.PropertyDatabaseImpl
import kotlinx.coroutines.CoroutineScope

/**
 * Interface representing a container for Room databases in the FolioControlAndroid app.
 * It provides access to the [PartnershipDatabase], [PropertyDatabase], and [AccountDatabase].
 */
interface RoomContainer {
    /** Instance of [PartnershipDatabase] for managing partnership-related data. */
    val partnershipDatabase: PartnershipDatabase

    /** Instance of [PropertyDatabase] for managing property-related data. */
    val propertyDatabase: PropertyDatabase

    /** Instance of [AccountDatabase] for managing user account-related data. */
    val accountDatabase: AccountDatabase
}

/**
 * Implementation of [RoomContainer] that initializes Room database instances for partnerships,
 * properties, and user accounts.
 *
 * @param context The application context.
 * @param scope The CoroutineScope to manage database-related coroutines.
 */
@Suppress("ktlint:standard:max-line-length")
class RoomContainerImpl(private val context: Context, private val scope: CoroutineScope) :
    RoomContainer {
    /** Lazily initialized [PartnershipDatabase] instance. */
    override val partnershipDatabase: PartnershipDatabase by lazy {
        PartnershipDatabaseImpl(FolioRoomDatabase.getDatabase(context, scope).partnershipDao())
    }

    /** Lazily initialized [PropertyDatabase] instance. */
    override val propertyDatabase: PropertyDatabase by lazy {
        PropertyDatabaseImpl(FolioRoomDatabase.getDatabase(context, scope).propertyDao())
    }

    /** Lazily initialized [AccountDatabase] instance. */
    override val accountDatabase: AccountDatabase by lazy {
        AccountDatabaseImpl(FolioRoomDatabase.getDatabase(context, scope).userDao())
    }
}
