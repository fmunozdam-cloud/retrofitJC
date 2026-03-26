package campalans.m8.retrofitjc

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import campalans.m8.retrofitjc.ui.theme.RetrofitJCTheme
import campalans.m8.retrofitjc.ui.theme.Typography

@Composable
fun MainView( modifier: Modifier = Modifier, devices: List<Device>) {

    Column(modifier){
        Text(
            text = "Comprar",
            modifier = Modifier.fillMaxWidth(),
            style = Typography.displayMedium,
            textAlign = TextAlign.Center
        )

        LazyColumn {
            items(devices.size){index ->
                DeviceItemView(devices[index])
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainViewPreview() {
    RetrofitJCTheme {
        MainView(Modifier.padding(top = 24.dp), listOf(
            Device(1,"Nexus", Specs("Black","64GB")),
            Device(2,"Galaxy", null),
            Device(3,"Iphone 4", Specs("Grey","128GB"))))
    }
}