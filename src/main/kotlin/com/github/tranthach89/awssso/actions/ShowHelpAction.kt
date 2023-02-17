package com.github.tranthach89.awssso.actions

import com.github.tranthach89.awssso.helper.Icons.helpIcon
import com.intellij.ide.BrowserUtil
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.project.DumbAware

class ShowHelpAction: AnAction("Help", "Contact for me", helpIcon), DumbAware {
    override fun actionPerformed(e: AnActionEvent) =
            BrowserUtil.browse("https://www.facebook.com/tcthachss/")
}