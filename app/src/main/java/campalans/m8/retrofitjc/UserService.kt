package campalans.m8.retrofitjc

import retrofit2.Response
import retrofit2.http.*

interface UserService {

    @GET("users")
    suspend fun getUsers(): UserResponse

    @GET("users/{id}")
    suspend fun getUser(@Path("id") id: Int): UserResponse

    @POST("users")
    suspend fun createUser(@Body user: User): Response<User>

    @DELETE("users/{id}")
    suspend fun deleteUser(@Path("id") id: Int): Response<Unit>
}