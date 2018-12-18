package com.uonagent.iism.random.randomvariable

import com.uonagent.iism.random.baserandom.BaseRandom
import com.uonagent.iism.random.baserandom.LCG
import kotlin.math.PI
import kotlin.math.atan
import kotlin.math.tan

class Cauchy(val x0: Double,
             val gamma: Double,
             private val generator: BaseRandom) : ContinuousRandomVariable {

    constructor(x0: Double, gamma: Double) : this(x0, gamma, LCG())

    private val u = Gaussian(0.0, 1.0, generator)

    override val mean = Double.NaN

    override val dispersion = Double.POSITIVE_INFINITY

    override fun cumulative(x: Double) = atan((x - x0) / gamma) / PI + 0.5

    override val distributionName = "Cauchy($x0, $gamma)"

    override fun next() = x0 + gamma * tan(PI * (u.next() - 0.5))
}