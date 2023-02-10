package com.github.tranthach89.awssso.actions.profile

import com.github.tranthach89.awssso.models.Profile
import com.intellij.terminal.TerminalShellCommandHandler
import com.github.tranthach89.awssso.helper.shellCommand

//object ListProfileAction {
//    val all = listOf(
//        Profile("1"),
//        Profile("2"),
//        Profile("xin chao"),
//        Profile("Non Profile")
//    )
//}

fun ListProfileAction(): ArrayList<Profile> {
    val profiles: ArrayList<Profile> = ArrayList()
    val awsCli = shellCommand("which", "aws")
    // Todo check awsCli is exists
    val listProfile = shellCommand(awsCli.stdout, "configure", "list-profiles").stdout.split("\n")

    listProfile.forEach() {
        profiles.add(Profile(it))
    }

    return profiles
}
