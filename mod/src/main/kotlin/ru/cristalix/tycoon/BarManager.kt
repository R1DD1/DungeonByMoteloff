import dev.xdark.clientapi.event.entity.PlayerSprint
import dev.xdark.clientapi.event.render.*
import ru.cristalix.clientapi.registerHandler
import ru.cristalix.uiengine.UIEngine
import ru.cristalix.uiengine.UIEngine.clientApi
import ru.cristalix.uiengine.element.CarvedRectangle
import ru.cristalix.uiengine.element.RectangleElement
import ru.cristalix.uiengine.element.TextElement
import ru.cristalix.uiengine.eventloop.animate
import ru.cristalix.uiengine.utility.*
import kotlin.math.min

class BarManager {

    private var healthIndicator: HealthIndicator? = null


    private var health = 0


    init {

        registerHandler<HealthRender> { isCancelled = true }
        registerHandler<ExpBarRender> { isCancelled = true }
        registerHandler<HungerRender> { isCancelled = true }
        registerHandler<ArmorRender> { isCancelled = true }
        registerHandler<AirBarRender> { isCancelled = true }
        registerHandler<VehicleHealthRender> { isCancelled = true }

        registerHandler<RenderTickPre> {

            val healthLevel = clientApi.minecraft().player.health.toInt()
            val maxHealth = clientApi.minecraft().player.maxHealth.toInt()


            health = healthLevel
            healthIndicator?.updatePercentage(healthLevel, maxHealth)

        }

        mod.registerChannel("enable-bars") {
            val status = readBoolean()
            healthIndicator!!.enabled = status

        }

        mod.registerChannel("update-healthbar") {
            val healthLevel = readInt()
            val maxHealth = readInt()
            health = healthLevel
            healthIndicator?.updatePercentage(healthLevel, maxHealth)
        }

        display()
    }


    private fun display() {
        healthIndicator = HealthIndicator()


        healthIndicator!!.bar.color = Color(250, 0, 0)


        val parent = rectangle {
            origin = Relative.BOTTOM
            align = Relative.BOTTOM
            offset.y = -14.0
        }
        parent.addChild(healthIndicator!!)

        UIEngine.overlayContext.addChild(parent)
    }

}

class HealthIndicator : CarvedRectangle() {

    var bar: RectangleElement
    private val text: TextElement = text {
        origin = Relative.CENTER
        align = Relative.CENTER
        offset.x = 4.0
    }

    var maxX: Double

    init {
        color = Color(0, 0, 0, 0.68)
        offset = V3(100.0, -15.0)
        align = Relative.CENTER
        origin = Relative.RIGHT
        size = V3(200.0, 10.0)

        val parentSize = size

        bar = carved {
            color = WHITE
            size = parentSize
            carveSize = 1.0
        }
        this.maxX = bar.size.x

        addChild(bar, text)
    }

    fun updatePercentage(current: Int, max: Int) {
        bar.animate(0.1, Easings.CUBIC_OUT) {
            bar.size.x = maxX * min(1.0, current / max.toDouble())
        }
        this.text.content = "§f$current HP"
    }
}