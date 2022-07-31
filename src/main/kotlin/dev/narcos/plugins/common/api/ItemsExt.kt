package dev.narcos.plugins.common.api

import net.runelite.api.Item
import net.unethicalite.api.commons.Predicates
import net.unethicalite.api.commons.Time
import net.unethicalite.api.items.Bank
import net.unethicalite.api.items.Equipment
import net.unethicalite.api.items.Inventory
import java.util.function.Predicate

object ItemsExt {

    fun hasItem(id: Int): Boolean {
        return hasItem(Predicates.ids(id))
    }

    fun hasItem(predicate: Predicate<Item>): Boolean {
        return Inventory.contains(predicate) || Equipment.contains(predicate)
    }

    fun setupInventory(exclusions: List<Int> = listOf(), vararg items: Pair<Int, Int>) {
        Inventory.getAll { !items.map { item -> item.first }.contains(it.id) && !exclusions.contains(it.id) }
            .distinctBy { it.id }
            .forEach {
                Bank.depositAll(it.id)
            }

        withdrawInventory(*items)
        Time.sleepTicks((items.size / 10) + 1)
    }

    fun withdrawInventory(vararg items: Pair<Int, Int>) {
        items.forEach {
            val dif = it.second - Inventory.getCount(true, it.first) - Equipment.getCount(true, it.first)
            if (dif > 0) {
                Bank.withdraw(it.first, dif, Bank.WithdrawMode.ITEM)
                Time.sleep(10)
            }
            val item: Item? = Inventory.getFirst(it.first)
            if (dif < 0 && item != null && !item.isStackable) {
                Bank.deposit(item.id, dif)
            }
        }
    }

    fun containsAll(vararg items: Pair<Int, Int>): Boolean {
        return items.all { (key, _) -> Equipment.contains(key) || Inventory.contains(key) }
    }

}