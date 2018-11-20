package com.uonagent.iism.statisticstester

import com.uonagent.iism.random.randomvariable.RandomVariable
import com.uonagent.iism.random.randomvariable.StatisticsSample
import com.uonagent.iism.random.randomvariable.statisticsSampleOf

class KolmogorovTester(testSample: StatisticsSample, private val variable: RandomVariable, private val eps: Double) :
        StatisticsTester() {

    init {
        this.testSample.addSample(testSample)
    }

    constructor(xi: RandomVariable, eps: Double) : this(statisticsSampleOf(), xi, eps)

    override val testName: String
        get() = "Kolmogorov-Smirnov testName"

    inner class Result(val statistics: Double, val quantile: Double) : StatisticsTester.Result() {
        override val passes: Boolean get() = statistics < quantile
        override fun toString(): String {
            return super.toString() + "\nQuantile = $quantile, Statistics = $statistics"
        }
    }

    override fun runTest(): Result {
        if (testSample.isEmpty) {
            throw IllegalArgumentException(EMPTY_SAMPLE_MESSAGE)
        }
        var dn = Double.MIN_VALUE
        val n = testSample.size
        var di: Double
        testSample.values.forEach {
            di = Math.abs(testSample.cumulative(it) - variable.cumulative(it))
            dn = Math.max(di, dn)
        }
        val sqrtN = Math.sqrt(n.toDouble())
        val statistics = sqrtN * dn + 1 / (6 * sqrtN)
        val l = kAlpha(eps)
        return Result(statistics, l)
    }

    fun kAlpha(eps: Double) = 1.3581
}
