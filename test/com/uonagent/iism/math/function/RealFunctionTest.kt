package com.uonagent.iism.math.function

import com.uonagent.iism.random.baserandom.LCG
import org.junit.jupiter.api.Test
import kotlin.math.exp

internal class RealFunctionTest {

    private val f: (DoubleArray) -> Double = {
        (it[0] * it[0] * it[0] + exp(it[1])) / (it[0] * it[0] + 2 * it[1] * it[1])
    }

    private val area: (DoubleArray) -> Boolean = {
        val r = (it[0] * it[0] + it[1] * it[1])
        1 <= r && r < 7
    }

    private val bounds = arrayListOf(Pair(-4.0, 4.0), Pair(-4.0, 4.0))

    private val mcn = 10000

    private val n = 2

    private val function = RealFunction(f, n)

    private val generator = LCG()

    @Test
    fun integrate() {
        val i = function.integrate(area, bounds, mcn, generator)
        println(i)
    }
}