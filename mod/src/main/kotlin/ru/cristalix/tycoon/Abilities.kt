package ru.cristalix.tycoon


import mod
import ru.cristalix.clientapi.theMod
import ru.cristalix.uiengine.UIEngine
import ru.cristalix.uiengine.eventloop.animate
import ru.cristalix.uiengine.utility.*
import kotlin.math.min

class Abilities {
    private val reload: Boolean = false

    private val abilityF = text {
        content = "F"
        color = WHITE
        align = CENTER
        origin = CENTER
        shadow = true
        scale = V3(0.8, 0.8)
    }

    private val bgF = carved {
        color.alpha = 1.0
        align = BOTTOM
        origin = BOTTOM
        size = V3(19.0, 19.0)
        offset.y = -1.0
        offset.x += 105
        carveSize = 1.0
        color = Color(128, 166, 265, .9)
    }

    init {
        mod.registerChannel("unlock-abilities") {
            bgF.addChild(abilityF)
            UIEngine.overlayContext + bgF
        }


        mod.registerChannel("enable-abilities") {
            val status = readBoolean()
            bgF.enabled = status

        }

        mod.registerChannel("reload-start") {
            reloadStart()
        }

        mod.registerChannel("reload-end") {
            reloadEnd()
        }

    }

    fun reloadStart() {
        bgF.color = BLACK
    }

    fun reloadEnd() {
        bgF.animate(1, Easings.QUART_IN) {
            bgF.color = Color(128, 166, 265, .9)
        }
    }
}