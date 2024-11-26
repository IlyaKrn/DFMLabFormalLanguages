package com.ilyakrn.funcs

import com.ilyakrn.models.CFGrammar
import java.io.InputStream
import java.io.PrintStream
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap
import kotlin.collections.HashSet


fun getGrammar(stream: InputStream, out: PrintStream?): CFGrammar {
    // Scanner of input stream
    val scanner = Scanner(stream)
    // initial values of grammar
    val terminals = HashSet<String>()
    val nonTerminals = HashSet<String>()
    val rules = ArrayList<Pair<String, ArrayList<String>>>()
    var start = ""
    var linearly = 0 // 0 none; 1 right; -1 left;


    // reading NonTerminals
    out?.println("enter NonTerminals every from new line without SPACE key,\n('EPSILON' and 'FINAL' is reserved value) (stop word is 'STOP')")
    var inputLine = scanner.nextLine()
    while (inputLine != "STOP") {
        if (!inputLine.contains(" ") && !nonTerminals.contains(inputLine) && inputLine != "EPSILON" && inputLine != "FINAL")
            nonTerminals.add(inputLine)
        else
            out?.println("wrong NonTerminal $inputLine skipped, try again")
        inputLine = scanner.nextLine()
    }
    // checking empty input
    if (nonTerminals.size == 0)
        throw RuntimeException("NonTerminals not found")


    // reading Terminals
    out?.println("enter Terminals (not repeat NonTerminals,\n('EPSILON' and 'FINAL' is reserved value) every from new line\nwithout SPACE key (stop word is 'STOP')")
    inputLine = scanner.nextLine()
    while (inputLine != "STOP") {
        if (!inputLine.contains(" ") && !nonTerminals.contains(inputLine) && !terminals.contains(inputLine) && inputLine != "EPSILON" && inputLine != "FINAL")
            terminals.add(inputLine)
        else
            out?.println("wrong Terminal $inputLine skipped, try again")
        inputLine = scanner.nextLine()
    }
    // checking empty input
    if (terminals.size == 0)
        throw RuntimeException("NonTerminals not found")
    // empty word
    terminals.add("EPSILON")


    // reading Rules
    out?.println("enter Rules as 'NonTerminal -> Terminal',\n'NonTerminal -> Terminal NonTerminal' or\n'NonTerminal -> NonTerminal Terminal' every from new line\nwithout SPACE key (stop word is 'STOP')")
    inputLine = scanner.nextLine()
    while (inputLine != "STOP") {
        val lineSep = inputLine.split(" ")
        if (lineSep.size < 3){
            out?.println("wrong Rule $inputLine skipped, try again")
        }
        if (!nonTerminals.contains(lineSep[0])){
            out?.println("wrong Rule $inputLine skipped, try again")
        }
        var fl = false
        for (i in lineSep.indices){
            if(i > 1 && !nonTerminals.contains(lineSep[i]) && !terminals.contains(lineSep[i])){
                out?.println("wrong Rule $inputLine skipped, try again")
                fl = true
                break
            }
        }
        if (fl)
            continue
        val r = ArrayList<String>()
        for (i in lineSep.indices){
            if(i > 1 && (!nonTerminals.contains(lineSep[i]) || !terminals.contains(lineSep[i]))){
                r.add(lineSep[i])
            }
        }
        rules.add(Pair(lineSep[0], r))
        inputLine = scanner.nextLine()
    }
    // checking empty input
    if (rules.size == 0)
        throw RuntimeException("Rules not found")


    // reading starting NonTerminal
    out?.println("enter start NonTerminal")
    inputLine = scanner.nextLine()
    while (true) {
        if (nonTerminals.contains(inputLine))
            start = inputLine
        if (start != "")
            break
        out?.println("wrong start NonTerminal $inputLine skipped, try again")
        if (scanner.hasNext())
            inputLine = scanner.nextLine()
    }

    if (nonTerminals.size == 0 || terminals.size == 0 || rules.size == 0)
        throw RuntimeException("Input is empty")

    return CFGrammar(terminals, nonTerminals, rules, start)
}

