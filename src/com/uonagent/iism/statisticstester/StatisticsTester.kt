package com.uonagent.iism.statisticstester

import com.uonagent.iism.random.randomvariable.StatisticsSample
import com.uonagent.iism.random.randomvariable.statisticsSampleOf

internal const val EMPTY_SAMPLE_MESSAGE = "Sample can't be empty"

abstract class StatisticsTester {

    abstract inner class Result {
        open val testName = this@StatisticsTester.testName
        abstract val passes: Boolean
        override fun toString()  = "$testName. ${if (passes) "passed" else "failed"}"
    }

    abstract val testName: String

    abstract fun runTest(): Result

    protected val testSample = statisticsSampleOf()

    open fun addToSample(value: Double) {
        testSample.add(value)
    }
    open fun addToSample(sample: StatisticsSample) {
        testSample.addSample(sample)
    }
}