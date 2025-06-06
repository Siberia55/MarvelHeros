package com.example.marvelheros.utils

import androidx.compose.foundation.lazy.LazyListState

fun LazyListState.findCenteredItemIndex(): Int? {
    val center = (layoutInfo.viewportStartOffset + layoutInfo.viewportEndOffset) / 2
    return layoutInfo.visibleItemsInfo.minByOrNull { item ->
        val itemCenter = item.offset + item.size / 2
        kotlin.math.abs(itemCenter - center)
    }?.index
}
