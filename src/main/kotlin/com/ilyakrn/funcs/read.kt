package com.ilyakrn.funcs

import com.ilyakrn.models.DFA

fun read(str: ArrayList<String>, a: DFA) {
    var state = a.start
    for (i in str) {
        if (a.transitions[Pair(state, i)] != null) {
            state = a.transitions[Pair(state, i)]!!
            if (a.ends.contains(state))
                state = a.start
        }
        else{
            println("not in grammar")
            return
        }
    }
    println("in grammar")
}