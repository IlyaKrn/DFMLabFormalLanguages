package com.ilyakrn.funcs

import com.ilyakrn.models.DFA
import java.io.InputStream
import java.io.PrintStream
import java.util.Scanner

fun getAutomate(input: InputStream, output: PrintStream?): DFA {

    val scanner = Scanner(input)
    val alphabet = HashSet<String>()
    val states = HashSet<String>()
    val transitions = HashMap<Pair<String, String>, String>()
    var start = ""
    var ends = HashSet<String>()


    output?.println("enter alphabet. STOP to end, 'EPSILON' reserved")
    var inputLine = scanner.nextLine()
    while (inputLine != "STOP") {
        if (!inputLine.contains(" ") && inputLine.isNotEmpty() && !alphabet.contains(inputLine) && inputLine != "EPSILON")
            alphabet.add(inputLine)
        else
            output?.println("wrong input $inputLine skipped, try again")
        inputLine = scanner.nextLine()
    }

    output?.println("enter states. STOP to end")
    inputLine = scanner.nextLine()
    while (inputLine != "STOP") {
        if (!inputLine.contains(" ") && inputLine.isNotEmpty() && !states.contains(inputLine))
            states.add(inputLine)
        else
            output?.println("wrong input $inputLine skipped, try again")
        inputLine = scanner.nextLine()
    }

    output?.println("enter transitions (State Symbol -> State). STOP to end")
    inputLine = scanner.nextLine()
    while (inputLine != "STOP") {
        val tr = inputLine.split(" ")
        if (tr.size == 4 && states.contains(tr[0]) && states.contains(tr[3]) && alphabet.contains(tr[1])) {
            transitions[Pair(tr[0], tr[1])] = tr[3]
        }
        else
            output?.println("wrong input $inputLine skipped, try again")
        inputLine = scanner.nextLine()
    }

    output?.println("enter start state")
    inputLine = scanner.nextLine()
    while (true) {
        if (states.contains(inputLine)) {
            start = inputLine
            break
        }
        else
            output?.println("wrong input $inputLine skipped, try again")
        inputLine = scanner.nextLine()
    }

    output?.println("enter end states. STOP to end")
    inputLine = scanner.nextLine()
    while (inputLine != "STOP") {
        if (states.contains(inputLine))
            ends.add(inputLine)
        else
            output?.println("wrong input $inputLine skipped, try again")
        inputLine = scanner.nextLine()
    }

    return DFA(alphabet, states, transitions, start, ends)

}