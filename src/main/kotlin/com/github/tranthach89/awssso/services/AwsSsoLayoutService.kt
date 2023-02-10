package com.github.tranthach89.awssso.services

import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.wm.ToolWindowFactory
import com.intellij.openapi.project.Project
import com.intellij.openapi.wm.ToolWindow
import com.intellij.ui.ScrollPaneFactory
import com.intellij.ui.content.Content
import com.intellij.ui.content.ContentFactory
import com.intellij.openapi.project.DumbAware
import com.intellij.openapi.ui.SimpleToolWindowPanel
import java.awt.GridLayout
import javax.swing.JComponent
import javax.swing.JPanel
import com.intellij.openapi.actionSystem.*
import javax.swing.Icon
import com.github.tranthach89.awssso.actions.ShowHelpAction
import com.github.tranthach89.awssso.actions.BuyMeCoffee
import com.github.tranthach89.awssso.actions.AddProfileAction
import com.github.tranthach89.awssso.helper.Icons.addPluginIcon

class AwsSsoLayoutService: ToolWindowFactory, DumbAware {
    override fun createToolWindowContent(project: Project, toolWindow: ToolWindow) {
        toolWindow.contentManager.addContent(PluginToolWindow().createContent())
    }
}

private class PluginToolWindow() {
//    private val fileSystemTree = createManageScreen(project)
    fun createContent(): Content {
        val panel = SimpleToolWindowPanel(true).also {
            it.add(ScrollPaneFactory.createScrollPane())
            it.toolbar = createToolBar()
        }

        return ApplicationManager.getApplication().getService(ContentFactory::class.java)
                .createContent(panel, "", false)
    }

    private fun createToolBar(): JComponent {
        fun AnAction.with(icon: Icon) = also { it.templatePresentation.icon = icon }

        val actionGroup = DefaultActionGroup().apply {
            add(DefaultActionGroup("Add", true).apply {
//                add(CreateGroupAction())
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

//    private fun createManageScreen(project: Project): FileSystemTree {
//        val myTree = MyTree(project)
//        EditSourceOnDoubleClickHandler.install(myTree) // This handler only seems to work before creating FileSystemTreeImpl.
//
//        val fileSystemTree = FileSystemTreeImpl(project, createFileChooserDescriptor(), myTree, null, null, null)
//        Disposer.register(project, fileSystemTree)
//        fileSystemTree.tree.let {
//            EditSourceOnEnterKeyHandler.install(it) // This handler only seems to work after creating FileSystemTreeImpl.
//            it.installPopupMenu()
//        }
//        return fileSystemTree
//    }
}
