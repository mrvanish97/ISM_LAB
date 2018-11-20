package com.uonagent.iism.math.binomialcoefficient

class BinomialCoefficient(private val algorithm: BCAlgorithm) : IBinomialCoefficient {
    override fun get(n: Int, k: Int) = algorithm.calculate(n, k)
}