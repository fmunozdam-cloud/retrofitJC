package campalans.m8.retrofitjc.network

import campalans.m8.retrofitjc.model.User
import campalans.m8.retrofitjc.model.UserResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface UserService {

    @GET("users")
    suspend fun getUsers(): UserResponse

    @GET("users/{id}")
    suspend fun getUser(@Path("id") id: String): UserResponse

    @POST("users")
    suspend fun createUser(@Body user: User): Response<User>

    @DELETE("users/{id}")
    suspend fun deleteUser(@Path("id") id: Int): Response<Unit>
}