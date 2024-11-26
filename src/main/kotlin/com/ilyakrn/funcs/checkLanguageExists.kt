package com.ilyakrn.funcs

import com.ilyakrn.models.CFGrammar

fun checkLanguageExists(grammar: CFGrammar): Boolean {

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

    return N.contains(grammar.start)
}