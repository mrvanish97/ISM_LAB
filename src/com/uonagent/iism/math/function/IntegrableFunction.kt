package com.uonagent.iism.math.function

class IntegrableFunction(
        val f: (Double) -> (Double),
        val a: Int,
        val b: Int
): Function<Double, Double> {
    override fun get(x: Double): Double {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override val isInDefinitionArea: (x: Double) -> Boolean
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.
}