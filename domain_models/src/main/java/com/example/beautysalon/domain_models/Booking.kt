package com.example.beautysalon.domain_models

data class Booking(
    val id: String,
    val masterId: String,
    val clientId: String,
    val serviceId: String,
    val startTs: Long,
    val endTs: Long,
    val status: String, // "pending","confirmed","completed","cancelled"
    val note: String?
)
