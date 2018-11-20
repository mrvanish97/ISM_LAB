package com.uonagent.iism.random.randompermutation

import com.uonagent.iism.random.baserandom.BaseRandom
import com.uonagent.iism.random.baserandom.LCG
import com.uonagent.iism.random.randomvariable.DiscreteUniform
import kotlin.math.roundToInt

class RandomPermutation(val size: Int, val generator: BaseRandom) {

    constructor(size: Int) : this(size, LCG())

    private val permutationArray = Array(size) { -1 }
    val permutation: List<Int> get() {
        makePermutation()
        return permutationArray.asList()
    }
    private val u = DiscreteUniform(0, size - 1, generator)

    init {
        makePermutation()
    }

    @Synchronized
    private fun makePermutation() {
        val set = linkedSetOf<Int>()
        while (set.size < size) {
            set.add(u.next().roundToInt())
        }
        set.forEachIndexed {index, it -> permutationArray[index] = it }
    }

    companion object {
        fun <E> List<E>.shuffle() = this.shuffle(LCG())

        fun <E> List<E>.shuffle(generator: BaseRandom) : List<E> {
            val randomPermutation = RandomPermutation(this.size, generator)
            val p = randomPermutation.permutation
            val arr = ArrayList<E>(this.size)
            for (i in this.indices) {
                arr[i] = this[p[i]]
            }
            return arr
        }
    }
}