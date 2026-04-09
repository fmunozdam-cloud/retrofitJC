package campalans.m8.retrofitjc

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import campalans.m8.retrofitjc.model.User
import campalans.m8.retrofitjc.network.UserService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// ViewModel per gestionar la lògica dels usuaris
class UserViewModel : ViewModel() {

    // Configurem Retrofit per cridar la API
    private val retrofit = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    // Servei de la API
    private val service = retrofit.create(UserService::class.java)

    // Llista de tots els usuaris
    private val _users = MutableStateFlow<List<User>>(emptyList())
    val users: StateFlow<List<User>> = _users.asStateFlow()

    // Usuari seleccionat per veure detalls
    private val _selectedUser = MutableStateFlow<User?>(null)
    val selectedUser: StateFlow<User?> = _selectedUser.asStateFlow()

    // Per mostrar el spinner de càrrega
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    // Missatges d'error pel Snackbar
    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage.asStateFlow()

    // Quan es crea el ViewModel, carreguem els usuaris
    init {
        loadUsers()
    }

    // Obté tots els usuaris de la API
    fun loadUsers() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val response = service.getUsers()
                _users.value = response.data
                _errorMessage.value = null
                Log.d("UserViewModel", "Usuaris carregats: ${response.data.size}")
            } catch (e: Exception) {
                _errorMessage.value = "Error al carregar usuaris: ${e.message}"
                Log.e("UserViewModel", "Error loading users", e)
            } finally {
                _isLoading.value = false
            }
        }
    }

    // Obté un usuari específic per ID
    fun getUser(id: String) {
        viewModelScope.launch {
            try {
                val response = service.getUser(id)
                if (response.data.isNotEmpty()) {
                    _selectedUser.value = response.data.first()
                } else {
                    _errorMessage.value = "Usuari no trobat"
                }
                Log.d("UserViewModel", "Usuari obtingut: ${response.data}")
            } catch (e: Exception) {
                _errorMessage.value = "Error al obtenir usuari: ${e.message}"
                Log.e("UserViewModel", "Error getting user", e)
            }
        }
    }

    // Crea un usuari nou
    fun createUser(firstName: String, lastName: String, email: String) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val newUser = User(
                    first_name = firstName,
                    last_name = lastName,
                    email = email
                )
                val response = service.createUser(newUser)
                if (response.isSuccessful) {
                    _errorMessage.value = null
                    loadUsers()
                    Log.d("UserViewModel", "Usuari creat: ${response.body()}")
                } else {
                    _errorMessage.value = "Error al crear usuari: ${response.code()}"
                }
            } catch (e: Exception) {
                _errorMessage.value = "Error al crear usuari: ${e.message}"
                Log.e("UserViewModel", "Error creating user", e)
            } finally {
                _isLoading.value = false
            }
        }
    }

    // Actualitza un usuari existent
    fun updateUser(id: Int, firstName: String, lastName: String, email: String) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val updatedUser = User(
                    id = id,
                    first_name = firstName,
                    last_name = lastName,
                    email = email
                )
                val response = service.updateUser(id, updatedUser)
                if (response.isSuccessful) {
                    _errorMessage.value = null
                    loadUsers()
                    _selectedUser.value = null
                    Log.d("UserViewModel", "Usuari actualitzat: ${response.body()}")
                } else {
                    _errorMessage.value = "Error al actualitzar usuari: ${response.code()}"
                }
            } catch (e: Exception) {
                _errorMessage.value = "Error al actualitzar usuari: ${e.message}"
                Log.e("UserViewModel", "Error updating user", e)
            } finally {
                _isLoading.value = false
            }
        }
    }

    // Elimina un usuari
    fun deleteUser(id: Int) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val response = service.deleteUser(id)
                if (response.isSuccessful) {
                    _errorMessage.value = null
                    loadUsers()
                    Log.d("UserViewModel", "Usuari eliminat: $id")
                } else {
                    _errorMessage.value = "Error al eliminar usuari: ${response.code()}"
                }
            } catch (e: Exception) {
                _errorMessage.value = "Error al eliminar usuari: ${e.message}"
                Log.e("UserViewModel", "Error deleting user", e)
            } finally {
                _isLoading.value = false
            }
        }
    }

    // Neteja el missatge d'error
    fun clearError() {
        _errorMessage.value = null
    }

    // Tanca el dialog de detalls
    fun clearSelectedUser() {
        _selectedUser.value = null
    }
}

