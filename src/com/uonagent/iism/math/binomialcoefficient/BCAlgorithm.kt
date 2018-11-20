package com.uonagent.iism.math.binomialcoefficient

import java.io.Serializable

interface BCAlgorithm : Serializable {
    fun calculate(n: Int, k: Int): Long
}