package campalans.m8.retrofitjc.model

data class User(
    val id: Int? = null,
    val first_name: String,
    val last_name: String,
    val email: String,
    val avatar: String? = null
)