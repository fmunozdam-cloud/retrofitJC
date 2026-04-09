package campalans.m8.retrofitjc.network

import campalans.m8.retrofitjc.model.User
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

// Interface amb els endpoints de l'API - JSONPlaceholder
interface UserService {

    // GET /posts - obté tots els posts (usuaris)
    @GET("posts")
    suspend fun getUsers(): List<User>

    // GET /posts/{id} - obté un post per ID
    @GET("posts/{id}")
    suspend fun getUser(@Path("id") id: String): User

    // POST /posts - crea un post nou
    @POST("posts")
    suspend fun createUser(@Body user: User): Response<User>

    // PUT /posts/{id} - actualitza un post
    @PUT("posts/{id}")
    suspend fun updateUser(@Path("id") id: Int, @Body user: User): Response<User>

    // DELETE /posts/{id} - elimina un post
    @DELETE("posts/{id}")
    suspend fun deleteUser(@Path("id") id: Int): Response<Unit>
}