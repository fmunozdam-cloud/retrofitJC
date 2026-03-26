package campalans.m8.retrofitjc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import campalans.m8.retrofitjc.ui.theme.RetrofitJCTheme
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RetrofitJCTheme {
                var devices by remember { mutableStateOf(listOf<Device>()) }

                getDevices { result ->
                    devices = result
                }

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainView(Modifier.padding(innerPadding),
                        devices = devices)
                }
            }
        }
    }
    private fun getDevices(onResult: (List<Device>) -> Unit){
        val retrofit = Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(DeviceService::class.java)
        //Corrutina
        lifecycleScope.launch{
            val devices = service.getAllDevices()
            onResult(devices)
        }
    }
}

