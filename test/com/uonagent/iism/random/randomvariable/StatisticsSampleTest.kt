package com.uonagent.iism.random.randomvariable

import com.uonagent.iism.random.baserandom.SecureLCG
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class StatisticsSampleTest {

    private val s1 = StatisticsSample(Bernoulli(0.2), 100000)
    private val s2 = StatisticsSample(Uniform(0.0, 1.0), 100000)

    @Test
    fun next() {
        println(s2.cumulative(0.5))
    }

    @Test
    fun kek() {
        val inc = 0.01
        var i = -0.2
        while (i <= 1.1) {
            println("f($i) = ${s2.cumulative(i)}")
            i += inc
        }
    }

    @Test
    fun probability() {
        println(s2.probability(0.9, 1.1))
    }
}