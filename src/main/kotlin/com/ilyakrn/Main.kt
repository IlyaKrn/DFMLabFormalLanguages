package com.ilyakrn

import com.ilyakrn.funcs.checkLanguageExists
import com.ilyakrn.funcs.getGrammar
import com.ilyakrn.funcs.removeExtraNonTerminals
import com.ilyakrn.funcs.removeExtraSymbols
import java.io.File
import java.io.FileInputStream
import java.util.*

fun main() {

    val scanner = Scanner(System.`in`)
    var inputStream = System.`in`
    println("load input from file 'input.txt'? [y/n]")
//    val ans = scanner.nextLine()
//    when(ans){
//        "y" -> {
//            println("enter path to file")
//            val file = File(scanner.nextLine())
//            if (!file.exists()){
//                println("file does not exist")
//               // return
//            }
//            println("enabled input from file")
//            inputStream = FileInputStream("C:\\Users\\IlyaKrn\\IdeaProjects\\DFMLabFormalLanguages\\src\\main\\resources\\test.txt")
//        }
//        "n" -> {
//            println("enabled input from terminal")
//        }
//        else -> {
//            println("incorrect answer, enabled input from terminal")
//        }
//    }
    inputStream = FileInputStream("C:\\Users\\IlyaKrn\\IdeaProjects\\DFMLabFormalLanguages\\src\\main\\resources\\test.txt")




    var g = getGrammar(inputStream, System.out)
    checkLanguageExists(g)
    g = removeExtraNonTerminals(g)
    g = removeExtraSymbols(g)
    println(g)



}
