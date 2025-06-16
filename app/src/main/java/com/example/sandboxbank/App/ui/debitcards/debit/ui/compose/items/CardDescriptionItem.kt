package com.example.sandboxbank.cardmanager.cards.debit.ui.compose.items

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

private val BorderColor = Color(0xFFCAC4D0)
private val Shape = RoundedCornerShape(12.dp)

@Composable
fun CardDescriptionItem(title: String, value: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
            .background(color = MaterialTheme.colors.background, shape = Shape)
            .border(width = 1.dp, color = BorderColor, shape = Shape)
            .padding(12.dp)
    ) {
        if (title.isNotEmpty()) {
            Text(
                text = title,
                style = MaterialTheme.typography.caption,
                color = Color.Gray
            )
            Spacer(modifier = Modifier.height(4.dp))
        }
        Text(
            text = value,
            style = MaterialTheme.typography.body2
        )
    }
}
