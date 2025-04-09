package com.example.marvelheros.utils

import java.security.MessageDigest
import javax.inject.Inject

class MarvelAuth (
    val publicKey: String = "MARVEL_PUBLIC_KEY",
    private val privateKey: String = "MARVEL_PRIVATE_KEY"
) {
    fun generateHash(ts: String): String {
        val input = "$ts$privateKey$publicKey"
        return md5(input)
    }

    fun getTimestamp(): String = System.currentTimeMillis().toString()

    private fun md5(input: String): String {
        val md = MessageDigest.getInstance("MD5")
        return md.digest(input.toByteArray())
            .joinToString("") { "%02x".format(it) }
    }
}
/*
data class AuthParams(
    val ts: String,
    val apiKey: String,
    val hash: String
)

 */