package com.example.marvelheros.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.marvelheros.R

@Composable
fun ErrorView(
    errorMessage: String?,
    onRetry: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(color = Color.White),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier,
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "${stringResource(R.string.error)} $errorMessage" +
                        " ${stringResource(R.string.unauthorized_error)}",
                color = Color.Black,
                modifier = Modifier
                    .padding(bottom = 10.dp),
            )
            Button(
                onClick = onRetry,
                modifier = Modifier
                    .padding(bottom = 10.dp)
                    .fillMaxWidth(0.5f)

            ) {
                Text( text = stringResource(R.string.retry)
                    )
            }
            Image(
                painter = painterResource(R.drawable.oops),
                contentDescription = "error",
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .padding(30.dp)
                    .width(300.dp)
            )
        }
    }
}