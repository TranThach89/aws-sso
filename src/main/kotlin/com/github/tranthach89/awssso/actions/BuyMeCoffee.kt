package com.github.tranthach89.awssso.actions

import com.github.tranthach89.awssso.helper.Icons.profileIcon
import com.intellij.ide.BrowserUtil
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.project.DumbAware

class BuyMeCoffee: AnAction("Buy Coffee", "Buy Coffee for me", profileIcon), DumbAware {
    override fun actionPerformed(e: AnActionEvent) = BrowserUtil.browse("https://www.buymeacoffee.com/")
}
