package com.github.tranthach89.awssso.helper

import com.intellij.execution.configurations.GeneralCommandLine
import java.util.concurrent.CompletableFuture

//class ShellCommand {
//}

fun shellCommand(vararg command: String): CommandResult {
    val process = GeneralCommandLine(command.toList())
            .withWorkDirectory(System.getProperty("user.home"))
            .withParentEnvironmentType(GeneralCommandLine.ParentEnvironmentType.CONSOLE)
            .createProcess()
    val stdout = CompletableFuture.supplyAsync { process.inputStream.bufferedReader().readText() }
    val stderr = CompletableFuture.supplyAsync { process.errorStream.bufferedReader().readText() }
    val exitCode = process.waitFor()
    return CommandResult(exitCode, stdout.get(), stderr.get())
}

data class CommandResult(
        val exitCode: Int,
        val stdout: String,
        val stderr: String,
)