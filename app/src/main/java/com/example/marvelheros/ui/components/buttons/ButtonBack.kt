package com.example.marvelheros.ui.components.buttons

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBackIos
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.marvelheros.R
import com.example.marvelheros.ui.theme.diments.Dimens
import com.example.marvelheros.ui.theme.diments.OtherConstants

@Composable
fun ButtonBack(
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier
) {
    IconButton(
        onClick = onDismiss,
        modifier = Modifier
            .padding(Dimens.paddingLarge)
            .size(OtherConstants.mediumSIze)
    ) {
        androidx.compose.material3.Icon(
            imageVector = Icons.AutoMirrored.Filled.ArrowBackIos,
            contentDescription = stringResource(R.string.button_back),
            tint = MaterialTheme.colorScheme.primaryContainer,
            modifier = Modifier.size(OtherConstants.smallSize)
        )
    }
}