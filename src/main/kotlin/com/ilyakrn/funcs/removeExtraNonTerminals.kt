package com.ilyakrn.funcs

import com.ilyakrn.models.CFGrammar

fun removeExtraNonTerminals(grammar: CFGrammar): CFGrammar {
    val N = HashSet<String>()
    val NNew = HashSet<String>()
    var isStart = true
    while (N != NNew || isStart) {
        N.addAll(NNew)
        isStart = false
        for (rule in grammar.rules) {
            var fl = false
            rule.second.forEach {if (!N.contains(it) && !grammar.alphabet.contains(it)) {fl = true}}
            if (fl)
                continue
            NNew.add(rule.first)

        }
    }

    val newR = ArrayList<Pair<String, ArrayList<String>>>()
    val newNT = HashSet<String>()
    grammar.nonTerminals.forEach {
        if (N.contains(it)) {
            newNT.add(it)
        }
    }
    grammar.rules.forEach {
        if (newNT.contains(it.first)) {
            var fl = true
            it.second.forEach { it1 ->
                if (!N.contains(it1) && !grammar.alphabet.contains(it1)) {
                    fl = false
                }
            }
            if (fl){
                newR.add(it)
            }
        }
    }

    return CFGrammar(grammar.alphabet, newNT, newR, grammar.start)

}