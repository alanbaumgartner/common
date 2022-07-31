package dev.narcos.plugins.common.api

import net.runelite.api.DecorativeObject
import net.runelite.api.ItemID
import net.runelite.api.coords.WorldArea
import net.runelite.api.coords.WorldPoint
import net.unethicalite.api.entities.Players
import net.unethicalite.api.entities.TileItems
import net.unethicalite.api.entities.TileObjects
import net.unethicalite.api.items.Inventory
import net.unethicalite.api.movement.Movement
import net.unethicalite.api.movement.pathfinder.CollisionMap
import net.unethicalite.client.Static
import java.util.*

object Skilling {

    fun firemake(logId: Int) {
        if (Movement.isWalking()) {
            return
        }

        val currentPosition = Players.getLocal().worldLocation

        val tileObject = TileObjects.getFirstAt(currentPosition) {
            it.name != null && (it.name != "null" || it.name.lowercase(Locale.getDefault()).contains("fire"))
        }

        if (tileObject != null && Players.getLocal().isIdle) {
            val randPoint = getNextStartPoint()
            Movement.walk(randPoint)
            return
        }

        if (!Players.getLocal().isIdle) {
            return
        }

        val logs = Inventory.getFirst(logId) ?: TileItems.getFirstAt(currentPosition) { it.id == logId } ?: return
        val tinderBox = Inventory.getFirst(ItemID.TINDERBOX) ?: return
        tinderBox.useOn(logs)
    }

    private fun getNextStartPoint(): WorldPoint {
        val tileObjectLocations = TileObjects.getAll().filter {
            it !is DecorativeObject
        }.map { it.worldLocation }

        val cm = Static.getGlobalCollisionMap()

        return WorldArea(Players.getLocal().worldLocation.dx(-10).dy(-10), 20, 20)
            .toWorldPointList()
            .groupBy { it.y }
            .map { it.value }
            .map { u -> u.sortedBy { it.x } }
            .flatMap { partition(it, cm, tileObjectLocations) }
            .filter { it.isNotEmpty() }
            .maxBy { it.size }
            .last()
    }

    private fun partition(
        points: List<WorldPoint>,
        cm: CollisionMap,
        tileObjects: List<WorldPoint>,
    ): List<List<WorldPoint>> {
        var list = listOf(*points.toTypedArray())

        var nextIndex = list.indexOfFirst { tileObjects.contains(it) || cm.fullBlock(it) }

        if (nextIndex == -1) {
            return listOf(list)
        }

        val result = mutableListOf<List<WorldPoint>>()
        do {
            result.add(list.take(nextIndex))
            list = list.drop(nextIndex + 1)
            nextIndex = list.indexOfFirst { tileObjects.contains(it) || cm.fullBlock(it) }
        } while (nextIndex != -1)

        result.add(list)
        return result
    }

}