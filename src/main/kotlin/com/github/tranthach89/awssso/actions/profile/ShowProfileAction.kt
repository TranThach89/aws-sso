package com.github.tranthach89.awssso.actions.profile

import com.github.tranthach89.awssso.helper.notify
import com.github.tranthach89.awssso.models.Profile
import com.intellij.execution.ui.ExecutionConsole
import com.intellij.openapi.actionSystem.ActionGroup
import com.intellij.openapi.actionSystem.ActionManager
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.project.DumbAware
import com.intellij.ui.dsl.builder.panel
import java.awt.BorderLayout
import javax.swing.JPanel

class ShowProfileAction(private val profile: Profile): AnAction(), DumbAware {
    init {
        templatePresentation.text = profile.profileName
    }

    override fun actionPerformed(event: AnActionEvent) {
//        val project = event.project
        notify("Hello...")

        panel {
            row("Row1 label:") {
                textField()
                label("Some text")
            }

            row("Row2:") {
                label("This text is aligned with previous row")
            }
        }
    }

    override fun update(event: AnActionEvent) {
//        val alreadyAdded = livePluginsById().containsKey(profile.pluginId)
//        event.presentation.isEnabled = !alreadyAdded
    }
}

private class MyConsolePanel(consoleView: ExecutionConsole, toolbarActions: ActionGroup) : JPanel(BorderLayout()) {
    init {
        val toolbarPanel = JPanel(BorderLayout()).also {
            val actionToolbar = ActionManager.getInstance().createActionToolbar("aws sso", toolbarActions, false)
            actionToolbar.targetComponent = this
            it.add(actionToolbar.component)
        }
        add(toolbarPanel, BorderLayout.WEST)
        add(consoleView.component, BorderLayout.CENTER)
    }
}