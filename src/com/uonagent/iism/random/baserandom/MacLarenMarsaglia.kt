package com.uonagent.iism.random.baserandom

private const val DEFAULT_K = 256

class MacLarenMarsaglia(private val r1: BaseRandom, private val r2: BaseRandom, private val k: Int) : BaseRandom {

    constructor() : this(LCG(), LCG(), DEFAULT_K)

    constructor(r1: BaseRandom, r2: BaseRandom) : this(r1, r2, DEFAULT_K)

    private val v = Array(k) { Double.NaN }

    init {
        for (i in 0 until k) {
            v[i] = r1.next()
        }
    }

    @Synchronized
    override fun next(): Double {
        val next: Double
        val x = r1.next()
        val y = r2.next()
        val j = (k * y).toInt()
        next = v[j]
        v[j] = x
        return next
    }
}