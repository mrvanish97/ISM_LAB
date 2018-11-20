package com.uonagent.iism.math.binomialcoefficient

import java.io.Serializable
import java.util.concurrent.Future

interface BCConcurrentModel: Serializable {
    operator fun get(n: Int, k: Int): Future<Long>?
    operator fun set(n: Int, k: Int, v: Future<Long>)
}