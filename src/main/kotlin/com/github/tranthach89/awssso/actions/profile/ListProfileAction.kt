package com.github.tranthach89.awssso.actions.profile

import com.github.tranthach89.awssso.models.Profile
import com.github.tranthach89.awssso.models.awsCli
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
    val listProfile = shellCommand(awsCli(), "configure", "list-profiles").stdout.split("\n")

    listProfile.forEach() {
        if(it.isNotEmpty()) profiles.add(Profile(it))
    }

    return profiles
}
