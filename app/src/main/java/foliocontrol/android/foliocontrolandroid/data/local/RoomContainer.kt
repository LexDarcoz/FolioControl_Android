package foliocontrol.android.foliocontrolandroid.data.local

import android.content.Context
import foliocontrol.android.foliocontrolandroid.data.local.database.AccountDatabase
import foliocontrol.android.foliocontrolandroid.data.local.database.AccountDatabaseImpl
import foliocontrol.android.foliocontrolandroid.data.local.database.PartnershipDatabase
import foliocontrol.android.foliocontrolandroid.data.local.database.PartnershipDatabaseImpl
import foliocontrol.android.foliocontrolandroid.data.local.database.PropertyDatabase
import foliocontrol.android.foliocontrolandroid.data.local.database.PropertyDatabaseImpl
import kotlinx.coroutines.CoroutineScope

interface RoomContainer {
    val partnershipDatabase: PartnershipDatabase
    val propertyDatabase: PropertyDatabase
    val accountDatabase: AccountDatabase
}

class RoomContainerImpl(private val context: Context, private val scope: CoroutineScope) :
    RoomContainer { // ktlint-disable max-line-length
    override val partnershipDatabase: PartnershipDatabase by lazy {
        PartnershipDatabaseImpl(FolioRoomDatabase.getDatabase(context, scope).partnershipDao())
    }
    override val propertyDatabase: PropertyDatabase by lazy {
        PropertyDatabaseImpl(FolioRoomDatabase.getDatabase(context, scope).propertyDao())
    }
    override val accountDatabase: AccountDatabase by lazy {
        AccountDatabaseImpl(FolioRoomDatabase.getDatabase(context, scope).userDao())
    }
}
