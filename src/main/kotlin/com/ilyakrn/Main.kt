package com.ilyakrn

import com.ilyakrn.funcs.connectEqualsStates
import com.ilyakrn.funcs.getAutomate
import com.ilyakrn.funcs.removeUnattainableStates
import java.io.File
import java.io.FileInputStream
import java.util.Scanner

fun main() {

    println("enter path to input file:")
    var a = getAutomate(FileInputStream(File(Scanner(System.`in`).nextLine())), null)
    println("Start automate:\n$a")
    a = removeUnattainableStates(a)
    a = connectEqualsStates(a)
    println("Minimized automate:\n$a")

}
