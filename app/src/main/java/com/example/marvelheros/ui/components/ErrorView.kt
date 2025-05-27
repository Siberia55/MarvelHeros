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
import com.example.marvelheros.R
import com.example.marvelheros.ui.theme.diments.Dimens
import com.example.marvelheros.ui.theme.diments.OtherConstants

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
                    .padding(bottom = Dimens.paddingMedium),
            )
            Button(
                onClick = onRetry,
                modifier = Modifier
                    .padding(bottom = Dimens.paddingMedium)
                    .fillMaxWidth(OtherConstants.FILL_MAX_WIDTH_MEDIUM)

            ) {
                Text(
                    text = stringResource(R.string.retry)
                )
            }
            Image(
                painter = painterResource(R.drawable.oops),
                contentDescription = "error",
                modifier = Modifier
                    .fillMaxWidth(OtherConstants.FILL_MAX_WIDTH_MEDIUM)
                    .padding(Dimens.paddingExtraLarge)
                    .width(OtherConstants.appointedSizeOne)
            )
        }
    }
}