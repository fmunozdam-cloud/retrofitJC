package campalans.m8.retrofitjc.model

// Envoltada per la resposta de l'API - els usuaris venen dins de "data"
data class UserResponse(
    val data: List<User>
)