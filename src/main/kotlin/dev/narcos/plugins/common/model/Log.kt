package dev.narcos.plugins.common.model

import net.runelite.api.ItemID

enum class Log(val logId: Int, val treeName: String) {
    REGULAR(ItemID.LOGS, "Tree"),
    OAK(ItemID.OAK_LOGS, "Oak"),
    WILLOW(ItemID.WILLOW_LOGS, "Willow"),
    TEAK(ItemID.TEAK_LOGS, "Teak"),
    MAPLE(ItemID.MAPLE_LOGS, "Maple"),
    YEW(ItemID.YEW_LOGS, "Yew"),
    MAGIC(ItemID.MAGIC_LOGS, "Magic"),
    REDWOOD(ItemID.REDWOOD_LOGS, "Redwood"),
    ;
}
