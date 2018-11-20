package com.uonagent.iism.random.baserandom

import java.security.SecureRandom

class SecureLCG : BaseRandom {

    private val generator = SecureRandom()

    override fun next() = generator.nextDouble()
}