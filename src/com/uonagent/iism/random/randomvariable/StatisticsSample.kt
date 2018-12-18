package com.uonagent.iism.random.randomvariable

import com.uonagent.iism.random.Random

private const val DEFAULT_NUMBER_OF_ELEMENTS = 10

private const val SAMPLE_IS_EMPTY = "Can't get next. Sample is empty"

class StatisticsSample() : DiscreteRandomVariable {
    constructor(values: DoubleArray) : this() {
        values.forEach { add(it) }
    }

    constructor(random: Random, size: Int) : this() {
        for (i in 1..size) {
            add(random.next())
        }
    }

    private var nextIndex = 0

    @Synchronized
    override fun next(): Double {
        if (size == 0) {
            throw IllegalStateException(SAMPLE_IS_EMPTY)
        }
        if (++nextIndex == size) {
            nextIndex = 0
        }
        return sampleArr[nextIndex]
    }

    private val freqMap = sortedMapOf<Double, Int>()
    private val cumulativeMap = sortedMapOf<Double, Int>()

    private lateinit var valArr: MutableList<Double>

    val values: List<Double> get() {
        if (!this::valArr.isInitialized) {
            valArr = arrayListOf()
        }
        if (valArr.size != freqMap.size) {
            valArr.clear()
            valArr.addAll(freqMap.keys.sorted())
        }
        return valArr
    }

    private fun initCumulativeMap() {
        if (freqMap.size != cumulativeMap.size) {
            cumulativeMap.clear()
            var prev = 0
            freqMap.forEach { t, u ->
                cumulativeMap[t] = u + prev
                prev = cumulativeMap[t]!!
            }
        }
    }

    override fun cumulative(x: Double) = when {
        x < freqMap.keys.first() -> 0.0
        x >= freqMap.keys.last() -> 1.0
        else -> {
            initCumulativeMap()
            val k = cumulativeMap.keys.findLast { it <= x }
            cumulativeMap[k]!!.toDouble() / size
        }
    }

    override fun probability(a: Double, b: Double) = if (a != b) {
        super.probability(a, b)
    } else {
        probability(a)
    }

    override fun probability(x: Double) = if (freqMap.contains(x)) {
        freqMap[x]!!.toDouble() / size
    } else {
        0.0
    }

    private val sampleArr = arrayListOf<Double>()

    private var sum = 0.0

    val size get() = sampleArr.size
    val isEmpty get() = sampleArr.size == 0

    val list get() = sampleArr.toList()

    fun add(value: Double) {
        sampleArr.add(value)
        sum += value
        if (freqMap[value] == null) {
            freqMap[value] = 1
        } else {
            freqMap[value] = freqMap[value]!! + 1
        }
    }

    fun addSample(sample: StatisticsSample) {
        sample.sampleArr.forEach {
            add(it)
        }
    }

    override val mean = sum / size

    override val dispersion: Double
        get() {
            var dispersionSum = 0.0
            sampleArr.forEach {
                dispersionSum += Math.pow(it - mean, 2.0)
            }
            return dispersionSum / (size - 1)
        }

    override val distributionName = "Statistics Sample"

    override fun toString() = "Sample size = $size, mean = $mean, dispersion = $dispersion"
}

fun statisticsSampleOf(vararg values: Double): StatisticsSample = if (values.isEmpty())
    StatisticsSample() else StatisticsSample(values)