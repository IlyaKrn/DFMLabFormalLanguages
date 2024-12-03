package com.ilyakrn.funcs

import com.ilyakrn.models.CFGrammar

fun removeChainRules(grammar: CFGrammar): CFGrammar {

    val outNTs = HashMap<String, HashSet<String>>()

    for (a in grammar.nonTerminals){



        val N = HashSet<String>()
        val NNew = HashSet<String>()
        N.add(a)
        NNew.add(a)
        var isStart = true
        while (N != NNew || isStart) {
            N.addAll(NNew)
            isStart = false
            for (b in N){
                for (rule in grammar.rules) {
                    if (rule.first == b){
                        if (rule.second.size == 1 && grammar.nonTerminals.contains(rule.second[0]))
                            NNew.add(rule.second[0])
                    }
                }
            }
        }
        outNTs[a] = N
    }

    val newR = ArrayList<Pair<String, ArrayList<String>>>()
    grammar.rules.forEach {
        if (!(it.second.size == 1 && grammar.nonTerminals.contains(it.second[0]))) {
            for(n in outNTs){
                if (n.value.contains(it.first)){
                    newR.add(Pair(n.key, it.second))
                }
            }
        }
    }

    return CFGrammar(grammar.alphabet, grammar.nonTerminals, newR, grammar.start)

}