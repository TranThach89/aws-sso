package com.github.tranthach89.awssso.models

import com.intellij.openapi.diagnostic.Logger
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.openapi.ui.DialogPanel
import com.intellij.ui.dsl.builder.panel
import com.github.tranthach89.awssso.helper.notify

data class Profile(val profileName: String) {
//    fun addProfile(
//        handleError: (e: Exception, pluginPath: String) -> Unit = { e, pluginPath -> logger.warn("Failed to install plugin: $pluginPath", e) },
//        whenCreated: (mainPanel) -> Unit = {}
//    ) {
//        val mainPanel = panel {
//            row {
//                button("Like") {
//                    notify("Cám ơn bạn đã Like")
//                }
//            }
//        }
//    }
}

val logger = Logger.getInstance(Profile::class.java)

//data class ExamplePlugin(val path: String, val pluginId: String, val filePaths: List<String>) {
//    constructor(path: String, pluginId: String, vararg filePaths: String): this(path, pluginId, filePaths.toList())
//    fun installPlugin(
//            handleError: (e: Exception, pluginPath: String) -> Unit = { e, pluginPath -> logger.warn("Failed to install plugin: $pluginPath", e) },
//            whenCreated: (VirtualFile) -> Unit = {}
//    ) {
//        filePaths.forEach { relativeFilePath ->
//            val resourceDirPath = "$path/$pluginId/"
//            try {
//                val text = readSampleScriptFile("$resourceDirPath/$relativeFilePath")
//                val (parentPath, fileName) = splitIntoPathAndFileName("$livePluginsPath/$pluginId/$relativeFilePath")
//                createFile(parentPath, fileName, text, whenCreated)
//            } catch (e: IOException) {
//                handleError(e, resourceDirPath)
//            }
//        }
//    }
//}