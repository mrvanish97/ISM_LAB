package com.uonagent.iism.random.randomvariable

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class BernoulliTest {

    @Test
    operator fun next() {
        val g = Bernoulli(0.8)
        for (i in 1..10000) {
            println(g.next())
        }
        assert(true)
    }
}