package com.uonagent.iism.statisticstester

import com.uonagent.iism.random.baserandom.JavaDefaultLCG
import com.uonagent.iism.random.baserandom.SecureLCG
import com.uonagent.iism.random.randomvariable.*
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class PearsonTesterTest {

    private val xi = Bernoulli(0.1, SecureLCG())

    @Test
    fun runTest() {
        println(PearsonTester(
                StatisticsSample(xi, 1000),
                xi,
                0.05,
                arrayListOf(-0.1, 0.7, 1.0)).runTest())
    }
}