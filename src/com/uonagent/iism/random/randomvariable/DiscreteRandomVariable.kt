package com.uonagent.iism.random.randomvariable

interface DiscreteRandomVariable: RandomVariable {
    fun probability(x: Double): Double
}