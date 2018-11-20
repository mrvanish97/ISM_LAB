package com.uonagent.iism.random.baserandom

class LCG(seed: Long, private val multiplier: Long,
          private val modulus: Long, private val increment: Long) : BaseRandom {

    constructor() : this(System.currentTimeMillis(), 48271, 2147483647, 0)
    constructor(seed: Long) : this(seed, 48271, 2147483647, 0)

    private var prev = seed

    @Synchronized
    override fun next(): Double {
        val next = Math.abs((prev * multiplier + increment) % modulus)
        prev = next
        return next.toDouble() / modulus
    }
}