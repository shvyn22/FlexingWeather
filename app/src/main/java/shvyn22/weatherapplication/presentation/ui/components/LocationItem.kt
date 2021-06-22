package shvyn22.weatherapplication.presentation.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import shvyn22.weatherapplication.data.local.model.Location

@Composable
fun LocationItem(
    item: Location,
    onClick: (Int) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick(item.woeId) }
            .padding(bottom = 5.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(
                    horizontal = 10.dp,
                    vertical = 6.dp
                )
        ) {
            Text(
                text = item.title,
                style = MaterialTheme.typography.body1,
                modifier = Modifier.padding(bottom = 5.dp)
            )
            Text(
                text = item.lattLong,
                style = MaterialTheme.typography.body2,
            )
        }
    }
}