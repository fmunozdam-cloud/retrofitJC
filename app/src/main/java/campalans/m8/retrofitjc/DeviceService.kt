package campalans.m8.retrofitjc

import retrofit2.http.GET

interface DeviceService {
    @GET(Constants.OBJECTS_PATH)
    suspend fun getAllDevices(): List<Device>
}