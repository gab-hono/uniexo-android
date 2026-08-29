package com.unicofrance.uniexo.ui.googleMap

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties
import androidx.compose.ui.window.Dialog
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import com.unicofrance.uniexo.data.local.database.entities.Container
import com.unicofrance.uniexo.ui.lib.SvgIcon

@Composable
fun ContainerDetailDialog(
    container: Container,
    onDismiss: () -> Unit
) {
    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(topStart = 27.dp, topEnd = 27.dp))
                .background(Color.White)
                .padding(20.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = androidx.compose.foundation.layout.Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(75.dp)
                            .clip(RoundedCornerShape(16.dp))
                            .background(Color(0xFFEBEBEB)),
                        contentAlignment = Alignment.Center
                    ) {
                        SvgIcon(
                            url = container.iconUrl,
                            modifier = Modifier.size(48.dp)
                        )
                    }

                    androidx.compose.foundation.layout.Spacer(modifier = Modifier.size(15.dp))

                    Text(
                        text = container.label,
                        fontSize = 21.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF242526)
                    )
                }

                IconButton(
                    onClick = onDismiss,
                    modifier = Modifier
                        .size(40.dp)
                        .border(2.dp, Color(0xFFD0D0D0), RoundedCornerShape(10.dp))
                ) {
                    Icon(
                        imageVector = Icons.Filled.Close,
                        contentDescription = "Fermer",
                        tint = Color(0xFF242526),
                        modifier = Modifier.size(24.dp)
                    )
                }
            }
        }
    }
}