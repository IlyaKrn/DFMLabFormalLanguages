package com.ilyakrn

import com.ilyakrn.funcs.convertNFAtoDFA
import com.ilyakrn.funcs.getAutomateFromRight
import com.ilyakrn.funcs.getGrammar
import com.ilyakrn.funcs.read
import java.io.File
import java.io.FileInputStream
import java.util.*
import kotlin.collections.ArrayList

fun main() {

    val scanner = Scanner(System.`in`)
    var inputStream = System.`in`
    println("load input from file 'input.txt'? [y/n]")
    val ans = scanner.nextLine()
    when(ans){
        "y" -> {
            println("enter path to file")
            val file = File(scanner.nextLine())
            if (!file.exists()){
                println("file does not exist")
                return
            }
            println("enabled input from file")
            inputStream = FileInputStream(file)
        }
        "n" -> {
            println("enabled input from terminal")
        }
        else -> {
            println("incorrect answer, enabled input from terminal")
        }
    }



    val g = getGrammar(inputStream, System.out)
    if (g.isRight) {
        val a = getAutomateFromRight(g)
        val da = convertNFAtoDFA(a)
        println(da)
        while (true) {
            println("enter word")
            val input = ArrayList<String>()
            while (true) {
                val inp = scanner.nextLine().split(" ")
                var fl = false
                for (i in inp) {
                    if (i.isEmpty()) {
                        println("try again")
                        fl = true
                        continue
                    }
                }
                if (fl) {
                    continue
                }
                input.addAll(inp)
                break
            }
            read(input, da)
        }
    }
    else {
        println("grammar is left!")
    }



}
