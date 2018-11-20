package com.uonagent.iism.random.randomvariable

import com.uonagent.iism.random.baserandom.BaseRandom
import com.uonagent.iism.random.baserandom.LCG
import com.uonagent.iism.math.binomialcoefficient.BinomialCoefficient
import com.uonagent.iism.math.binomialcoefficient.ConcurrentDFS
import com.uonagent.iism.math.binomialcoefficient.ConcurrentPascalArray

private const val DEFAULT_N = 50

class Poisson(val lambda: Double, n: Int, generator: BaseRandom,
              bc: BinomialCoefficient) : DiscreteRandomVariable {

    constructor(lambda: Double) : this(lambda, DEFAULT_N, LCG(),
            BinomialCoefficient(ConcurrentDFS(ConcurrentPascalArray)))

    constructor(lambda: Double, generator: BaseRandom) : this(lambda, DEFAULT_N, generator,
            BinomialCoefficient(ConcurrentDFS(ConcurrentPascalArray)))

    private val binomial = Binomial(n, lambda / n, generator, bc)

    override val mean: Double
        get() = lambda
    override val dispersion: Double
        get() = lambda

    override fun cumulative(x: Double) = binomial.cumulative(x)

    override fun probability(x: Double) = if (x.toInt().toDouble() == x && x >= 0) {
        l(x.toInt()) * Math.exp(-lambda)
    } else {
        0.0
    }

    private fun l(k: Int): Double {
        var r = 1.0
        for (i in 1..k) {
            r = r * lambda / k
        }
        return r
    }

    override fun next() = binomial.next()

    override val distributionName: String
        get() = "Poisson($lambda)"
}