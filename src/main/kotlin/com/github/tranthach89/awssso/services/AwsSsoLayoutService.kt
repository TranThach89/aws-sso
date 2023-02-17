package com.github.tranthach89.awssso.services

import com.github.tranthach89.awssso.actions.AddProfileAction
import com.github.tranthach89.awssso.actions.BuyMeCoffee
import com.github.tranthach89.awssso.actions.ShowHelpAction
import com.github.tranthach89.awssso.helper.Icons.addPluginIcon
import com.github.tranthach89.awssso.helper.notify
import com.github.tranthach89.awssso.helper.shellCommand
import com.github.tranthach89.awssso.models.Profile
import com.intellij.openapi.actionSystem.ActionManager
import com.intellij.openapi.actionSystem.ActionPlaces
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.DefaultActionGroup
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.project.DumbAware
import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.SimpleToolWindowPanel
import com.intellij.openapi.wm.ToolWindow
import com.intellij.openapi.wm.ToolWindowFactory
import com.intellij.ui.ScrollPaneFactory
import com.intellij.ui.content.Content
import com.intellij.ui.content.ContentFactory
import com.intellij.ui.dsl.builder.panel
import java.awt.GridLayout
import javax.swing.Icon
import com.github.tranthach89.awssso.models.awsCli
import javax.swing.JComponent
import javax.swing.JPanel

class AwsSsoLayoutService: ToolWindowFactory, DumbAware {
    override fun createToolWindowContent(project: Project, toolWindow: ToolWindow) {
        toolWindow.contentManager.addContent(PluginToolWindow(project).createContent())
    }
}

class PluginToolWindow(project: Project) {
    private val myTree = createManageScreen(project)

    fun createContent(): Content {
        val panel = SimpleToolWindowPanel(true).also {
            it.add(ScrollPaneFactory.createScrollPane(myTree))
            it.toolbar = createToolBar()
        }

        return ApplicationManager.getApplication().getService(ContentFactory::class.java)
                .createContent(panel, "manager", false)
    }

    private fun createToolBar(): JComponent {
        fun AnAction.with(icon: Icon) = also { it.templatePresentation.icon = icon }

        val actionGroup = DefaultActionGroup().apply {
            add(DefaultActionGroup("Add", true).apply {
                add(AddProfileAction())
            }.with(addPluginIcon))
            add(ShowHelpAction())
            add(BuyMeCoffee())
        }

        return JPanel(GridLayout()).also {
            // this is a "hack" to force drop-down box appear below button
            // (see com.intellij.openapi.actionSystem.ActionPlaces#isToolbarPlace implementation for details)
            val place = ActionPlaces.EDITOR_TOOLBAR
            val toolbar = ActionManager.getInstance().createActionToolbar(place, actionGroup, true)
            // Set target component to avoid this error:
            // 'EditorToolbar' toolbar by default uses any focused component to update its actions. Toolbar actions that need local UI context would be incorrectly disabled. Please call toolbar.setTargetComponent() explicitly. java.lang.Throwable: toolbar creation trace
            toolbar.targetComponent = it
            it.add(toolbar.component)
        }
    }

    private fun createManageScreen(project: Project): JPanel {
        val listProfile = Profile.getInstance(project)
        println("============   createManageScreen  ============")

        if (listProfile.profileName.isEmpty()) {
            val myTree = panel { row { label("No Profile. Please add") } }
            return myTree
        } else {
            val myTree = panel {
                listProfile.profileName.split(",").forEach {
                    row {
                        button(it) {
                            notify(it.toString())
                            println(it)
                            shellCommand(awsCli(), "sso", "login", "--profile", it.actionCommand)
                        }
                    }
                }
            }
            return myTree
        }
    }
}

class AwsCliNotPound {

}