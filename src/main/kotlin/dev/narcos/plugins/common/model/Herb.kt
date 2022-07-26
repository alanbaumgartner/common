package dev.narcos.plugins.common.model

import net.runelite.api.ItemID

enum class Herb(val cleanId: Int, val grimyId: Int, val herbSeedId: Int) {
    GUAM(ItemID.GUAM_LEAF, ItemID.GRIMY_GUAM_LEAF, ItemID.GUAM_SEED),
    MARRENTILL(ItemID.MARRENTILL, ItemID.GRIMY_MARRENTILL, ItemID.MARRENTILL_SEED),
    TARROMIN(ItemID.TARROMIN, ItemID.GRIMY_TARROMIN, ItemID.TARROMIN_SEED),
    HARRALANDER(ItemID.HARRALANDER, ItemID.GRIMY_HARRALANDER, ItemID.HARRALANDER_SEED),
    RANARR(ItemID.RANARR_WEED, ItemID.GRIMY_RANARR_WEED, ItemID.RANARR_SEED),
    TOADFLAX(ItemID.TOADFLAX, ItemID.GRIMY_TOADFLAX, ItemID.TOADFLAX_SEED),
    IRIT(ItemID.IRIT_LEAF, ItemID.GRIMY_IRIT_LEAF, ItemID.IRIT_SEED),
    AVANTOE(ItemID.AVANTOE, ItemID.GRIMY_AVANTOE, ItemID.AVANTOE_SEED),
    KWUARM(ItemID.KWUARM, ItemID.GRIMY_KWUARM, ItemID.KWUARM_SEED),
    CADANTINE(ItemID.CADANTINE, ItemID.GRIMY_CADANTINE, ItemID.CADANTINE_SEED),
    DWARF_WEED(ItemID.DWARF_WEED, ItemID.GRIMY_DWARF_WEED, ItemID.DWARF_WEED_SEED),
    TORSTOL(ItemID.TORSTOL, ItemID.GRIMY_TORSTOL, ItemID.TORSTOL_SEED);
}