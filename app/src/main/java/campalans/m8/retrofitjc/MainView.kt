package campalans.m8.retrofitjc

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable


@Composable
fun MainView(
    users: List<User>,
    onAddUser: () -> Unit,
    onDeleteUser: (Int) -> Unit
) {

    Column {

        Button(onClick = onAddUser) {
            Text("Crear usuario")
        }

        LazyColumn {
            items(users) { user ->
                UserItemView(
                    user = user,
                    onDelete = {
                        user.id?.let { onDeleteUser(it) }
                    }
                )
            }
        }
    }
}