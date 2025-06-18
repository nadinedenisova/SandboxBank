package com.example.sandboxbank.App.ui.debitcards.debit.mapper

import com.example.sandboxbank.Card as ApiCard
import com.example.sandboxbank.cardmanager.cards.entity.Card as LocalCard

fun ApiCard.toLocalModel(): LocalCard = LocalCard(
    id = this.id,
    cvv = this.cvv.toLong(),
    endDate = this.endDate,
    owner = this.owner,
    type = this.type,
    percent = this.percent,
    balance = this.balance.toLong()
)
