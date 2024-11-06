package com.ilyakrn.funcs

import com.ilyakrn.models.NFA

fun removeUnattainableStates(a: NFA): NFA {

    val attainable = HashSet<String>()
    var newAttainable = HashSet<String>()
    newAttainable.add(a.start)

    while (newAttainable != attainable){
        attainable.addAll(newAttainable)
        for (state in attainable){
            for (symb in a.symb){
                if (a.transitions[Pair(state, symb)] != null)
                    newAttainable.addAll(a.transitions[Pair(state, symb)]!!)
            }
        }
    }
    attainable.addAll(newAttainable)

    val newTransitions = HashMap<Pair<String, String>, HashSet<String>>();
    for (transition in a.transitions){
        if (!attainable.contains(transition.key.first)){
            continue
        }
        val newRes = HashSet<String>()
        for (res in transition.value){
            if (!attainable.contains(res))
                continue
            newRes.add(res)
        }
        if (newRes.isEmpty())
            continue
        newTransitions[Pair(transition.key.first, transition.key.second)] = newRes
    }
    var newEnds = HashSet<String>()
    for (e in a.ends){
        if (!attainable.contains(e))
            continue
        newEnds.add(e)
    }
    return NFA(a.symb, attainable, newTransitions, a.start, newEnds)

}