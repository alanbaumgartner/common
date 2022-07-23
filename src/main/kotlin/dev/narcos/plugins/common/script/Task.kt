package dev.narcos.plugins.common.script

import org.slf4j.Logger
import org.slf4j.LoggerFactory

abstract class Task {
    protected val log: Logger = LoggerFactory.getLogger(this::class.java)
    open fun reset() {}
    abstract fun validate(): Boolean
    abstract fun execute()
}