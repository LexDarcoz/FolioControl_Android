import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import foliocontrol.android.foliocontrolandroid.data.local.FolioRoomDatabase
import foliocontrol.android.foliocontrolandroid.data.local.dao.PartnershipDao
import foliocontrol.android.foliocontrolandroid.data.local.dao.PropertyDao
import foliocontrol.android.foliocontrolandroid.data.local.dao.UserDao
import foliocontrol.android.foliocontrolandroid.data.local.schema.PartnershipRoomEntity
import foliocontrol.android.foliocontrolandroid.data.local.schema.PropertyRoomEntity
import foliocontrol.android.foliocontrolandroid.domain.Partnership
import foliocontrol.android.foliocontrolandroid.domain.Property
import foliocontrol.android.foliocontrolandroid.domain.User
import foliocontrol.android.foliocontrolandroid.domain.asPartnershipRoomEntity
import foliocontrol.android.foliocontrolandroid.domain.asPropertyRoomEntity
import foliocontrol.android.foliocontrolandroid.domain.asUserRoomEntity
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test


class FolioRoomDatabaseTest {

    private lateinit var database: FolioRoomDatabase
    private lateinit var partnershipDao: PartnershipDao
    private lateinit var propertyDao: PropertyDao
    private lateinit var userDao: UserDao

    @Before
    fun setUp() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(), FolioRoomDatabase::class.java
        ).build()

        partnershipDao = database.partnershipDao()
        propertyDao = database.propertyDao()
        userDao = database.userDao()
    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun insertAndReadPartnership() = runBlocking {
        val partnership = Partnership(name = "Test Partnership")

        var partnerships = listOf<PartnershipRoomEntity>()
        partnerships += partnership.asPartnershipRoomEntity()
        partnershipDao.insertAllPartnerships(partnerships)

        val retrievedPartnership = partnershipDao.getPartnerships()

        Assert.assertNotNull(retrievedPartnership)
        Assert.assertEquals("Test Partnership", retrievedPartnership.first().get(0).name)
    }

    @Test
    fun insertAndReadProperty() = runBlocking {
        val property = Property(propertyName = "Test Property")
        var properties = listOf<PropertyRoomEntity>()
        properties += property.asPropertyRoomEntity()

        propertyDao.insertAll(properties)

        val retrievedProperty = propertyDao.getAllProperties()

        Assert.assertNotNull(retrievedProperty)
        Assert.assertEquals("Test Property", retrievedProperty.first().get(0).propertyName)
    }

    @Test
    fun insertAndReadUser() = runBlocking {
        val user = User(firstName = "John", lastName = "Doe")

        userDao.insert(user.asUserRoomEntity())

        val retrievedUser = userDao.getUser()

        Assert.assertNotNull(retrievedUser)
        Assert.assertEquals("John", retrievedUser.first().firstName)
        Assert.assertEquals("Doe", retrievedUser.first().lastName)
    }
}
