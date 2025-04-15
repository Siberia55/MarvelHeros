package com.example.marvelheros.utils

import com.example.marvelheros.BuildConfig
import com.example.marvelheros.BuildConfig.MARVEL_PUBLIC_KEY
import java.security.MessageDigest

class MarvelAuth (
    val publicKey: String = MARVEL_PUBLIC_KEY,
    private val privateKey: String = BuildConfig.MARVEL_PRIVATE_KEY
) {
    fun generateHash(ts: String): String {
        val input = "$ts$privateKey$publicKey"
        val md5 = md5(input)
        return md5
    }

    fun getTimestamp(): String = System.currentTimeMillis().toString()

    @OptIn(ExperimentalStdlibApi::class)
    private fun md5(input: String): String {
        val md = MessageDigest.getInstance("MD5")
        return md.digest(input.toByteArray())
            .toHexString()
    }
}
