/*
package com.uonagent.iism.statistics.tests

import com.uonagent.iism.random.randomvariable.RandomVariable
import com.uonagent.iism.random.randomvariable.StatisticsSample
import com.uonagent.iism.random.randomvariable.statisticsSampleOf

private const val CHI_SQUARE_N_MESSAGE = "N must be greater than 0"

class PearsonTester(testSample: StatisticsSample, override val variable: RandomVariable
                    private val g: Int, private val eps: Double) : StatisticsTester() {

    init {
        this.testSample.addSample(testSample)
    }

    constructor(xi: RandomVariable, g: Int, eps: Double) : this(statisticsSampleOf(), xi, g, eps)

    companion object {
        private val chiSquareMap = hashMapOf<Double, HashMap<Int, Double>>()

        init {
            chiSquareMap[0.05] = hashMapOf(Pair(1, 3.84146))
            chiSquareMap[0.95] = hashMapOf(Pair(1, 0.00393))
        }

        fun chiSquare(alpha: Double, n: Int): Double {
            if (n > 0) {
                checkOrGenerateChiSquare(alpha, n)
                return chiSquareMap[alpha]!![n]!!
            } else {
                throw IllegalArgumentException(CHI_SQUARE_N_MESSAGE)
            }
        }

        private fun checkOrGenerateChiSquare(alpha: Double, n: Int) {
            if (chiSquareMap[alpha] == null || chiSquareMap[alpha]?.get(n) == null) {
                generateChiSquare(alpha, n)
            }
        }

        private fun generateChiSquare(alpha: Double, n: Int) {
            throw Exception()
        }
    }

    override val testName: String
        get() = "Pearson xi-square"

    inner class Result(val statistics: Double, val chiSquare: Double, val niArray: ArrayList<Int>) : StatisticsTester.Result() {
        override val passes: Boolean get() = statistics < chiSquare
        override fun toString(): String {
            return super.toString() + "\nChi Square = $chiSquare, Statistics = $statistics"
        }
    }

    override fun runTest(): Result {
        val n = testSample.size
        val niArray = arrayListOf<Int>()

        when {
            testSample.isEmpty -> throw IllegalArgumentException(EMPTY_SAMPLE_MESSAGE)
            g > n -> throw IllegalArgumentException(
TOO_MANY_GROUPS_MESSAGE
)
            g < 2 -> throw IllegalArgumentException(
TOO_FEW_GROUPS_MESSAGE
)
        }

        val sample = testSample.sortedSample
        val groups = makeGroupList(n, g)

        var ai: Double
        var bi: Double
        var ni: Int
        var pi: Double
        var ei: Double

        var statistics = 0.0

        for (i in 1..g) {
            ai = sample.getSampleElement(groups[i - 1])
            bi = sample.getSampleElement(groups[i])
            ni = groups[i] - groups[i - 1]
            niArray.add(ni)
            pi = cumulative(bi) - cumulative(ai)
            ei = n * pi
            statistics += Math.pow(ni - ei, 2.0) / ei
        }

        val actualChiSquare = chiSquare(eps, g - 1)
        val result = statistics < actualChiSquare
        return Result(statistics, actualChiSquare, niArray)
    }

    private fun makeGroupList(n: Int, g: Int): List<Int> {
        val indices = arrayListOf(-1)
        val lowerValue = Math.floorDiv(n, g)
        val upperValue = lowerValue + 1
        val x = upperValue * g - n
        val y = g - x
        var counter = x
        var incrementValue = lowerValue
        for (i in 1..g) {
            if (counter == 0) {
                counter = y
                incrementValue = upperValue
            }
            indices.add(indices.last() + incrementValue)
            --counter
        }
        return indices
    }
}
*/
