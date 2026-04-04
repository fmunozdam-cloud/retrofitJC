package campalans.m8.retrofitjc

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.lifecycle.lifecycleScope
import campalans.m8.retrofitjc.model.User
import campalans.m8.retrofitjc.network.UserService
import campalans.m8.retrofitjc.ui.theme.MainView
import campalans.m8.retrofitjc.ui.theme.RetrofitJCTheme
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : ComponentActivity() {

    private val retrofit = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val service = retrofit.create(UserService::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            RetrofitJCTheme {

                var users by remember { mutableStateOf(listOf<User>()) }

                LaunchedEffect(Unit) {
                    try {
                        users = getUsers()
                        val singleUser = getUser("1")
                        singleUser.let { Log.d("USER_DETAIL", it.toString()) }
                    } catch (e: retrofit2.HttpException) {
                        Log.e("API_ERROR", "HTTP error ${e.code()} ${e.message()}")
                    } catch (e: Exception) {
                        Log.e("API_ERROR", "Other error: $e")
                    }
                }

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                    MainView(
                        modifier = Modifier.padding(innerPadding),
                        users = users,

                        onAddUser = {
                            lifecycleScope.launch {
                                try {
                                    createUser()
                                    users = getUsers()
                                } catch (e: retrofit2.HttpException) {
                                    Log.e("API_ERROR", "HTTP error ${e.code()} ${e.message()}")
                                } catch (e: Exception) {
                                    Log.e("API_ERROR", "Other error: $e")
                                }
                            }
                        },

                        onDeleteUser = { id ->
                            lifecycleScope.launch {
                                deleteUser(id)
                                users = users.filter { it.id != id }
                            }
                        }
                    )
                }
            }
        }
    }

    private suspend fun getUsers(): List<User> {
        return service.getUsers().data
    }

    private suspend fun getUser(id: String): User {
        return service.getUser(id).data.first()
    }

    private suspend fun createUser() {
        val newUser = User(
            first_name = "Nou",
            last_name = "Usuari",
            email = "nou@email.com"
        )
        service.createUser(newUser)
    }

    private suspend fun deleteUser(id: Int) {
        service.deleteUser(id)
    }
}