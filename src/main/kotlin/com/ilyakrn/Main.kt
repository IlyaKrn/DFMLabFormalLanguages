package com.ilyakrn

import com.ilyakrn.funcs.*
import java.io.File
import java.io.FileInputStream
import java.util.*

fun main() {

    var g = getGrammar(FileInputStream("C:\\Users\\IlyaKrn\\IdeaProjects\\DFMLabFormalLanguages\\src\\main\\resources\\test.txt"), System.out)
    println("grammar input: ")
    println(g)
    if(checkLanguageExists(g)){
        g = removeExtraNonTerminals(g)
        println("extra nonTerminals removed")
        println(g)
        g = removeExtraSymbols(g)
        println("extra symbols removed")
        println(g)
        g = removeExtraEpsilonRules(g)
        println("extra epsilon rules removed")
        println(g)
        g = removeChainRules(g)
        println("extra chain rules removed")
        println(g)
        g = removeLeftFact(g)
        println("extra left factorization removed")
        println(g)
        g = removeDirectLeftRec(g)
        println("extra direct left recursion removed")
        println(g)
        return
    }
    println("language not exists")


}
