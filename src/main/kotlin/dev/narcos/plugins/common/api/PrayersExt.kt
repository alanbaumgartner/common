package dev.narcos.plugins.common.api

import dev.narcos.plugins.common.model.QuickPrayer
import net.runelite.api.widgets.WidgetInfo
import net.unethicalite.api.game.GameThread
import net.unethicalite.api.game.Vars
import net.unethicalite.api.packets.WidgetPackets
import net.unethicalite.api.widgets.Widgets
import kotlin.math.pow

/**
 * Extension of [net.unethicalite.api.widgets.Prayers]
 */
object PrayersExt {
    fun getEnabledQuickPrayers(): Set<QuickPrayer> {
        return QuickPrayer.values().filter {
            isQuickPrayerEnabled(it)
        }.toSet()
    }

    fun isQuickPrayerEnabled(prayer: QuickPrayer): Boolean = GameThread.invokeLater {
        val bitValue = Vars.getBit(4102)
        val enabledValue = 2.0.pow(prayer.index).toInt()
        bitValue and enabledValue == enabledValue
    }

    fun toggleQuickPrayer(prayer: QuickPrayer) {
        setupQuickPrayers(getEnabledQuickPrayers().plus(prayer))
    }

    fun toggleQuickPrayer() = GameThread.invoke {
        WidgetPackets.queueWidgetAction1Packet(10485779, -1, -1)
    }

    fun flickQuickPrayers() {
        toggleQuickPrayer()
        toggleQuickPrayer()
    }

    fun setupQuickPrayers(prayers: Set<QuickPrayer>) {
        val prayersToToggle = GameThread.invokeLater {
            QuickPrayer.values().filter {
                prayers.contains(it) != isQuickPrayerEnabled(it)
            }
        }
        if (prayersToToggle.isNotEmpty()) {
            if (!isQuickPrayerSetupOpen()) {
                openQuickPrayerSetup()
                return
            }
            prayersToToggle.forEach {
                val widget = Widgets.get(WidgetInfo.QUICK_PRAYER_PRAYERS) ?: return
                val child = widget.getChild(it.index) ?: return
                child.interact("Toggle")
            }
            closeQuickPrayerSetup()
        }
    }

    fun isQuickPrayerSetupOpen(): Boolean {
        return Widgets.get(WidgetInfo.QUICK_PRAYER_PRAYERS) != null
    }

    fun openQuickPrayerSetup() {
        val widget = Widgets.get(WidgetInfo.MINIMAP_QUICK_PRAYER_ORB) ?: return
        widget.interact("Setup")
    }

    fun closeQuickPrayerSetup() {
        val widget = Widgets.get(77, 5) ?: return
        widget.interact(0)
    }
}


