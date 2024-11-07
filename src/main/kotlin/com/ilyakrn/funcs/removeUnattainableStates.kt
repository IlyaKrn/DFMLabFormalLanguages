package com.ilyakrn.funcs

import com.ilyakrn.models.DFA

fun removeUnattainableStates(a: DFA): DFA {

    val attainable = HashSet<String>()
    var newAttainable = HashSet<String>()
    newAttainable.add(a.start)

    while (newAttainable != attainable){
        attainable.addAll(newAttainable)
        for (state in attainable){
            for (symb in a.symb){
                if (a.transitions[Pair(state, symb)] != null)
                    newAttainable.add(a.transitions[Pair(state, symb)]!!)
            }
        }
    }
    attainable.addAll(newAttainable)

    val newTransitions = HashMap<Pair<String, String>, String>();
    for (transition in a.transitions){
        if (!attainable.contains(transition.key.first) || !attainable.contains(transition.value)){
            continue
        }
        newTransitions[Pair(transition.key.first, transition.key.second)] = transition.value
    }
    var newEnds = HashSet<String>()
    for (e in a.ends){
        if (!attainable.contains(e))
            continue
        newEnds.add(e)
    }
    return DFA(a.symb, attainable, newTransitions, a.start, newEnds)

}