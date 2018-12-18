package com.uonagent.iism.random.randomvariable

import com.uonagent.iism.random.baserandom.BaseRandom
import com.uonagent.iism.random.baserandom.LCG
import kotlin.math.exp

class Laplace(
        override val mean: Double,
        val scale: Double,
        private val generator: BaseRandom
): ContinuousRandomVariable {

    constructor(scale: Double, mean: Double) : this(scale, mean, LCG())

    private val exponential = Exponential(scale, generator)

    override val dispersion = 2.0 / (scale * scale)

    override fun cumulative(x: Double) =
            if (x <= mean) exp(scale * (x - mean)) / 2 else 1 - exp(-scale * (x - mean)) / 2

    override val distributionName = "Laplace($mean, $scale)"

    override fun next() = exponential.next() - exponential.next() + mean
}