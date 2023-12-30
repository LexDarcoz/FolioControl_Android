package foliocontrol.android.foliocontrol_android

import foliocontrol.android.foliocontrolandroid.domain.User
import foliocontrol.android.foliocontrolandroid.ui.viewModels.AccountViewModel
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

@OptIn(ExperimentalCoroutinesApi::class)
class AccountViewModelTest {
    private val testScope = StandardTestDispatcher()
    private val fakeAccountRepo = FakeAccountDatabase()
    private val fakeTokenRepo = FakeTokenRepo()
    private lateinit var accountViewModel: AccountViewModel

    @Before
    fun setUp() {
        Dispatchers.setMain(testScope)
        accountViewModel = AccountViewModel(fakeAccountRepo, fakeTokenRepo)
        accountViewModel.authService = FakeAuthServiceImpl()
    }

    @Test
    fun `test viewModel initialized`() = runTest {
        Assert.assertTrue(accountViewModel.uiState is UiState.LoggedOut)
    }

    @Test
    fun `test getData Uistate successful`() = runTest {
        accountViewModel.handleUserSave()
        accountViewModel.getData()
        advanceUntilIdle()
        Assert.assertEquals(
            UiState.Success("Loaded data successfully"), accountViewModel.uiState
        )
    }

    @Test
    fun `test getToken`() {
        val fakeToken = "fakeToken"
        fakeTokenRepo.setToken(fakeToken)
        Assert.assertEquals(fakeToken, accountViewModel.getToken())
    }

    @Test
    fun `test getUserData`() = runTest {
        val fakeUser = User(userID = 1, name = "John Doe", email = "john@example.com")
        accountViewModel.handleUserSave()
        advanceUntilIdle()
        accountViewModel.getUserData()

        Assert.assertEquals(fakeUser, accountViewModel.user)
        Assert.assertEquals(
            UiState.Success("Loaded data successfully"), accountViewModel.uiState
        )
    }

    @Test
    fun `test getUserData with Exception`() = runTest {
        accountViewModel.getUserData()
        advanceUntilIdle()
        Assert.assertEquals(
            UiState.Error("Something went very wrong: User not found"), accountViewModel.uiState
        )
    }

    @Test
    fun `test handleUserEdit`() {
        val newName = "Jane Doe"
        val newEmail = "jane@example.com"
        accountViewModel.handleUserEdit(name = newName, email = newEmail)

        val updatedUser = accountViewModel.user
        Assert.assertEquals(newName, updatedUser.name)
        Assert.assertEquals(newEmail, updatedUser.email)
    }

}
