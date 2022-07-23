package dev.narcos.plugins.common.script

import net.unethicalite.client.Static

abstract class TaskScript : Script() {

    protected abstract val tasks: List<Task>

    override fun startUp() {
        super.startUp()
        tasks.forEach {
            it.reset()
            Static.getEventBus().register(it)
        }
    }

    override fun shutDown() {
        super.shutDown()
        tasks.forEach {
            it.reset()
            Static.getEventBus().unregister(it)
        }
    }

    /**
     * Executes each task that has been validated with an early exit
     * case when a task isn't completed successfully.
     */
    override fun loop() {
        tasks.forEach {
            if (it.validate()) {
                log.debug("Current Task: ${it::class.java.simpleName}")
                it.execute()
            }
        }
    }

}