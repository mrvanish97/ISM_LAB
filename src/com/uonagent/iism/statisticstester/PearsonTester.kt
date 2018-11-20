package com.uonagent.iism.statisticstester

import com.uonagent.iism.math.chisquare.ChiSquare
import com.uonagent.iism.random.randomvariable.RandomVariable
import com.uonagent.iism.random.randomvariable.StatisticsSample
import java.lang.Math.pow

private const val SPLIT_POINTS_EXCEPTION_MESSAGE = "Size of list must be greater than 2"

class PearsonTester(private val xi: StatisticsSample, private val eta: RandomVariable,
                    private val eps: Double, private val splitPoints: List<Double>) : StatisticsTester() {

    init {
        if (splitPoints.size < 3) {
            throw IllegalArgumentException(SPLIT_POINTS_EXCEPTION_MESSAGE)
        }
    }

    inner class Result(val statistics: Double, val quantile: Double) : StatisticsTester.Result() {
        override val passes: Boolean get() = statistics < quantile
        override fun toString(): String {
            return super.toString() + "\nQuantile = $quantile, Statistics = $statistics"
        }
    }

    override val testName = "Pearson Tester"

    override fun runTest(): Result {
        var s = 0.0
        val n = xi.size
        for (i in 0 until splitPoints.size - 1) {
            val a = splitPoints[i]
            val b = splitPoints[i + 1]
            val pXi = xi.probability(a, b)
            val pEta = eta.probability(a, b)
            s += n * pow(pXi - pEta, 2.0) / pEta
        }
        return Result(s, ChiSquare[eps, splitPoints.size - 1])
    }

}
