package com.uonagent.iism.random.randomvariable

import com.uonagent.iism.random.baserandom.BaseRandom
import com.uonagent.iism.random.baserandom.LCG
import org.apache.commons.math3.special.Erf
import kotlin.math.sqrt

class Gaussian(override val mean: Double,
               override val dispersion: Double,
               private val generator: BaseRandom): ContinuousRandomVariable {

    constructor(mean: Double, dispersion: Double): this(mean, dispersion, LCG())

    private val sigma: Double by lazy { sqrt(dispersion) }

    private val multNextStand: Double by lazy { sqrt(12.0) / 10 }

    private val divCumulative: Double by lazy { sigma * sqrt(2.0) }

    override fun cumulative(x: Double) = (1 + Erf.erf((x - mean) / divCumulative)) / 2

    override val distributionName = "N(mean=$mean, dispersion=$dispersion)"

    override fun next() = sigma * nextStandart() + mean

    private fun nextStandart(): Double {
        var s = 0.0
        for (i in 1..100) {
            s += generator.next()
        }
        s -= 50
        s *= multNextStand
        return s
    }
}