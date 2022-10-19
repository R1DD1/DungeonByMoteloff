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
    private var experienceIndicator: ExperienceIndicator? = null


    private var health = 0
    private var exp = 0.0F


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

            val level = clientApi.minecraft().player.experienceLevel
            val expLevel = clientApi.minecraft().player.experience

            exp = expLevel
            health = healthLevel
            experienceIndicator?.updatePercentage(expLevel, 1F, level)
            healthIndicator?.updatePercentage(healthLevel, maxHealth)

        }

        mod.registerChannel("enable-bars") {
            val status = readBoolean()
            healthIndicator!!.enabled = status
            experienceIndicator!!.enabled = status

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
        experienceIndicator = ExperienceIndicator()


        healthIndicator!!.bar.color = Color(250, 0, 0)
        experienceIndicator!!.bar.color = Color(10, 160, 10)


        val parent = rectangle {
            origin = Relative.BOTTOM
            align = Relative.BOTTOM
            offset.y = -14.0
        }
        parent.addChild(healthIndicator!!, experienceIndicator!!)

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
        offset = V3(-7.0, -15.0)
        align = Relative.CENTER
        origin = Relative.RIGHT
        size = V3(85.0, 10.0)

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

class ExperienceIndicator : CarvedRectangle() {
    var bar: RectangleElement
    private val text: TextElement = text {
        origin = Relative.CENTER
        align = Relative.CENTER
        offset.x = 4.0
    }

    var maxX: Double

    init {
        color = Color(0, 0, 0, 0.68)
        offset = V3(90.0, -15.0)
        align = Relative.CENTER
        origin = Relative.RIGHT
        size = V3(85.0, 10.0)

        val parentSize = size

        bar = carved {
            color = WHITE
            size = parentSize
            carveSize = 1.0
        }
        this.maxX = bar.size.x

        addChild(bar, text)
    }
    fun updatePercentage(current: Float, max: Float, level: Int) {
        bar.animate(0.1, Easings.CUBIC_OUT) {
            bar.size.x = maxX * current
        }
        var levelInString: String = level.toString()
        when (levelInString) {
            "0" -> levelInString = "F"
            "1" -> levelInString = "E"
            "2" -> levelInString = "D"
            "3" -> levelInString = "C"
            "4" -> levelInString = "B"
            "5" -> levelInString = "A"
            "6" -> levelInString = "S"
            "7" -> levelInString = "S+"
            "8" -> levelInString = "SSS"

        }
        this.text.content = "§f$levelInString "
    }
}