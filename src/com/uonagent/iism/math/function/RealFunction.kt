package com.uonagent.iism.math.function

import com.uonagent.iism.random.baserandom.BaseRandom
import com.uonagent.iism.random.baserandom.LCG
import com.uonagent.iism.random.randomvariable.Uniform

class RealFunction(val f: (DoubleArray) -> Double, val n: Int): Operator<DoubleArray, Double> {

    override fun get(x: DoubleArray) = f(x)

    fun integrate(area: (DoubleArray) -> Boolean, bounds: List<Pair<Double, Double>>, numberOfPoints: Int) =
            integrate(area, bounds, numberOfPoints,LCG())

    fun integrate(area: (DoubleArray) -> Boolean,
                  bounds: List<Pair<Double, Double>>,
                  numberOfPoints: Int,
                  generator: BaseRandom): Double {
        val u = Array<Uniform?>(n) { null }
        for (i in 0 until n) {
            u[i] = Uniform(bounds[i].first, bounds[i].second, generator)
        }

        var randomArea = 1.0

        for (i in 0 until n) {
            randomArea *= bounds[i].second - bounds[i].first
        }

        var done = 0
        var attempts = 0
        val xi = DoubleArray(n)
        var s = 0.0

        while (done < numberOfPoints) {
            for (i in xi.indices) {
                xi[i] = u[i]!!.next()
            }
            if (area(xi)) {
                s += f(xi)
                ++done
            }
            ++attempts
        }
        return s * randomArea / attempts
    }
}
