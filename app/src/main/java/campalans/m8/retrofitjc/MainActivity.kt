package campalans.m8.retrofitjc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import campalans.m8.retrofitjc.ui.theme.MainView
import campalans.m8.retrofitjc.ui.theme.RetrofitJCTheme

// Activity principal de l'aplicació
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            RetrofitJCTheme {
                // Creem el ViewModel
                val userViewModel: UserViewModel = viewModel()
                
                // Observem els estados reactius
                val users by userViewModel.users.collectAsState()
                val selectedUser by userViewModel.selectedUser.collectAsState()
                val isLoading by userViewModel.isLoading.collectAsState()
                val errorMessage by userViewModel.errorMessage.collectAsState()

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    // Passem els dades i callbacks a la vista
                    MainView(
                        modifier = Modifier.padding(innerPadding),
                        users = users,
                        selectedUser = selectedUser,
                        isLoading = isLoading,
                        errorMessage = errorMessage,
                        onAddUser = {
                            userViewModel.createUser("Nou Post", "Contingut del post", "email@example.com")
                        },
                        onDeleteUser = { id ->
                            userViewModel.deleteUser(id)
                        },
                        onSelectUser = { user ->
                            userViewModel.getUser(user.id.toString())
                        },
                        onUpdateUser = { id, firstName, lastName, email ->
                            userViewModel.updateUser(id, firstName, lastName, email)
                        },
                        onClearError = {
                            userViewModel.clearError()
                        },
                        onCloseUserDetail = {
                            userViewModel.clearSelectedUser()
                        }
                    )
                }
            }
        }
    }
}