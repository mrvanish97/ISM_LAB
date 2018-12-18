package com.uonagent.iism.statisticstester

import com.uonagent.iism.random.randomvariable.Laplace
import com.uonagent.iism.random.randomvariable.StatisticsSample
import org.junit.jupiter.api.RepeatedTest

internal class KolmogorovTesterTest {

    private val xi = Laplace(2.0, 3.0)
    private val t = KolmogorovTester(StatisticsSample(xi, 1000), xi, 0.05)

    @RepeatedTest(1000)
    fun runTest() {
        assert(t.runTest().passes)
    }
}