package com.uonagent.iism.math.chisquare

import java.io.Serializable
import java.lang.Math.pow
import kotlin.math.ln

private const val ILLEGAL_EPS = "Eps must be in range from 0.001 up to 0.999"
private const val ILLEGAL_K = "K must be greater than 0"

object ChiSquare : Serializable {
    private val map = hashMapOf<Int, HashMap<Double, Double>>()

    operator fun get(eps: Double, n: Int) = map[n]?.get(eps) ?: calculate(eps, n)

    private fun calculate(eps: Double, n: Int): Double {
        if (n < 1) {
            throw IllegalArgumentException(ILLEGAL_K)
        }
        val alpha = 1 - eps
        val d = if (alpha in 0.5..0.999) {
            2.0637 * pow((ln(1 / (1 - alpha)) - 0.16), 0.4274) - 1.5774
        } else if (alpha >= 0.001 && alpha < 0.5) {
            -2.0637 * pow((ln(1 / alpha) - 0.16), 0.4274) + 1.5774
        } else throw IllegalArgumentException(ILLEGAL_EPS)

        var s = 0.0

        for (i in 0..6) {
            s += pow(n.toDouble(), -i.toDouble() / 2) * pow(d, i.toDouble()) *
                    (a[i] + b[i] / n + c[i] / (n * n))
        }

        return n * pow(s, 3.0)
    }

    private val a = doubleArrayOf(
            1.0000886,
            0.4713941,
            0.0001348028,
            -0.008553069,
            0.00312558,
            -0.0008426812,
            0.00009780499
    )

    private val b = doubleArrayOf(
            -0.2237368,
            0.02607083,
            0.01128186,
            -0.01153761,
            0.005169654,
            0.00253001,
            -0.001450117
    )

    private val c = doubleArrayOf(
            -0.01513904,
            -0.008986007,
            0.02277679,
            -0.01323293,
            -0.006950356,
            0.001060438,
            0.001565326
    )

}

fun main(args: Array<String>) {
    println(ChiSquare[0.05, 5])
    println(ChiSquare[0.25, 4])
}