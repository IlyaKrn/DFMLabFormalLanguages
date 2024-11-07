package com.ilyakrn

import com.ilyakrn.funcs.connectEqualsStates
import com.ilyakrn.funcs.getAutomate
import com.ilyakrn.funcs.removeUnattainableStates
import java.io.FileInputStream

fun main() {

    var a = getAutomate(FileInputStream("C:\\Users\\IlyaKrn\\IdeaProjects\\DFMLabFormalLanguages\\src\\main\\resources\\test.txt"), null)
    println("Start automate:\n$a")
    a = removeUnattainableStates(a)
    a = connectEqualsStates(a)
    println("Minimized automate:\n$a")

}
