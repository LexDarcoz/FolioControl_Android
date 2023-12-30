package foliocontrol.android.foliocontrol_android

import foliocontrol.android.foliocontrolandroid.ui.viewModels.PropertyViewModel
import foliocontrol.android.foliocontrolandroid.ui.viewModels.common.UiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class PropertyViewModelTest {
    private val testScope = StandardTestDispatcher()
    private val fakePropertyRepo = FakePropertyDatabase()
    private val fakePartnershipRepo = FakePartnershipDatabase()
    private val fakeDownloader = FakeDownloader()
    private val fakeTokenRepo = FakeTokenRepo()
    private lateinit var propertyViewModel: PropertyViewModel

    @Before
    fun setUp() {
        Dispatchers.setMain(testScope)
        propertyViewModel =
            PropertyViewModel(fakePropertyRepo, fakePartnershipRepo, fakeTokenRepo, fakeDownloader)
        propertyViewModel.authService = FakeAuthServiceImpl()
        propertyViewModel.propertyService = FakePropertyServiceImpl()

    }

    @Test
    fun `test viewModel initialized`() = runTest {
        Assert.assertTrue(propertyViewModel.uiState is UiState.LoggedOut)
    }

    @Test
    fun `test viewModel getData`() = runTest {
        propertyViewModel.getData()
    }

//    @Test
//    fun `test switchPartnership`() = runTest {
//        var oldPartnership = Partnership(1, "Test Partnership")
//
//        propertyViewModel.switchPartnership(oldPartnership)
//
//
//        var newPart = Partnership(2, "Test Partnership 2")
//
//        propertyViewModel.switchPartnership(newPart)
//        Assert.assertEquals(newPart, propertyViewModel.currentPartnership)
//        Assert.assertNotEquals(oldPartnership, propertyViewModel.currentPartnership)
//    }
//
//    @Test
//    fun `test getPropertyList filled`() = runTest {
//        propertyViewModel.setPropertyListState(
//            listOf(
//                Property(1, "Test Property"),
//                Property(2, "Test Property 2"),
//                Property(3, "Test Property 3")
//            )
//        )
//        Assert.assertTrue(propertyViewModel.propertyListState.isNotEmpty())
//    }
//
//
//    @Test
//    fun `test getPropertyList empty`() = runTest {
//        propertyViewModel.getPropertyListForPartnership()
//        propertyViewModel.setPropertyListState(listOf())
//        Assert.assertTrue(propertyViewModel.propertyListState.isEmpty())
//    }
//
//    @Test
//    fun `test handlePropertyDelete`() = runTest {
//        propertyViewModel.getPartnershipListForLoggedInUser()
//        propertyViewModel.getPropertyListForPartnership()
//        val propertyIdToDelete = propertyViewModel.propertyListState.first().propertyID
//        propertyViewModel.handlePropertyDelete(propertyIdToDelete)
//        Assert.assertTrue(propertyViewModel.propertyListState.none { it.propertyID == propertyIdToDelete })
//        Assert.assertTrue(propertyViewModel.uiState is UiState.Success)
//    }


}