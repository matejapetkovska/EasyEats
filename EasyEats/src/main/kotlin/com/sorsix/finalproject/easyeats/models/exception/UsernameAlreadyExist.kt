package com.sorsix.finalproject.easyeats.models.exception


class UsernameAlreadyExist(username: String?) :
    RuntimeException(String.format("Username %s already exist", username))
