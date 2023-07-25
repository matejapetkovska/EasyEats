package com.sorsix.finalproject.easyeats.models.dto

import com.sorsix.finalproject.easyeats.models.User
import lombok.Data


@Data
class UserDetailsDto {
    private var username: String? = null
    private var password: String? = null

    companion object {
        fun of(user: User): UserDetailsDto {
            val detailsDto = UserDetailsDto()
            detailsDto.username = user.username
            detailsDto.password = user.password
            return detailsDto
        }
    }
}

