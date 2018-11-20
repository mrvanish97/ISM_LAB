package com.uonagent.iism.random.randomvariable

interface ContinuousRandomVariable: RandomVariable {
    fun probability(x: Double) = probability(x, x)
}