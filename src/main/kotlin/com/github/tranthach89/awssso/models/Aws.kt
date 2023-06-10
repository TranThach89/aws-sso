package com.github.tranthach89.awssso.models

import com.github.tranthach89.awssso.helper.CommandResult
import com.github.tranthach89.awssso.helper.shellCommand

class Aws {
    fun cli(): CommandResult {
        val awsCli = shellCommand("which", "aws")
        return awsCli
    }

    fun cliExists(): Boolean {
        return cli().stdout.contains("not found", ignoreCase = true)
    }
}

fun awsCli(): String {
    return Aws().cli().stdout
}