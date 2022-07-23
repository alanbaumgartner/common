package dev.narcos.plugins.common.api

import net.runelite.api.widgets.Widget
import net.unethicalite.client.Static

/**
 * Extension of [net.unethicalite.api.widgets.Widgets]
 */
object WidgetsExt {

    fun Widget.childWithText(text: String): Widget? {
        val childWidgets = children ?: return null
        return childWidgets.firstOrNull { it.text != null && it.text.contains(text, true) }
    }

    fun Widget.childWithId(id: Int): Widget? {
        val childWidgets = children ?: return null
        return childWidgets.firstOrNull { it.id == id }
    }

    fun withText(text: String): Widget? {
        return all().firstOrNull { it.text != null && it.text.contains(text, true) }
    }

    fun withId(id: Int): Widget? {
        return all().firstOrNull { it.id == id }
    }

    fun all(): List<Widget> {
        val widgets = Static.getClient().widgets?.filterNotNull() ?: return emptyList()
        return widgets.flatMap { it.asList<Widget?>() }.filterNotNull()
    }
}
