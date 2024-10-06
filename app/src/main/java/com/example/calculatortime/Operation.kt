package com.example.calculatortime

import kotlin.math.abs

class Operation(private val firstSec: Int, private val secondSec: Int) {
    fun sum() = firstSec + secondSec
    fun dif() = abs(firstSec - secondSec)
}