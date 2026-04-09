package campalans.m8.retrofitjc.model

// Envoltada per la resposta de l'API - JSONPlaceholder retorna array directament
// Per compatibilitat, cream aquesta classe
data class UserResponse(
    val data: List<User> = emptyList()
)