package com.uonagent.iism.math.binomialcoefficient

import java.util.concurrent.Callable
import java.util.concurrent.ForkJoinPool
import java.util.concurrent.Future

class ConcurrentDFS(private val model: BCConcurrentModel) : BCAlgorithm {

    private val executor = ForkJoinPool()

    override fun calculate(n: Int, k: Int) = execute(n, k).get()!!

    private fun execute(n: Int, k: Int): Future<Long> {
        if (model[n, k] == null) {
            val f = executor.submit(Calculator(n, k))
            model[n, k] = f
        }
        return model[n, k]!!
    }

    private inner class Calculator(private val n: Int, private val k: Int) : Callable<Long> {
        override fun call(): Long {
            var first = model[n - 1, k - 1]
            if (first == null) {
                first = execute(n - 1, k - 1)
            }
            val f = first.get()
            var second = model[n - 1, k]
            if (second == null) {
                second = execute(n - 1, k)
            }
            return f + second.get()
        }
    }

}