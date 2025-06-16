package com.example.sandboxbank.cardmanager.cards.debit.ui.compose.items

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.sandboxbank.R

import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.material.Surface
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource

@Composable
fun CardView() {
    val cardHeight = 205.dp

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(cardHeight)
            .clip(RoundedCornerShape(16.dp))
    ) {
        Image(
            painter = painterResource(id = R.drawable.image_bacground_final),
            contentDescription = "Card Background",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .fillMaxSize()
        )

        Box(modifier = Modifier.fillMaxSize()) {
            Text(
                text = stringResource(R.string.card_bank_title),
                style = MaterialTheme.typography.h5,
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(start = 45.5.dp, top = 41.53.dp)
            )

            Text(
                text = stringResource(R.string.one_million_rouble),
                style = MaterialTheme.typography.h6,
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(start = 45.5.dp, bottom = 46.32.dp)
            )

            Row(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(end = 55.dp, bottom = 47.dp),
                verticalAlignment = Alignment.Bottom
            ) {
                Image(
                    painter = painterResource(id = R.drawable.image_mi),
                    contentDescription = "MI"
                )
                Spacer(modifier = Modifier.width(2.dp))
                Column(
                    verticalArrangement = Arrangement.spacedBy(1.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.image_mir_r1),
                        contentDescription = "R top"
                    )
                    Image(
                        painter = painterResource(id = R.drawable.image_mir_r2),
                        contentDescription = "R bottom"
                    )
                }
            }
        }
    }
}





@Preview(showBackground = true)
@Composable
fun CardViewPreview() {
    MaterialTheme {
        Surface {
            CardView()
        }
    }
}

