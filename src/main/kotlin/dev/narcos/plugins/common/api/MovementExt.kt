package dev.narcos.plugins.common.api

import net.runelite.api.NPC
import net.runelite.api.TileObject
import net.unethicalite.api.SceneEntity
import net.unethicalite.api.entities.NPCs
import net.unethicalite.api.entities.Players
import net.unethicalite.api.entities.TileObjects
import net.unethicalite.api.items.Bank
import net.unethicalite.api.movement.Movement
import net.unethicalite.api.movement.Reachable
import net.unethicalite.api.movement.pathfinder.model.BankLocation
import java.util.function.Predicate

object MovementExt {

    private val bankPredicate: Predicate<SceneEntity> =
        Predicate { it.hasAction("Bank") && Reachable.isInteractable(it) }

    fun getBankEntity(): SceneEntity? {
        val entities = listOf(
            NPCs.getAll { it.hasAction("Bank") && Reachable.isInteractable(it) },
            TileObjects.getAll { it.hasAction("Bank") && Reachable.isInteractable(it) },
            TileObjects.getAll {
                it.name != null && it.name.contains(
                    "bank",
                    true
                ) && Reachable.isInteractable(it) && it.hasAction("Use")
            }
        )
        return entities.flatten().minByOrNull { it.distanceTo(Players.getLocal().worldLocation) }
    }

    fun getBankEntity(bankLocation: BankLocation): SceneEntity? {
        val entities = listOf(
            NPCs.getAll(bankPredicate.and { bankLocation.area.contains(it) } as Predicate<NPC>),
            TileObjects.getAll(bankPredicate.and { bankLocation.area.contains(it) } as Predicate<TileObject>),
            TileObjects.getAll {
                it.name != null && it.name.contains(
                    "bank",
                    true
                ) && bankLocation.area.contains(it) && Reachable.isInteractable(it) && it.hasAction("Use")
            }
        )
        return entities.flatten().minByOrNull { it.distanceTo(Players.getLocal().worldLocation) }
    }

    fun canOpenBank(): Boolean {
        return getBankEntity() != null
    }

    fun openBank(bankLocation: BankLocation = BankLocation.getNearestPath()) {
        if (Movement.isWalking()) {
            return
        }

        if (!Bank.isOpen()) {
            if (canOpenBank()) {
                if (Players.getLocal().isMoving) {
                    return
                }
                getBankEntity()?.let {
                    if (it.hasAction("Bank")) {
                        it.interact("Bank")
                    } else {
                        it.interact("Use")
                    }
                }
            } else {
                Movement.walkTo(bankLocation)
            }
        }
    }
}