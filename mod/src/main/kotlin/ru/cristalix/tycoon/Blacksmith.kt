package ru.cristalix.tycoon

import dev.xdark.clientapi.resource.ResourceLocation
import mod
import ru.cristalix.clientapi.theMod
import ru.cristalix.uiengine.UIEngine
import ru.cristalix.uiengine.element.CarvedRectangle
import ru.cristalix.uiengine.utility.*

class Blacksmith {
    private val menu = BlacksmithMenu()

    private val title = text {
        content = "Кузница"
        align = TOP
        origin = TOP
        offset.y += 10
        offset.x += 3
    }

    private fun createItemButton(): CarvedRectangle {
        val button = carved {
            textureLocation = ResourceLocation.of("minecraft:textures/items/iron_sword.png")
            textureSize = V2(100.0, 100.0)
            align = CENTER
            origin = CENTER
            enabled = true
            size = V3(100.0, 150.0)
            offset.y = 0.0
            offset.x = 0.0
            onHover {
                color = BLACK
            }
        }
        return button
    }

    private fun display() {
        menu.addChild(title, createItemButton())
        UIEngine.overlayContext.addChild(menu)
    }

    init {
        mod.registerChannel("show-smith") {
            display()
        }
    }
}

class BlacksmithMenu : CarvedRectangle() {


    init {
        color.alpha = .7
        align = CENTER
        origin = CENTER
        enabled = true
        size = V3(500.0, 200.0)
        offset.y = 0.0
        offset.x = 0.0
        carveSize = 2.0
        color = Color(0, 0, 0, .3)
    }
}