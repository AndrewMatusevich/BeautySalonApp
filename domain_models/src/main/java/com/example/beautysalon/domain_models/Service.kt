package com.example.beautysalon.domain_models

data class Service(
    val id: String,
    val title: String,
    val durationMinutes: Int,
    val priceCents: Int
)