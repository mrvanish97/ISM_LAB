package com.uonagent.iism.random.randomvariable

import com.uonagent.iism.random.baserandom.BaseRandom
import com.uonagent.iism.random.baserandom.LCG
import com.uonagent.iism.random.baserandom.MacLarenMarsaglia

class Uniform(val a: Double, val b: Double, private val generator: BaseRandom) : ContinuousRandomVariable {

    constructor(a: Double, b: Double) : this(a, b, LCG())

    override val mean get() = (a + b) / 2
    override val dispersion get() = Math.pow(b - a, 2.0) / 12

    override fun cumulative(x: Double) =
            when {
                x in a..b -> (x - a) / (b - a)
                x < a -> 0.0
                else -> 1.0
            }

    override fun next() = (b - a) * generator.next() + a

    override val distributionName: String
        get() = "Uniform($a, $b)"
}