package com.github.tranthach89.awssso.actions.profile

import com.github.tranthach89.awssso.helper.notify
import com.github.tranthach89.awssso.models.Profile
import com.intellij.openapi.actionSystem.*
import com.intellij.openapi.project.DumbAware
import com.intellij.openapi.project.Project
import com.intellij.openapi.wm.RegisterToolWindowTask
import com.intellij.openapi.wm.ToolWindowManager
import com.github.tranthach89.awssso.services.PluginToolWindow

class ShowProfileAction(private val profile: Profile): AnAction(), DumbAware {
    init {
        templatePresentation.text = profile.profileName
    }

    override fun actionPerformed(event: AnActionEvent) {
        val project = event.getRequiredData(CommonDataKeys.PROJECT)
        val toolWindowManager = ToolWindowManager.getInstance(project)
        var toolWindow = toolWindowManager.getToolWindow(ID)

        if (toolWindow == null) {
            toolWindow = toolWindowManager.registerToolWindow(
                    RegisterToolWindowTask(
                            id = ID,
                            component = null,
                            canCloseContent = true,
                    ))
            toolWindow.setToHideOnEmptyContent(true)
        }

        val contentManager = toolWindow.contentManager
        val content = toolWindow.contentManager.getContent(0)

        if (content != null) {
            contentManager.removeContent(content, true)
        }

        val profile1 = Profile.getInstance(project)
//        val profile2 = profile1.profileName.add(Profile(profile.profileName))
        val profile2 = Profile(profile1.profileName + "," + profile.profileName)
        println("Save ===============")
        profile1.loadState(profile2)
        notify("Add Success")
        toolWindow.contentManager.addContent(PluginToolWindow(project).createContent())
//        notify(profile1)
//        notify(profile2)
    }

    override fun update(event: AnActionEvent) {
        // Todo check profile is add
//        notify("update")
//        notify(profile.profileName)
//        val alreadyAdded = livePluginsById().containsKey(profile.pluginId)
//        event.presentation.isEnabled = !alreadyAdded
        val currentProject: Project? = event.project
        event.presentation.isEnabledAndVisible = currentProject != null
    }

    companion object {
        const val ID = "aws-sso"
    }
}
