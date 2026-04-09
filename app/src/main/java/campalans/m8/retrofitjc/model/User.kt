package campalans.m8.retrofitjc.model

// Model que representa un usuari (post de JSONPlaceholder)
data class User(
    val id: Int,
    val userId: Int? = null,
    val title: String,
    val body: String,
    val first_name: String? = null,
    val last_name: String? = null,
    val email: String? = null,
    val avatar: String? = null
) {
    // Per compatibilitat amb el codi existent
    fun getFirstName(): String = first_name ?: title.split(" ").firstOrNull() ?: "U"
    fun getLastName(): String = last_name ?: body.split(" ").firstOrNull() ?: "ser"
    fun getEmailAddress(): String = email ?: "post${id}@example.com"
}
