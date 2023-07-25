package com.sorsix.finalproject.easyeats.configurations


object JWTAuthConstants {
    val EXPIRATION_TIME: Long = 864000000 //10 days
    val SECRET = "s3cr3tkey"
    val TOKEN_PREFIX = "Bearer "
    val HEADER_STRING = "Authorization"
}
