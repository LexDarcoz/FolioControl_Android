package foliocontrol.android.foliocontrol_android

import foliocontrol.android.foliocontrolandroid.domain.Partnership
import foliocontrol.android.foliocontrolandroid.ui.viewModels.PropertyViewModel
import foliocontrol.android.foliocontrolandroid.ui.viewModels.common.UiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
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

    @OptIn(ExperimentalCoroutinesApi::class)
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

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `test viewModel getData Uistate succesful`() = runTest {
        propertyViewModel.getData()
        advanceUntilIdle()
        Assert.assertEquals(
            UiState.Success("Succesfully retrieved data"), propertyViewModel.uiState
        )
    }

    @Test
    fun `test get properties`() = runTest {
        propertyViewModel.switchPartnership(Partnership(1, "Test Partnership"))
        propertyViewModel.getPropertyListForPartnership()
        Assert.assertTrue(propertyViewModel.propertyListState.isNotEmpty())
    }

    @Test
    fun `test switchPartnership`() = runTest {
        var oldPartnership = Partnership(1, "Test Partnership")

        propertyViewModel.switchPartnership(oldPartnership)

        var newPart = Partnership(2, "Test Partnership 2")

        propertyViewModel.switchPartnership(newPart)
        Assert.assertEquals(newPart, propertyViewModel.currentPartnership)
        Assert.assertNotEquals(oldPartnership, propertyViewModel.currentPartnership)
    }

    @Test
    fun `test getPropertyList empty`() = runTest {
        propertyViewModel.getPropertyListForPartnership()
        Assert.assertTrue(propertyViewModel.propertyListState.isEmpty())
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `test handlePropertyDelete`() = runTest {
        // Assuming propertyListState is not empty before calling this method
        propertyViewModel.getPartnershipListForLoggedInUser()
        propertyViewModel.switchPartnership(propertyViewModel.partnershipListState.first())
        propertyViewModel.getPropertyListForPartnership()
        val propertyIdToDelete = propertyViewModel.propertyListState.first().propertyID
        propertyViewModel.handlePropertyDelete(propertyIdToDelete)
        advanceUntilIdle()
        Assert.assertTrue(propertyViewModel.propertyListState.none { it.propertyID == propertyIdToDelete })
        Assert.assertTrue(propertyViewModel.uiState is UiState.Success)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `test getData with empty property list`() = runTest {
        propertyViewModel.getPartnershipListForLoggedInUser()
        propertyViewModel.switchPartnership(propertyViewModel.partnershipListState.get(1))
        propertyViewModel.getPropertyListForPartnership()

        propertyViewModel.getData()
        advanceUntilIdle()

        Assert.assertTrue(propertyViewModel.uiState is UiState.Error)
        // Add more assertions based on your specific use case
    }

    @Test
    fun `test switchPartnership with the same partnership`() = runTest {
        val partnership = Partnership(1, "Test Partnership")

        propertyViewModel.switchPartnership(partnership)
        val initialPartnership = propertyViewModel.currentPartnership

        // Switch to the same partnership again
        propertyViewModel.switchPartnership(partnership)

        // Assert that the current partnership remains unchanged
        Assert.assertEquals(initialPartnership, propertyViewModel.currentPartnership)
        // Add more assertions based on your specific use case
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `test getData with Exception`() = runTest {
        propertyViewModel.getPartnershipListForLoggedInUser()
        propertyViewModel.switchPartnership(propertyViewModel.partnershipListState.get(1))

        propertyViewModel.getData()
        advanceUntilIdle()

        Assert.assertTrue(propertyViewModel.uiState is UiState.Error)
    }
}