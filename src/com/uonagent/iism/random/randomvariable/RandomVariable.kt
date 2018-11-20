package com.uonagent.iism.random.randomvariable

import com.uonagent.iism.random.Random

interface RandomVariable : Random {
    val mean: Double
    val dispersion: Double
    fun cumulative(x: Double): Double
    fun probability(a: Double, b: Double): Double {
        val cb = cumulative(b)
        val ca = cumulative(a)
        return cb - ca
    }
    val distributionName: String
    fun description() = "$distributionName\nMean = $mean, Dispersion = $dispersion"
}