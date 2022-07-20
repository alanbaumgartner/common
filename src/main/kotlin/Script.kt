package dev.narcos.plugins.common

import net.runelite.api.events.GameTick
import net.runelite.client.eventbus.Subscribe
import net.runelite.client.plugins.Plugin
import net.unethicalite.api.plugins.Plugins
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.time.Duration
import java.time.Instant
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.Future

abstract class Script : Plugin() {

    protected val log: Logger = LoggerFactory.getLogger(this::class.java)

    private var startTime: Instant = Instant.now()

    private val runtime: Duration get() = Duration.between(startTime, Instant.now())

    private val executor: ExecutorService = Executors.newSingleThreadExecutor()
    private var future: Future<*>? = null

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

    @Subscribe
    fun gt(event: GameTick) {
        val shouldLoop = future?.let {
            it.isDone || it.isCancelled
        } ?: true

        if (shouldLoop) {
            future = executor.submit {
                loop()
            }
        }
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

}