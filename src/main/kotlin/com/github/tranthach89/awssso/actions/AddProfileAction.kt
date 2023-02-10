package com.github.tranthach89.awssso.actions

import com.intellij.openapi.actionSystem.DefaultActionGroup
import com.github.tranthach89.awssso.actions.profile.ListProfileAction
import com.github.tranthach89.awssso.actions.profile.ShowProfileAction

class AddProfileAction: DefaultActionGroup("Add profile", true) {
    init {
        ListProfileAction().forEach {
            add(ShowProfileAction(it))
        }
        addSeparator()
    }
}