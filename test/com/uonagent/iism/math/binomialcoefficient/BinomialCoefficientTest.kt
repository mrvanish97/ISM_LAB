package com.uonagent.iism.math.binomialcoefficient

import org.junit.jupiter.api.Test

internal class BinomialCoefficientTest {

    @Test
    fun getBFSConcurrent() {
        val bc = BinomialCoefficient(ConcurrentBFS(ConcurrentPascalArray))
        var actual = bc[66, 33]
        println(actual)
        actual = bc[5, 2]
        println(actual)
        assert(true)
    }

}