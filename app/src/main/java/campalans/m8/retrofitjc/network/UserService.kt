package campalans.m8.retrofitjc.network

import campalans.m8.retrofitjc.model.User
import campalans.m8.retrofitjc.model.UserResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

// Interface amb els endpoints de l'API
interface UserService {

    // GET /users - obté tots els usuaris
    @GET("users")
    suspend fun getUsers(): UserResponse

    // GET /users/{id} - obté un usuari per ID
    @GET("users/{id}")
    suspend fun getUser(@Path("id") id: String): UserResponse

    // POST /users - crea un usuari nou
    @POST("users")
    suspend fun createUser(@Body user: User): Response<User>

    // PUT /users/{id} - actualitza un usuari
    @PUT("users/{id}")
    suspend fun updateUser(@Path("id") id: Int, @Body user: User): Response<User>

    // DELETE /users/{id} - elimina un usuari
    @DELETE("users/{id}")
    suspend fun deleteUser(@Path("id") id: Int): Response<Unit>
}