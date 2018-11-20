package com.uonagent.iism.random.randomvariable

import com.uonagent.iism.random.baserandom.MacLarenMarsaglia
import com.uonagent.iism.random.baserandom.SecureLCG
import org.junit.jupiter.api.Test

internal class PoissonTest {

    @Test
    operator fun next() {
        val p = Poisson(5.0, MacLarenMarsaglia(SecureLCG(), SecureLCG()))
        for (i in 1..1000) {
            println(p.next())
        }
        assert(true)
    }
}