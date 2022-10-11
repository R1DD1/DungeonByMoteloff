package ru.cristalix.tycoon

import io.netty.buffer.Unpooled
import io.netty.util.NetUtil
import mod
import ru.cristalix.uiengine.UIEngine
import ru.cristalix.uiengine.element.CarvedRectangle
import ru.cristalix.uiengine.element.RectangleElement
import ru.cristalix.uiengine.element.TextElement
import ru.cristalix.uiengine.utility.*

class Preparing {
    var curY: Int = -55
    var preparingMenu: PreparingMenu = PreparingMenu()

//    private val firstPlayer = text {
//        content = "имя1"
//        color = WHITE
//        align = CENTER
//        origin = LEFT
//        shadow = true
//        offset.y += -40.0
//        offset.x += -40
//        scale = V3(1.2, 1.2)
//    }
//
//    private val secondPlayer = text {
//        content = "имя2"
//        color = WHITE
//        align = CENTER
//        origin = LEFT
//        shadow = true
//        offset.y += -15.0
//        offset.x += -40
//        scale = V3(1.2, 1.2)
//    }
//
//    private val thirdPlayer = text {
//        content = "имя3"
//        color = WHITE
//        align = CENTER
//        origin = LEFT
//        shadow = true
//        offset.x += -40
//        offset.y += 10.0
//        scale = V3(1.2, 1.2)
//    }
//
//    private val fourthPlayer = text {
//        content = "имя4"
//        color = WHITE
//        align = CENTER
//        origin = LEFT
//        shadow = true
//        offset.x += -40
//        offset.y += 35.0
//        scale = V3(1.2, 1.2)
//    }
//
//    private val fifthPlayer = text {
//        content = "имя5"
//        color = WHITE
//        align = CENTER
//        origin = LEFT
//        shadow = true
//        offset.x += -40
//        offset.y += 60.0
//        scale = V3(1.2, 1.2)
//    }

    private val leaveButton = carved {
        align = BOTTOM
        origin = BOTTOM
        size = V3(35.0, 10.0)
        carveSize = 1.0
        offset.y += -10
        offset.x += -20
        color = Color(250, 0, 0)
        onLeftClick {
            UIEngine.clientApi.clientConnection().sendPayload("btn:leave", Unpooled.EMPTY_BUFFER)
        }


    }
    private val leaveButtonText = text {
        content = "Покинуть"
        origin = CENTER
        align = CENTER
        shadow = true
        scale = V3(0.6, 0.6)

    }

    private val startButton = carved {
        align = BOTTOM
        origin = BOTTOM
        size = V3(35.0, 10.0)
        enabled = false
        carveSize = 1.0
        offset.y += -10
        offset.x += 30
        color = Color(10, 230, 10)
        onLeftClick {
            UIEngine.clientApi.clientConnection().sendPayload("btn:start", Unpooled.EMPTY_BUFFER)
        }


    }
    private val startButtonText = text {
        content = "Начать"
        origin = CENTER
        align = CENTER
        shadow = true
        scale = V3(0.6, 0.6)

    }

    private val title = text {
        content = "Подземелье"
        align = TOP
        origin = TOP
        offset.y += 10
        offset.x += 3
    }
    private val users = text {
        content = "Участники:"
        offset.x += -40
        offset.y += 35
        align = TOP
        origin = TOP_LEFT
        scale = V3(0.8, 0.8)
    }



    init {
        mod.registerChannel("show-preparing") {
            val status = readBoolean()
            preparingMenu.enabled = status
        }
//
        mod.registerChannel("show-start-btn") {
            val status = readBoolean()
            startButton.enabled = status
        }
//
        mod.registerChannel("user-connected") {
            val name = dev.xdark.feder.NetUtil.readUtf8(this)
            preparingMenu.addChild(addUser(name!!))
        }

        display()

    }

    private fun display() {

        val parent = rectangle {
            origin = Relative.CENTER
            align = Relative.LEFT
            offset.x += -5
        }

        startButton.addChild(startButtonText)
        leaveButton.addChild(leaveButtonText)
        preparingMenu.addChild(title, users, leaveButton, startButton)
        parent.addChild(preparingMenu)
        UIEngine.overlayContext.addChild(parent)
    }

    private fun addUser(name: String): TextElement {
        val text = text {
            content = name
            offset = V3(30.0, 0.0)
            offset.y += curY
            align = Relative.LEFT
            origin = Relative.TOP_LEFT
            scale = V3(1.0, 1.0)
        }
        curY += 15
        return text
    }
}

class PreparingMenu : CarvedRectangle() {
    init {
        color.alpha = 1.0
        align = LEFT
        origin = CENTER
        enabled = false
        size = V3(120.0, 200.0)
        offset.y = 0.0
        offset.x += 50
        carveSize = 2.0
        color = Color(0, 0, 0, .7)
    }
}