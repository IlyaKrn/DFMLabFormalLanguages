package com.ilyakrn.funcs

import com.ilyakrn.models.DFA

fun connectEqualsStates(a: DFA) : DFA {

    var partitions = ArrayList<HashSet<String>>()
    partitions.add(a.ends)
    partitions.add(a.q.filter { !a.ends.contains(it) }.toHashSet())

    partitions = recursivePartitions(partitions, a.symb, a.transitions)

    val newTransitions = HashMap<Pair<String, String>, String>()

    for (partitionKey in partitions) {
        for (transition in a.transitions) {
            if (partitionKey.contains(transition.key.first)){
                var nextState = HashSet<String>()
                for (partitionNext in partitions){
                    if (partitionNext.contains(transition.value)){
                        nextState = partitionNext
                    }
                }
                if (nextState.isNotEmpty()) {
                    newTransitions[Pair(partitionKey.toString(), transition.key.second)] = nextState.toString()
                }
            }
        }
    }
    val newEnds = HashSet<String>()
    val newStates = HashSet<String>()
    for (partition in partitions){
        for (s in partition){
            if (a.ends.contains(s)){
                newEnds.add(partition.toString())
            }
        }
        newStates.add(partition.toString())
    }

    return DFA(a.symb, newStates, newTransitions, a.start, newEnds)
}

private fun recursivePartitions(p: ArrayList<HashSet<String>>, symbols: HashSet<String>, transitions: HashMap<Pair<String, String>, String>): ArrayList<HashSet<String>>{
    var pNew = ArrayList<HashSet<String>>()
    for (eqClass in p) {
        val newClass = HashSet<String>()
        for (s in symbols) {
            var newStates = HashSet<String>()
            for (state in eqClass) {
                transitions[Pair(state, s)]?.let {
                    if (newStates.isEmpty()){
                        for (i in p){
                            if (i.contains(it))
                                newStates = i
                        }
                    }
                    else{
                        if (!newStates.contains(it)){
                            newClass.add(state)
                        }
                    }
                }
            }
        }
        val oldClass = HashSet<String>()
        if (newClass.isNotEmpty()){
            oldClass.addAll(eqClass)
            oldClass.removeAll(newClass)
            pNew.add(newClass)
            pNew.add(oldClass)
        }
        else{
            pNew.add(eqClass)
        }
    }
    if (p == pNew)
        return pNew
    else
        return recursivePartitions(pNew, symbols, transitions)


}