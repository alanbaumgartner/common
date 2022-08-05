package dev.narcos.plugins.common.api

import net.runelite.api.*
import net.unethicalite.api.game.Skills
import net.unethicalite.api.game.Vars
import net.unethicalite.api.quests.Quests
import net.unethicalite.api.widgets.Prayers

/**
 * Extension of [net.unethicalite.api.widgets.Prayers]
 */
object PrayersExt {

    val bestMeleePrayer: Set<Prayer>
        get() {
            val prayerLevel = Skills.getLevel(Skill.PRAYER)
            if (prayerLevel >= 70 && Quests.isFinished(Quest.KINGS_RANSOM)) {
                return setOf(Prayer.PIETY)
            } else if (prayerLevel >= 60 && Quests.isFinished(Quest.KINGS_RANSOM)) {
                return setOf(Prayer.CHIVALRY)
            } else {
                return setOf(Prayer.INCREDIBLE_REFLEXES, Prayer.ULTIMATE_STRENGTH)
            }
        }

    val bestMagicPrayer: Prayer
        get() {
            val prayerLevel = Skills.getLevel(Skill.PRAYER)
            if (prayerLevel >= 77 && Vars.getBit(Varbits.AUGURY_UNLOCKED) == 1) {
                return Prayer.AUGURY
            } else if (prayerLevel >= 45) {
                return Prayer.MYSTIC_MIGHT
            } else if (prayerLevel >= 27) {
                return Prayer.MYSTIC_LORE
            } else if (prayerLevel >= 9) {
                return Prayer.MYSTIC_WILL
            } else {
                return Prayer.MYSTIC_WILL
            }
        }

    val bestRangedPrayer: Prayer
        get() {
            val prayerLevel = Skills.getLevel(Skill.PRAYER)
            if (prayerLevel >= 74 && Vars.getBit(Varbits.RIGOUR_UNLOCKED) == 1) {
                return Prayer.RIGOUR
            } else if (prayerLevel >= 44) {
                return Prayer.EAGLE_EYE
            } else if (prayerLevel >= 26) {
                return Prayer.HAWK_EYE
            } else if (prayerLevel >= 8) {
                return Prayer.SHARP_EYE
            } else {
                return Prayer.SHARP_EYE
            }
        }

    fun flickPrayers(prayers: Iterable<Prayer>) = prayers.onEach {
        if (Prayers.isEnabled(it)) {
            Prayers.toggle(it)
        }
    }.forEach(Prayers::toggle)

//    fun getEnabledQuickPrayers(): Set<QuickPrayer> {
//        return QuickPrayer.values().filter {
//            isQuickPrayerEnabled(it)
//        }.toSet()
//    }
//
//    fun isQuickPrayerEnabled(prayer: QuickPrayer): Boolean = GameThread.invokeLater {
//        val bitValue = Vars.getBit(4102)
//        val enabledValue = 2.0.pow(prayer.index).toInt()
//        bitValue and enabledValue == enabledValue
//    }
//
//    fun toggleQuickPrayer(prayer: QuickPrayer) {
//        setupQuickPrayers(getEnabledQuickPrayers().plus(prayer))
//    }
//
//    fun toggleQuickPrayer() = GameThread.invoke {
////        Prayers.toggleQuickPrayer(false)
//        Widgets.get(WidgetInfo.MINIMAP_QUICK_PRAYER_ORB)?.interact(0)
////        WidgetPackets.queueWidgetAction1Packet(10485779, -1, -1)
//    }
//
//    fun flickQuickPrayers() {
//        toggleQuickPrayer()
//        toggleQuickPrayer()
//    }
//
//    fun setupQuickPrayers(prayers: Set<QuickPrayer>): Boolean {
//        val prayersToToggle = QuickPrayer.values().filter {
//            prayers.contains(it) != isQuickPrayerEnabled(it)
//        }
//
//        if (prayersToToggle.isNotEmpty()) {
//            if (!isQuickPrayerSetupOpen()) {
//                openQuickPrayerSetup()
//            }
//            val skip = mutableSetOf<QuickPrayer>()
//            for (value in QuickPrayer.values()) {
//                if (isQuickPrayerEnabled(value) != prayers.contains(value) && !skip.contains(value)) {
//                    if (prayers.contains(value)) {
//                        skip.addAll(QuickPrayer.disablesMap[value]!!)
//                    }
//                    val widget = Widgets.get(WidgetInfo.QUICK_PRAYER_PRAYERS) ?: continue
//                    val child = widget.getChild(value.index) ?: continue
//                    child.interact("Toggle")
//                }
//            }
//            return true
//        }
//        return false
//    }
//
//    fun isQuickPrayerSetupOpen(): Boolean {
//        return Widgets.get(WidgetInfo.QUICK_PRAYER_PRAYERS) != null
//    }
//
//    fun openQuickPrayerSetup() {
//        val widget = Widgets.get(WidgetInfo.MINIMAP_QUICK_PRAYER_ORB) ?: return
//        widget.interact("Setup")
//    }
//
//    fun closeQuickPrayerSetup() {
//        val widget = Widgets.get(77, 5) ?: return
//        widget.interact(0)
//    }
}


