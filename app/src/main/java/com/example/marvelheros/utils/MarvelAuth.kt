package com.example.marvelheros.utils

import com.example.marvelheros.BuildConfig
import com.example.marvelheros.BuildConfig.MARVEL_PUBLIC_KEY
import java.security.MessageDigest
import javax.inject.Inject

class MarvelAuth (
    val publicKey: String = "2bca3557f5c0e729ea3ff7d5c85fde20",//"MARVEL_PUBLIC_KEY", как будто не принимает ключ. при вводе любой строки http 401
    private val privateKey: String = "5caf80d8affd5a28cc6714b1ddd44006c21e9d4b"//BuildConfig.MARVEL_PRIVATE_KEY//"MARVEL_PRIVATE_KEY"
) {
    fun generateHash(ts: String): String {
        val input = "$ts$privateKey$publicKey" // попробовать сюда BuildConfig.MARVEL_PRIVATE_KEY//"MARVEL_PRIVATE_KEY"
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
/*
data class AuthParams(
    val ts: String,
    val apiKey: String,
    val hash: String
)

 */