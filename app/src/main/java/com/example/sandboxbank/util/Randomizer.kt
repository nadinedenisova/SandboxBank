package com.example.sandboxbank.util

import kotlin.random.Random

object Randomizer{

    fun trueOrFalse(percent:Int): Boolean{
        return (Random.nextDouble() * 100) > percent
    }

    fun randomString(length: Int): String{
        val charRange = ('a'..'z') + ('A'..'Z') + ('0'..'9')
        val random = Random
        val resultString = StringBuilder(length)

        for (i in 0 until length) {
            resultString.append(charRange.random(random))
        }

        return resultString.toString()
    }
}