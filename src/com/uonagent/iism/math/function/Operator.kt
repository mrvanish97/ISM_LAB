package com.uonagent.iism.math.function

interface Operator<X, Y> {
    fun get(x: X): Y
}