package com.uonagent.iism.math.chisquare

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class ChiSquareTest {

    @Test
    fun get() {
        println(ChiSquare[0.05, 5])
        println(ChiSquare[0.25, 4])
    }
}