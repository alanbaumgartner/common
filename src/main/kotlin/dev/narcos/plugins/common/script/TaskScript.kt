package dev.narcos.plugins.common.script

import net.unethicalite.client.Static

abstract class TaskScript : Script() {

    protected abstract fun getTasks(): List<Task>

    override fun startUp() {
        super.startUp()
        getTasks().forEach {
            it.reset()
            it.onStart()
            Static.getEventBus().register(it)
        }
    }

    override fun shutDown() {
        super.shutDown()
        getTasks().forEach {
            it.reset()
            it.onStop()
            Static.getEventBus().unregister(it)
        }
    }

    /**
     * Executes each task that has been validated with an early exit
     * case when a task isn't completed successfully.
     */
    override fun loop() {
        getTasks().forEach {
            if (it.validate()) {
                log.debug("Current Task: ${it::class.java.simpleName}")
                it.execute()
            }
        }
    }

}