package com.emirhan.marvel.util.network

import okhttp3.internal.and
import java.security.MessageDigest
import java.util.*

object AuthItemsGenerator {
    fun getTimeStamp(): String =
        (Calendar.getInstance(TimeZone.getTimeZone("UTC")).timeInMillis / 1000L).toString()

    fun getMD5Key(): String {
        val input: String =
            getTimeStamp() + ApiConstants.PRIVATE_API_KEY + ApiConstants.PUBLIC_API_KEY
        val md = MessageDigest.getInstance("MD5")
        val md5Bytes = md.digest(input.toByteArray())

        val md5 = StringBuilder()
        md5Bytes.forEach { byte ->
            md5.append(Integer.toHexString(byte and 0xFF or 0x100).substring(1, 3))
        }
        return md5.toString()
    }
}