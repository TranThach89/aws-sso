package com.github.tranthach89.awssso.helper

import com.intellij.notification.NotificationGroupManager
import com.intellij.notification.NotificationType.ERROR
import com.intellij.openapi.diagnostic.Logger
import com.intellij.openapi.project.Project
import com.intellij.notification.Notification
import com.intellij.notification.Notifications
import com.intellij.notification.NotificationAction
import com.intellij.notification.NotificationType
import com.intellij.openapi.application.ApplicationManager

const val livePluginId = "AwsSso"

// Lazy because it seems that it can be initialised before notification group is initialised in plugin.xml
val awsssoNotificationGroup by lazy {
    NotificationGroupManager.getInstance().getNotificationGroup("aws sso")!!
}

object IdeUtil {
    val logger = Logger.getInstance(livePluginId)
    
    fun Project?.showError(message: String, e: Exception? = null) {
        awsssoNotificationGroup.createNotification(title = "Live plugin", message, ERROR).notify(this)
        if (e != null) logger.info(e) // Don't log it as an error because then IJ will show an additional window with stacktrace.
    }
}

fun notify(
        message: Any?,
        title: String = "",
        notificationType: NotificationType = NotificationType.INFORMATION,
        groupDisplayId: String = "aws sso"
) {
    val notification = Notification(groupDisplayId, title, message.toString(), notificationType)
    ApplicationManager.getApplication().messageBus.syncPublisher(Notifications.TOPIC).notify(notification)
}
