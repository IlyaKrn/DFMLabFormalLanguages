package com.ilyakrn

import com.ilyakrn.funcs.*
import java.io.File
import java.io.FileInputStream
import java.util.*

fun main() {

    var g = getGrammar(FileInputStream("C:\\Users\\IlyaKrn\\IdeaProjects\\DFMLabFormalLanguages\\src\\main\\resources\\test.txt"), System.out)
    if(checkLanguageExists(g)){
        g = removeExtraNonTerminals(g)
        g = removeExtraSymbols(g)
        g = removeExtraEpsilonRules(g)
        g = removeChainRules(g)
        g = removeLeftFact(g)
        println(g)
        return
    }
    println("language not exists")


}
