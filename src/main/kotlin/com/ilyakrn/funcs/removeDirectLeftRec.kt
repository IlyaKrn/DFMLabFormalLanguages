package com.ilyakrn.funcs

import com.ilyakrn.models.CFGrammar

fun removeDirectLeftRec(grammar: CFGrammar): CFGrammar {
    val newR = ArrayList<Pair<String, ArrayList<String>>>(grammar.rules)
    val newNT = HashSet<String>(grammar.nonTerminals)

    grammar.nonTerminals.forEach { x ->
        val xRecRules = ArrayList<Pair<String, ArrayList<String>>>()
        val xNormRules = ArrayList<Pair<String, ArrayList<String>>>()
        grammar.rules.filter { it.first == x }.forEach{ rule ->
            if (rule.second[0] == x) {
                xRecRules.add(rule)
            }
            else{
                xNormRules.add(rule)
            }
        }
        if (xRecRules.isNotEmpty()){
            val y = "REC_" + x
            newR.removeAll(xRecRules)
            newR.removeAll(xNormRules)
            newNT.add(y)
            xRecRules.forEach { recRule ->
                val newRule1 = Pair(y, arrayListOf<String>())
                val newRule2 = Pair(y, arrayListOf<String>())
                newRule1.second.addAll(recRule.second.subList(1, recRule.second.size))
                newRule2.second.addAll(recRule.second.subList(1, recRule.second.size))
                newRule1.second.add(y)
                newR.add(newRule1)
                newR.add(newRule2)
            }
            xNormRules.forEach { normRule ->
                val newRule1 = Pair(x, ArrayList(normRule.second))
                val newRule2 = Pair(x, ArrayList(normRule.second))
                newRule1.second.add(y)
                newR.add(newRule1)
                newR.add(newRule2)
            }


        }

    }


    return CFGrammar(grammar.alphabet, newNT, newR, grammar.start)

}