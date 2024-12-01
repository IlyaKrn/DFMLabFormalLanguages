package com.ilyakrn.funcs

import com.ilyakrn.models.CFGrammar

fun removeExtraSymbols(grammar: CFGrammar): CFGrammar {
    val W = HashSet<String>()
    W.add(grammar.start)

    val WNew = HashSet<String>()
    WNew.add(grammar.start)
    var isStart = true

    while (W != WNew || isStart) {
        W.addAll(WNew)
        isStart = false
        for (rule in grammar.rules) {
            if (W.contains(rule.first)){
                WNew.addAll(rule.second)
            }
        }
    }

    val newR = ArrayList<Pair<String, ArrayList<String>>>()
    val newT = HashSet<String>()
    val newNT = HashSet<String>()
    W.forEach {
        if (grammar.alphabet.contains(it))
            newT.add(it)
        else
            newNT.add(it)
    }
    grammar.rules.forEach {
        if (newNT.contains(it.first)) {
            var fl = true
            it.second.forEach { it1 ->
                if (!newNT.contains(it1) && !newT.contains(it1)) {
                    fl = false
                }
            }
            if (fl){
                newR.add(it)
            }
        }
    }

    return CFGrammar(newT, newNT, newR, grammar.start)

}