package com.uonagent.iism.random.baserandom

private const val A = 48271L
private const val M = 2147483647L
private const val C = 0L

class LCG(seed: Long, private val multiplier: Long,
          private val modulus: Long, private val increment: Long) : BaseRandom {

    constructor() : this(System.currentTimeMillis(), A, M, C)
    constructor(seed: Long) : this(seed, A, M, C)
    constructor(multiplier: Long, modulus: Long, increment: Long) :
            this(System.currentTimeMillis(), multiplier, modulus, increment)

    companion object {
        val seed = System.currentTimeMillis()
    }

    private var prev = seed

    @Synchronized
    override fun next(): Double {
        val next = Math.abs((prev * multiplier + increment) % modulus)
        prev = next
        return next.toDouble() / modulus
    }
}