package com.uonagent.iism.random.randomvariable

import com.uonagent.iism.random.baserandom.BaseRandom
import com.uonagent.iism.random.baserandom.LCG

open class DiscreteUniform(val a: Int, val b: Int, private val generator: BaseRandom) : DiscreteRandomVariable {

    constructor(a: Int, b: Int) : this(a, b, LCG())

    override val mean: Double
        get() = (a + b).toDouble() / 2
    override val dispersion: Double
        get() = (((b - a + 1) * (b - a + 1)).toDouble() - 1) / 12

    override fun cumulative(x: Double) = when {
        x < a -> 0.0
        x >= b -> 1.0
        else -> (x.toInt() - a + 1).toDouble() / (b - a + 1)
    }

    override fun probability(x: Double) = if (x.toInt().toDouble() == x && x in a..b) {
        1.0 / (b - a + 1)
    } else {
        0.0
    }

    override val distributionName: String
        get() = "Discrete Uniform($a, $b)"

    override fun next(): Double {
        var r = Math.floor(generator.next() * (b - a + 1) + a)
        if (r > b) {
            r = b.toDouble()
        }
        return r
    }

}