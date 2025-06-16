package com.example.sandboxbank.cardmanager.cards.entity

data class Card(
    val id: Long,
    val cvv: Long,
    val endDate: String,
    val owner: String,
    val type: String,
    val percent: Double,
    val balance: Long
)
