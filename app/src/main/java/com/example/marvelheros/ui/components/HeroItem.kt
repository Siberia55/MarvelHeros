package com.example.marvelheros.ui.components

import com.example.marvelheros.data.model.Hero

data class HeroItem(val hero: Hero, val onClick: () -> Unit)
