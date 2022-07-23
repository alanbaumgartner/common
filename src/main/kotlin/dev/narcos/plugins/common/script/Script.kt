package dev.narcos.plugins.common.script

import net.runelite.api.events.GameTick
import net.runelite.client.eventbus.Subscribe
import net.runelite.client.plugins.Plugin
import net.runelite.client.task.Schedule
import net.unethicalite.api.plugins.Plugins
import net.unethicalite.client.Static
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.time.Duration
import java.time.Instant
import java.time.temporal.ChronoUnit
import java.util.concurrent.CompletableFuture
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.Future

abstract class Script : Plugin() {

    protected val log: Logger = LoggerFactory.getLogger(this::class.java)

    private var startTime: Instant = Instant.now()

    private val runtime: Duration get() = Duration.between(startTime, Instant.now())

    private val executor: ExecutorService = Executors.newSingleThreadExecutor()
    private var future: Future<*> = CompletableFuture.completedFuture(Unit)

    private var lastGameTick: Instant = Instant.EPOCH

    override fun startUp() {
        startTime = Instant.now()
        reset()
        onStart()
        log.info("${this::class.simpleName} started at $startTime")
    }

    override fun shutDown() {
        reset()
        onStop()
        log.info("${this::class.simpleName} stopped at ${Instant.now()} with runtime $runtime")
    }

    protected open fun reset() {

    }

    protected open fun onStart() {

    }

    protected open fun onStop() {

    }

    protected abstract fun loop()

    fun stop() {
        Plugins.stopPlugin(this)
    }

    @Subscribe
    fun gt(event: GameTick) {
        lastGameTick = Instant.now()
        Static.getEventBus().post(ScriptTick())
    }

    @Schedule(period = 600, unit = ChronoUnit.MILLIS)
    fun onBackupGameTick() {
        if (lastGameTick.isBefore(Instant.now().minusSeconds(1))) {
            log.debug("Backup GameTick")
            Static.getEventBus().post(ScriptTick(true))
        }
    }

    @Subscribe
    fun onScriptTick(event: ScriptTick) {
        if (future.isDone || future.isCancelled) {
            future = executor.submit {
                loop()
            }
        }
    }

}