package com.chrisp.calmspace.feature.konsultasi

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.chrisp.calmspace.navigation.Screen
import com.chrisp.calmspace.ui.theme.ChevronLeft
import com.chrisp.calmspace.ui.theme.Purple100
import com.chrisp.calmspace.ui.theme.Purple40
import java.text.SimpleDateFormat
import java.util.*

data class Message(
    val id: String = UUID.randomUUID().toString(),
    val content: String,
    val senderId: String,
    val timestamp: Long = System.currentTimeMillis(),
    val status: MessageStatus = MessageStatus.SENT
)

enum class MessageStatus {
    SENT, DELIVERED, READ
}




@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatScreen(navController: NavController, doctorName: String, consultationDate: String) {
    val chatViewModel: ChatViewModel = viewModel()
    var messageText by remember { mutableStateOf("") }
    val messages = chatViewModel.messages

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFECE5DD))
    ) {
        // Top App Bar with Doctor's Name and Consultation Date
        TopAppBar(
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Purple100
            ),
            title = {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth() // Membuat Row mengambil lebar penuh
                ) {
                    IconButton(
                        onClick = {
                            navController.navigate(Screen.Konsultasi.route) {
                                popUpTo(Screen.Chat.route) { inclusive = true }
                            }
                        },
                        modifier = Modifier.size(40.dp)
                    ) {
                        Icon(
                            imageVector = ChevronLeft,
                            contentDescription = "Chevron Left Button",
                            tint = Color.White
                        )
                    }
                    // Memberikan Modifier.weight(1f) agar kolom di sebelah kanan (doctorName dan consultationDate) terdorong ke kiri
                    Spacer(modifier = Modifier.width(8.dp)) // Optional: Menambah sedikit jarak antara ikon dan teks
                    Column(
                        modifier = Modifier.weight(1f) // Menyebabkan kolom ini mengambil ruang lebih besar dan mendorong ke kiri
                    ) {
                        Text(
                            text = doctorName,
                            color = Color.White,
                            style = MaterialTheme.typography.titleMedium
                        )
                        Text(
                            text = "Konsultasi: $consultationDate",
                            color = Color.White.copy(alpha = 0.7f),
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                }
            }
        )



        // Chat messages area
        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .padding(16.dp)
        ) {
            items(messages) { message ->
                MessageItem(message = message)
            }
        }

        // Message input area
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            TextField(
                value = messageText,
                onValueChange = { messageText = it },
                placeholder = { Text("Tulis pesan...") },
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 8.dp),
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.White,
                    focusedIndicatorColor = Color(0xFF075E54)
                )
            )
            IconButton(
                onClick = {
                    if (messageText.isNotBlank()) {
                        // Send message
                        chatViewModel.sendMessage(content = messageText, senderId = "currentUser")
                        messageText = ""  // Reset message input field
                    }
                }
            ) {
                Icon(
                    imageVector = Icons.Filled.Send,
                    contentDescription = "Send Message",
                    tint = Purple100
                )
            }
        }
    }
}





@Composable
fun MessageItem(message: Message) {
    val isCurrentUser = message.senderId == "currentUser"
    val arrangement = if (isCurrentUser) Arrangement.End else Arrangement.Start
    val bubbleColor = if (isCurrentUser) Color(0xFFE7FFDB) else Color.White
    val bubbleShape = RoundedCornerShape(
        topStart = 16.dp,
        topEnd = 16.dp,
        bottomStart = if (isCurrentUser) 16.dp else 0.dp,
        bottomEnd = if (isCurrentUser) 0.dp else 16.dp
    )

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 2.dp),
        horizontalArrangement = arrangement
    ) {
        Surface(
            shape = bubbleShape,
            color = bubbleColor,
            modifier = Modifier.widthIn(max = 280.dp)
        ) {
            Column(
                modifier = Modifier.padding(8.dp)
            ) {
                Text(
                    text = message.content,
                    style = MaterialTheme.typography.bodyMedium
                )

                Row(
                    modifier = Modifier
                        .align(Alignment.End)
                        .padding(top = 2.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = SimpleDateFormat("HH:mm", Locale.getDefault())
                            .format(Date(message.timestamp)),
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.Gray,
                        fontSize = 11.sp
                    )

                    if (isCurrentUser) {
                        Spacer(modifier = Modifier.width(4.dp))
                        Icon(
                            imageVector = Icons.Filled.Done,
                            contentDescription = "Read status",
                            modifier = Modifier.size(16.dp),
                            tint = Color(0xFF34B7F1)
                        )
                    }
                }
            }
        }
    }
}

