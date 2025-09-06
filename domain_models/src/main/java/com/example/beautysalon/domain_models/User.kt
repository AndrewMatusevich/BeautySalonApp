package com.example.beautysalon.domain_models

import com.example.beautysalon.domain_models.enums.Role

data class User(
    val token: String? = null,
    val name: String = "",
    val phone: String,
    val role: Role
)
