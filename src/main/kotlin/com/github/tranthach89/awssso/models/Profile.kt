package com.github.tranthach89.awssso.models

import com.intellij.openapi.components.*
import com.intellij.openapi.project.Project
import com.intellij.util.xmlb.XmlSerializerUtil
import com.intellij.openapi.components.ServiceManager
import com.intellij.openapi.components.State
import com.intellij.openapi.components.Storage
import com.intellij.openapi.components.PersistentStateComponent

//@State(name = "LimitedWIPPluginSettings", storages = [Storage(value = "limited-wip.xml")])
@State(name = "Profile", storages = [Storage(value = "aws-sso-profile.xml")])
data class Profile(
    var profileName: String = ""
): PersistentStateComponent<Profile> {
    override fun getState() = this

    @Suppress("DEPRECATION")
    override fun loadState(state: Profile) {
        println("=====  loadState   =====")
        println(state)
        XmlSerializerUtil.copyBean(state, this)
    }

    companion object {
        fun getInstance(project: Project): Profile {
            val settings = ServiceManager.getService(project, Profile::class.java)
            return settings
        }
//        val instance: Profile get() = service()
    }
}
