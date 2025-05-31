package com.example.marvelheros.ui.components.errorScreen

import androidx.compose.material3.MaterialTheme
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.example.marvelheros.R
import com.example.marvelheros.ui.theme.diments.Dimens
import com.example.marvelheros.ui.theme.diments.OtherConstants
import com.example.marvelheros.utils.ErrorCode

@Composable
fun ErrorScreen(
    modifier: Modifier = Modifier,
    errorCode: ErrorCode,
    errorMessage: String? = null,
    onRetry: () -> Unit

) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(color = Color.White),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(Dimens.paddingLarge),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(R.drawable.oops),
                contentDescription = "error",
                modifier = Modifier
                    .fillMaxWidth(OtherConstants.FILL_MAX_WIDTH_MEDIUM)
                    .padding(Dimens.paddingExtraLarge)
                    .width(OtherConstants.appointedSizeOne)
            )
            Text(
                text = when (errorCode) {
                    ErrorCode.NETWORK_ERROR -> stringResource(R.string.network_error)
                    ErrorCode.SERVER_ERROR -> stringResource(R.string.server_error)
                    ErrorCode.UNKNOWN_ERROR -> stringResource(R.string.unknown_error)
                } + (errorMessage?.let { ": $it" } ?: ""),
                style = MaterialTheme.typography.headlineSmall,
                color = Color.Black,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(vertical = Dimens.paddingMedium)
            )
            Button(
                onClick = onRetry,
                modifier = Modifier
                    .padding(vertical = Dimens.paddingMedium)
                    .fillMaxWidth(OtherConstants.FILL_MAX_WIDTH_MEDIUM)
            ) {
                Text(text = stringResource(R.string.retry))
            }
        }
    }
}