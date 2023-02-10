package com.github.tranthach89.awssso.helper

import com.intellij.openapi.util.IconLoader
import com.intellij.ui.IconManager
import javax.swing.Icon

public class CustomIcon {
    fun load(path: String, cacheKey: Long, flags: Int): Icon {
        return IconManager.getInstance().loadRasterizedIcon(path, CustomIcon::class.java.classLoader, cacheKey, flags)
    }

    //Todo path đang sai
    fun donate(): Icon {
        return load("images/donate.svg", 7984914808516043214L, 2)
    }
}