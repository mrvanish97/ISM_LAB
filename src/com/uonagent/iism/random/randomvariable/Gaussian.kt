package com.uonagent.iism.random.randomvariable

import com.uonagent.iism.random.baserandom.BaseRandom
import com.uonagent.iism.random.baserandom.LCG
import kotlin.math.sqrt

class Gaussian(override val mean: Double,
               override val dispersion: Double,
               private val generator: BaseRandom): ContinuousRandomVariable {

    constructor(mean: Double, dispersion: Double): this(mean, dispersion, LCG())

    private val sigma = sqrt(dispersion)

    override fun cumulative(x: Double): Double {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override val distributionName = "N(mean=$mean, dispersion=$dispersion)"

    override fun next() = sigma * nextStandart() + mean

    private fun nextStandart(): Double {
        var s = 0.0
        for (i in 1..100) {
            s += generator.next()
        }
        s -= 50
        s *= sqrt(12.0) / 10
        return s
    }
}