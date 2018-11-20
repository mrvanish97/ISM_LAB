package com.uonagent.iism.random.randomvariable

import com.uonagent.iism.random.baserandom.BaseRandom
import com.uonagent.iism.random.baserandom.LCG
import com.uonagent.iism.random.baserandom.MacLarenMarsaglia

class Bernoulli(val p: Double, private val generator: BaseRandom) : DiscreteRandomVariable {

    constructor(p: Double) : this(p, LCG())

    val q = 1 - p

    override val mean get() = p
    override val dispersion = p * q

    override fun cumulative(x: Double) = when {
        x < 0 -> 0.0
        x >= 1 -> 1.0
        else -> q
    }

    override fun probability(x: Double) = when (x) {
        1.0 -> p
        0.0 -> q
        else -> 0.0
    }

    override fun next() = if (generator.next() <= p) 1.0 else 0.0

    override val distributionName: String
        get() = "Bernoulli($p)"
}