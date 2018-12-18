package com.uonagent.iism.random.randomvariable

import com.uonagent.iism.random.baserandom.BaseRandom
import com.uonagent.iism.random.baserandom.LCG
import kotlin.math.exp
import kotlin.math.ln

class Exponential(val lambda: Double, private val generator: BaseRandom): ContinuousRandomVariable {

    constructor(lambda: Double) : this(lambda, LCG())

    override val mean = 1 / lambda

    override val dispersion = mean / lambda

    override fun cumulative(x: Double) = if (x >= 0) 1 - exp(-lambda * x) else 0.0

    override val distributionName = "Exp($lambda)"

    override fun next() = -ln(generator.next()) / lambda
}