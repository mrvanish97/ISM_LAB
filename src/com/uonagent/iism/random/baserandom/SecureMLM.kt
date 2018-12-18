package com.uonagent.iism.random.baserandom

class SecureMLM: BaseRandom {

    private val generator = MacLarenMarsaglia(SecureLCG(), SecureLCG())

    @Synchronized
    override fun next() = generator.next()
}