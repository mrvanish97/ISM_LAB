package com.uonagent.iism.math.function

interface Function<X, Y> {
    fun get(x: X): Y
    val isInDefinitionArea: (x: X) -> Boolean
}