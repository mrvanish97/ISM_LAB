package com.uonagent.iism.random.randomvariable

import com.uonagent.iism.random.baserandom.BaseRandom
import com.uonagent.iism.random.baserandom.LCG
import com.uonagent.iism.math.binomialcoefficient.BinomialCoefficient
import com.uonagent.iism.math.binomialcoefficient.ConcurrentDFS
import com.uonagent.iism.math.binomialcoefficient.ConcurrentPascalArray
import java.util.concurrent.Executors
import java.util.concurrent.ForkJoinPool


class Binomial(val m: Int, val p: Double, private val generator: BaseRandom,
               private val bc: BinomialCoefficient) : DiscreteRandomVariable {

    constructor(m: Int, p: Double) : this(m, p, LCG(), BinomialCoefficient(ConcurrentDFS(ConcurrentPascalArray)))
    constructor(m: Int, p: Double, generator: BaseRandom) :
            this(m, p, generator, BinomialCoefficient(ConcurrentDFS(ConcurrentPascalArray)))

    private val bernoulli = Bernoulli(p, generator)

    override fun next(): Double {
        var s = 0.0
        for (i in 1..m) {
            s += bernoulli.next()
        }
        return s
    }

    val q = 1 - p
    override val mean get() = m * p
    override val dispersion get() = m * p * q

    override fun cumulative(x: Double) =  when {
            x < 0 -> 0.0
            x > m -> 1.0
            else -> {
                var s = 0.0
                for (k in 0..x.toInt()) {
                    s += bc[m, k] * Math.pow(p, k.toDouble()) * Math.pow(q, (m - k).toDouble())
                }
                s
        }
    }

    override fun probability(x: Double) = if (x.toInt().toDouble() == x && x in 0..m) {
        bc[m, x.toInt()].toDouble() * Math.pow(p, x) * Math.pow(q, m - x)
    } else {
        0.0
    }

    override val distributionName: String
        get() = "Binomial($m, $p)"
}