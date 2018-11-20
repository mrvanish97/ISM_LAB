package com.uonagent.iism.statisticstester

import com.uonagent.iism.random.baserandom.JavaDefaultLCG
import com.uonagent.iism.random.randomvariable.Bernoulli
import com.uonagent.iism.random.randomvariable.Poisson
import com.uonagent.iism.random.randomvariable.StatisticsSample
import com.uonagent.iism.random.randomvariable.Uniform
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class KolmogorovTesterTest {

    private val xi = Bernoulli(0.2)
    private val t = KolmogorovTester(StatisticsSample(xi, 1000), xi, 0.05)

    @Test
    fun runTest() {
        println(t.runTest())
    }
}