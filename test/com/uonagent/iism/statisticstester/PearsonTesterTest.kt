package com.uonagent.iism.statisticstester

import com.uonagent.iism.random.randomvariable.Cauchy
import com.uonagent.iism.random.randomvariable.StatisticsSample
import org.junit.jupiter.api.Test

internal class PearsonTesterTest {

    private val xi = Cauchy(0.0, 1.0)

    @Test
    fun runTest() {
        println(PearsonTester(
                StatisticsSample(xi, 1000),
                xi,
                0.05,
                arrayListOf(-0.1, 0.7, 1.0)).runTest())
    }

    @Test
    fun run() {
        println(PearsonTester(
                StatisticsSample(xi, 10000),
                xi,
                0.05,
                50).runTest())
    }
}