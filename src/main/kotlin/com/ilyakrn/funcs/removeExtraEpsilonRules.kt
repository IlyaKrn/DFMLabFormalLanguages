package com.ilyakrn.funcs

import com.ilyakrn.models.CFGrammar

fun removeExtraEpsilonRules(grammar: CFGrammar): CFGrammar {
    val N = HashSet<String>()
    val NNew = HashSet<String>()
    grammar.rules.forEach {
        if (it.second.size == 1 && it.second.contains("EPSILON")) {
            NNew.add(it.first)
            N.add(it.first)
        }
    }
    var isStart = true

    while (N != NNew || isStart) {
        N.addAll(NNew)
        isStart = false
        for (rule in grammar.rules) {
            var fl = false
            rule.second.forEach {
                if (!N.contains(it)) {
                    fl = true
                }
            }
            if (fl)
                continue
            NNew.add(rule.first)
        }
    }


    val newR = ArrayList<Pair<String, ArrayList<String>>>()
    newR.addAll(grammar.rules)
    grammar.rules.forEach {
        if (it.second.size == 1 && it.second.contains("EPSILON")) {
            newR.remove(it)
        }
    }
    var additRules =  ArrayList<Pair<String, ArrayList<String>>>()

    for (rule in newR) {
        val NIndexs = HashSet<Int>()
        for (i in rule.second.indices){
            if (N.contains(rule.second[i]))
                NIndexs.add(i)
        }
        if(NIndexs.size == 0)
            continue
        val indexCombs = HashSet<HashSet<Int>>()

        for (i in 1..NIndexs.size){
            indexCombs.addAll(getCombs(hashSetOf(hashSetOf()), NIndexs, i))
        }

        for (i in indexCombs) {
            var nr = Pair(rule.first, ArrayList(rule.second))
            i.sortedBy { -it }.forEach {
                nr.second.removeAt(it)
            }
            if (nr.second.size != 0 && !(nr.second.size == 1 && nr.second.contains("EPSILON"))) {
                additRules.add(nr)
            }
        }
    }
    newR.addAll(additRules)




    var newNT = grammar.nonTerminals
    var newS = "NEW_" + grammar.start
    if (N.contains(grammar.start)){
        newR.add(Pair(newS, arrayListOf(grammar.start)))
        newR.add(Pair(newS, arrayListOf("EPSILON")))
        newNT.add(newS)
    }
    else {
        newS = grammar.start
    }

    return CFGrammar(grammar.alphabet, newNT, newR, newS)

}

fun getCombs(comb: HashSet<HashSet<Int>>, vals: HashSet<Int>, deep: Int): HashSet<HashSet<Int>> {
    for (c in comb){
        if (c.size == deep){
            return comb
        }
    }
    val next = HashSet<HashSet<Int>>()
    for (c in comb){
        for (i in vals){
            val temp = HashSet<Int>()
            temp.addAll(c)
            temp.add(i)
            next.add(temp)
        }
    }
    return getCombs(next, vals, deep)
}