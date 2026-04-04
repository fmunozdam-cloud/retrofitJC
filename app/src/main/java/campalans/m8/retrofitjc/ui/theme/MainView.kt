package campalans.m8.retrofitjc.ui.theme

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import campalans.m8.retrofitjc.model.User


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