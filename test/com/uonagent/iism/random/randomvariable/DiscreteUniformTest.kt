package com.uonagent.iism.random.randomvariable

import com.uonagent.iism.random.baserandom.BaseRandom
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

internal class DiscreteUniformTest {

    @Test
    operator fun next() {
        val g = DiscreteUniform(0, 1, object : BaseRandom {
            override fun next() = (Int.MAX_VALUE - 1).toDouble() / Int.MAX_VALUE
        })
        assertEquals(1.0, g.next())
    }
}