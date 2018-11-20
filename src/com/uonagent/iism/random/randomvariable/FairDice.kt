package com.uonagent.iism.random.randomvariable

import com.uonagent.iism.random.baserandom.MacLarenMarsaglia
import com.uonagent.iism.random.baserandom.SecureLCG

class FairDice : DiscreteUniform(0, 1, MacLarenMarsaglia(SecureLCG(), SecureLCG())) {
    override val distributionName: String
        get() = "\"Fair Dice\" ${super.distributionName}"
}