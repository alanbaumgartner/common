package dev.narcos.plugins.common.api

import net.runelite.api.widgets.Widget
import net.unethicalite.api.widgets.Widgets
import net.unethicalite.client.Static

fun Widgets.withText(text: String): Widget? {
    return all().firstOrNull { it.text != null && it.text.contains(text, true) }
}

fun Widgets.withId(id: Int): Widget? {
    return all().firstOrNull { it.id == id }
}

fun Widgets.all(): List<Widget> {
    val widgets = Static.getClient().widgets?.filterNotNull() ?: return emptyList()
    return widgets.flatMap { it.asList<Widget?>() }.filterNotNull()
}
