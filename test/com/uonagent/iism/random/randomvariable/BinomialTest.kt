package com.uonagent.iism.random.randomvariable

import com.uonagent.iism.random.baserandom.BaseRandom
import com.uonagent.iism.random.baserandom.MacLarenMarsaglia
import com.uonagent.iism.random.baserandom.SecureLCG
import org.junit.jupiter.api.Test
import kotlin.math.roundToInt

internal class BinomialTest {

    private val p = 0.6
    private val m = 5
    private val n = 1000

    @Test
    fun nextSecureMLM() {
        t(MacLarenMarsaglia(SecureLCG(), SecureLCG()))
        assert(true)
    }

    /*fun cumulative() {

        var s = -1.0
        val inc = 0.05
        while (s <= 67) {
            println(b.cumulative(s))
            s += inc
        }

        assert(true)
    }*/

    private fun t(genetator: BaseRandom) {
        val b = Binomial(m, p, genetator)
        val sample = statisticsSampleOf()
        var kek = false
        for (i in 1..n) {
            val s = b.next()
            println(s)
            sample.add(s)
            if (s.roundToInt() == 0) {
                kek = true
            }
        }
        println(sample.mean)
        println(sample.dispersion)
        //val k = KolmogorovTester(sample, Binomial)
    }

    @Test
    fun cumulative() {
        val b = Binomial(66, 0.5)
        val f = b::cumulative
        var x = -0.5
        val inc = 0.5
        while (x <= 70) {
            println("f($x) = ${f(x)}")
            x += inc
        }
        assert(true)
    }
}
