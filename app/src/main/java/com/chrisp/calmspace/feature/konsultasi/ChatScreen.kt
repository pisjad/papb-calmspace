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
import coil.compose.AsyncImage
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

class ChatViewModel : ViewModel() {
    private val _messages = mutableStateListOf<Message>()
    val messages: List<Message> = _messages

    fun sendMessage(content: String, senderId: String) {
        val message = Message(
            content = content,
            senderId = senderId
        )
        _messages.add(message)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatScreen(
    modifier: Modifier = Modifier,
    chatViewModel: ChatViewModel = viewModel(),
    doctorViewModel: DoctorViewModel = viewModel()
) {
    var messageText by remember { mutableStateOf("") }
    val messages = remember { mutableStateListOf<Message>() }
    val doctorSchedule by doctorViewModel.doctorSchedule

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFECE5DD))
    ) {
        TopAppBar(
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Color(0xFF075E54)
            ),
            title = {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Surface(
                        modifier = Modifier.size(40.dp),
                        shape = CircleShape
                    ) {
//                        AsyncImage(
//                            model = doctorSchedule.imageUrl,
//                            contentDescription = "Profile picture",
//                            modifier = Modifier
//                                .size(40.dp)
//                                .background(Color.Gray.copy(alpha = 0.2f), CircleShape),
//                            placeholder = painterResource(id = R.drawable.default_profile),
//                            error = painterResource(id = R.drawable.default_profile)
//                        )
                    }

                    Spacer(modifier = Modifier.width(12.dp))

                    Column {
                        Text(
                            text = doctorSchedule.name,
                            color = Color.White,
                            style = MaterialTheme.typography.titleMedium
                        )
                        Text(
                            text = "Konsultasi: ${doctorSchedule.dateTime}",
                            color = Color.White.copy(alpha = 0.7f),
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                }
            }
        )

        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            reverseLayout = true,
            contentPadding = PaddingValues(horizontal = 8.dp, vertical = 8.dp)
        ) {
            items(messages.asReversed()) { message ->
                MessageItem(message = message)
                Spacer(modifier = Modifier.height(4.dp))
            }
        }

        Surface(
            modifier = Modifier.fillMaxWidth(),
            color = Color.White,
            tonalElevation = 2.dp
        ) {
            Row(
                modifier = Modifier
                    .padding(horizontal = 8.dp, vertical = 4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextField(
                    value = messageText,
                    onValueChange = { messageText = it },
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 8.dp),
                    colors = TextFieldDefaults.colors(
                        unfocusedContainerColor = Color(0xFFEEEEEE),
                        focusedContainerColor = Color(0xFFEEEEEE),
                        unfocusedIndicatorColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent
                    ),
                    shape = RoundedCornerShape(24.dp),
                    placeholder = { Text("Ketik pesan") }
                )

                FloatingActionButton(
                    onClick = {
                        if (messageText.isNotEmpty()) {
                            messages.add(
                                Message(
                                    content = messageText,
                                    senderId = "currentUser"
                                )
                            )
                            messageText = ""
                        }
                    },
                    containerColor = Purple100,
                    modifier = Modifier.size(48.dp)
                ) {
                    Icon(
                        imageVector = Icons.Filled.Send,
                        contentDescription = "Send message",
                        tint = Color.White
                    )
                }
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

@Preview(showBackground = true, device = "spec:width=360dp,height=640dp")
@Composable
fun ChatScreenPreview() {
    val chatViewModel = ChatViewModel().apply {
        sendMessage("Halo, saya ingin berkonsultasi", "currentUser")
        sendMessage("Selamat datang. Ada yang bisa saya bantu?", "konselor")
    }

    val doctorViewModel = DoctorViewModel()

    MaterialTheme {
        ChatScreen(
            chatViewModel = chatViewModel,
            doctorViewModel = doctorViewModel
        )
    }
}