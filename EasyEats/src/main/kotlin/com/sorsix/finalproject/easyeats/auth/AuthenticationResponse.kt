package com.sorsix.finalproject.easyeats.auth

import lombok.AllArgsConstructor
import lombok.Builder
import lombok.Data
import lombok.NoArgsConstructor

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
class AuthenticationResponse(val token: String = "") {
}