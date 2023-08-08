package com.sorsix.finalproject.easyeats.models.exception


class EmailAlreadyExist(email: String?) :
    RuntimeException(String.format("Email %s already exist", email))
