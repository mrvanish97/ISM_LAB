package com.uonagent.iism.math.binomialcoefficient

import java.io.Serializable

interface BCModel : Serializable {
    operator fun get(n: Int, k: Int): Long?
    operator fun set(n: Int, k: Int, v: Long)
}