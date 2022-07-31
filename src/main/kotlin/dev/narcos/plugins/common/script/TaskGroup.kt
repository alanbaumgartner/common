package dev.narcos.plugins.common.script

import net.unethicalite.client.Static

abstract class TaskGroup : Task() {

    protected abstract fun getTasks(): List<Task>

    override fun reset() {
        super.reset()
        getTasks().forEach {
            it.reset()
        }
    }

    override fun onStart() {
        super.onStart()
        getTasks().forEach {
            Static.getEventBus().register(it)
        }
    }

    override fun onStop() {
        super.onStop()
        getTasks().forEach {
            Static.getEventBus().unregister(it)
        }
    }

    override fun execute() {
        getTasks().forEach {
            if (it.validate()) {
                log.debug("Current Task: ${it::class.java.simpleName}")
                it.execute()
            }
        }
    }
}