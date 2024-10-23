package com.ilyakrn.funcs

import com.ilyakrn.models.NFA
import com.ilyakrn.models.Grammar
import kotlin.collections.ArrayList

fun getAutomateFromRight(grammar: Grammar): NFA {
    // check grammar linearly
    if (!grammar.isRight)
        throw RuntimeException("grammar is not right")
    grammar.nonTerminals.add("FINAL")

    // rules with FINAL state
    val newRules = ArrayList<Triple<String, String, String>>()
    for (r in grammar.rules) {
        if (r.third == null){
            var fl = false
            for (i in grammar.rules){
                if (i.third != null)
                    fl = true
            }
            if (fl){
                newRules.add(Triple(r.first, r.second, "FINAL"))
            }
        }
    }
    grammar.rules.addAll(newRules)

    // automate transitions
    var transitions = HashMap<Pair<String, String>, HashSet<String?>>()
    for (r in grammar.rules){
        if (r.third != null){
            if (transitions[Pair(r.first, r.second)] == null)
                transitions[Pair(r.first, r.second)] = HashSet()
            transitions[Pair(r.first, r.second)]!!.add(r.third)
        }
    }
    // end states
    val ends = HashSet<String>()
    for (r in grammar.rules){
        if (r.third == null){
            for (i in grammar.rules){
                if (i.first == r.first && i.second == r.second && i.third != null)
                    ends.add(i.third!!)
            }
        }
    }
    // adding start to end states
    if (grammar.rules.contains(Triple(grammar.start, "EPSILON", null)))
        ends.add(grammar.start)
    return NFA(grammar.terminals, grammar.nonTerminals, transitions, grammar.start, ends)

}