package campalans.m8.retrofitjc.ui.theme

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import campalans.m8.retrofitjc.model.User
import kotlinx.coroutines.launch

// Pantalla principal - mostra la llista d'usuaris
@Composable
fun MainView(
    users: List<User>,
    selectedUser: User?,
    isLoading: Boolean,
    errorMessage: String?,
    onAddUser: () -> Unit,
    onDeleteUser: (Int) -> Unit,
    onSelectUser: (User) -> Unit,
    onUpdateUser: (Int, String, String, String) -> Unit,
    onClearError: () -> Unit,
    onCloseUserDetail: () -> Unit,
    modifier: Modifier = Modifier
) {

    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    // Si hi ha error, mostrem el snackbar

    if (errorMessage != null) {
        scope.launch {
            snackbarHostState.showSnackbar(
                message = errorMessage,
                duration = androidx.compose.material3.SnackbarDuration.Long
            )
            onClearError()
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { paddingValues ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {

            // Títol
            Text(
                text = "Gestió de Usuaris",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            // Botó per crear usuari
            Button(
                onClick = onAddUser,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            ) {
                Text("Crear nouusuari")
            }

            // Si està carregant, mostrem el spinner
            if (isLoading) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    CircularProgressIndicator()
                    Text(
                        text = "Carregant...",
                        modifier = Modifier.padding(top = 16.dp)
                    )
                }
            } else {
                // Llista d'usuaris
                if (users.isEmpty()) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "No hi ha usuaris",
                            fontSize = 18.sp,
                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                        )
                    }
                } else {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        items(users) { user ->
                            UserItemView(
                                user = user,
                                onDelete = {
                                    user.id?.let { onDeleteUser(it) }
                                },
                                onSelect = {
                                    onSelectUser(user)
                                }
                            )
                            Divider(modifier = Modifier.padding(vertical = 8.dp))
                        }
                    }
                }
            }
        }
    }

    // Dialog per veure/editar detalls de l'usuari
    if (selectedUser != null) {
        UserDetailDialog(
            user = selectedUser,
            onDismiss = onCloseUserDetail,
            onUpdate = onUpdateUser
        )
    }
}