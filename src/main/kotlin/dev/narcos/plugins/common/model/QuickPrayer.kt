//package dev.narcos.plugins.common.model
//
//enum class QuickPrayer(
//    val index: Int
//) {
//    THICK_SKIN(0),
//    BURST_OF_STRENGTH(1),
//    CLARITY_OF_THOUGHT(2),
//    SHARP_EYE(18),
//    MYSTIC_WILL(19),
//    ROCK_SKIN(3),
//    SUPERHUMAN_STRENGTH(4),
//    IMPROVED_REFLEXES(5),
//    RAPID_RESTORE(6),
//    RAPID_HEAL(7),
//    PROTECT_ITEM(8),
//    HAWK_EYE(20),
//    MYSTIC_LORE(21),
//    STEEL_SKIN(9),
//    ULTIMATE_STRENGTH(10),
//    INCREDIBLE_REFLEXES(11),
//    PROTECT_FROM_MAGIC(12),
//    PROTECT_FROM_MISSILES(13),
//    PROTECT_FROM_MELEE(14),
//    EAGLE_EYE(22),
//    MYSTIC_MIGHT(23),
//    RETRIBUTION(15),
//    REDEMPTION(16),
//    SMITE(17),
//    PRESERVE(28),
//    CHIVALRY(25),
//    PIETY(26),
//    RIGOUR(24),
//    AUGURY(27),
//    ;
//
//    companion object {
//        val overheads = listOf(
//            PROTECT_FROM_MAGIC,
//            PROTECT_FROM_MISSILES,
//            PROTECT_FROM_MELEE,
//            RETRIBUTION,
//            REDEMPTION,
//            SMITE,
//        )
//
//        val defenseBuffs = listOf(
//            THICK_SKIN,
//            ROCK_SKIN,
//            STEEL_SKIN,
//            CHIVALRY,
//            PIETY,
//            RIGOUR,
//            AUGURY,
//        )
//
//        val buffs = listOf(
//            THICK_SKIN,
//            BURST_OF_STRENGTH,
//            CLARITY_OF_THOUGHT,
//            SHARP_EYE,
//            MYSTIC_WILL,
//            ROCK_SKIN,
//            SUPERHUMAN_STRENGTH,
//            IMPROVED_REFLEXES,
//            HAWK_EYE,
//            MYSTIC_LORE,
//            STEEL_SKIN,
//            ULTIMATE_STRENGTH,
//            INCREDIBLE_REFLEXES,
//            EAGLE_EYE,
//            MYSTIC_MIGHT,
//            CHIVALRY,
//            PIETY,
//            RIGOUR,
//            AUGURY,
//        )
//
//        val disablesMap: Map<QuickPrayer, List<QuickPrayer>> = mapOf(
//            THICK_SKIN to defenseBuffs.minus(THICK_SKIN),
//            ROCK_SKIN to defenseBuffs.minus(ROCK_SKIN),
//            STEEL_SKIN to defenseBuffs.minus(STEEL_SKIN),
//
//            BURST_OF_STRENGTH to buffs.minus(listOf(BURST_OF_STRENGTH, CLARITY_OF_THOUGHT, IMPROVED_REFLEXES, INCREDIBLE_REFLEXES)),
//            SUPERHUMAN_STRENGTH to buffs.minus(listOf(SUPERHUMAN_STRENGTH, CLARITY_OF_THOUGHT, IMPROVED_REFLEXES, INCREDIBLE_REFLEXES)),
//            ULTIMATE_STRENGTH to buffs.minus(listOf(ULTIMATE_STRENGTH, CLARITY_OF_THOUGHT, IMPROVED_REFLEXES, INCREDIBLE_REFLEXES)),
//
//            CLARITY_OF_THOUGHT to buffs.minus(listOf(CLARITY_OF_THOUGHT, BURST_OF_STRENGTH, SUPERHUMAN_STRENGTH, ULTIMATE_STRENGTH)),
//            IMPROVED_REFLEXES to buffs.minus(listOf(IMPROVED_REFLEXES, BURST_OF_STRENGTH, SUPERHUMAN_STRENGTH, ULTIMATE_STRENGTH)),
//            INCREDIBLE_REFLEXES to buffs.minus(listOf(INCREDIBLE_REFLEXES, BURST_OF_STRENGTH, SUPERHUMAN_STRENGTH, ULTIMATE_STRENGTH)),
//
//            MYSTIC_WILL to buffs.minus(MYSTIC_WILL),
//            MYSTIC_LORE to buffs.minus(MYSTIC_LORE),
//            MYSTIC_MIGHT to buffs.minus(MYSTIC_MIGHT),
//
//            SHARP_EYE to buffs.minus(SHARP_EYE),
//            HAWK_EYE to buffs.minus(HAWK_EYE),
//            EAGLE_EYE to buffs.minus(EAGLE_EYE),
//
//            RAPID_RESTORE to listOf(),
//            RAPID_HEAL to listOf(),
//            PROTECT_ITEM to listOf(),
//
//            PROTECT_FROM_MAGIC to overheads.minus(PROTECT_FROM_MAGIC),
//            PROTECT_FROM_MISSILES to overheads.minus(PROTECT_FROM_MISSILES),
//            PROTECT_FROM_MELEE to overheads.minus(PROTECT_FROM_MELEE),
//            RETRIBUTION to overheads.minus(RETRIBUTION),
//            REDEMPTION to overheads.minus(REDEMPTION),
//            SMITE to overheads.minus(SMITE),
//
//            PRESERVE to listOf(),
//
//            CHIVALRY to buffs.minus(CHIVALRY),
//            PIETY to buffs.minus(PIETY),
//            RIGOUR to buffs.minus(RIGOUR),
//            AUGURY to buffs.minus(AUGURY),
//        )
//
//    }
//
//}