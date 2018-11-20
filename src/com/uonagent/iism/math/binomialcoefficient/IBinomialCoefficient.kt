package com.uonagent.iism.math.binomialcoefficient

import java.io.Serializable

interface IBinomialCoefficient: Serializable {
    operator fun get(n: Int, k: Int): Long
}