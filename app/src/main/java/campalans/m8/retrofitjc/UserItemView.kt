package campalans.m8.retrofitjc

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun UserItemView(user: User, onDelete: () -> Unit) {
    Row(modifier = Modifier.padding(8.dp)) {

        Column(modifier = Modifier.weight(1f)) {
            Text(text = "${user.first_name} ${user.last_name}")
            Text(text = user.email)
        }

        Button(onClick = onDelete) {
            Text("Eliminar")
        }
    }
}