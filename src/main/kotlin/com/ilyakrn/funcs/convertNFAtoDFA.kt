package com.ilyakrn.funcs

import com.ilyakrn.models.DFA
import com.ilyakrn.models.NFA

fun convertNFAtoDFA(nfa: NFA): DFA {
    val dfaStates = HashSet<HashSet<String>>() // Новые состояния DFA
    val dfaTransitions = HashMap<Pair<HashSet<String>, String>, HashSet<String>>() // Переходы DFA
    val dfaEnds = HashSet<HashSet<String>>() // Конечные состояния DFA

    // Start in DFA
    val startState = HashSet<String>()
    startState.add(nfa.start)
    val queue = ArrayDeque<HashSet<String>>()
    queue.add(startState)
    dfaStates.add(startState)

    while (queue.isNotEmpty()) {
        val currentState = queue.removeFirst()

        for (symbol in nfa.symb) {
            val newState = HashSet<String>()

            // Set of states from current state
            for (state in currentState) {
                val transitionKey = Pair(state, symbol)
                if (nfa.transitions.containsKey(transitionKey)) {
                    for (ii in nfa.transitions[transitionKey]!!){
                        newState.add(ii!!)
                    }
                }
            }

            if (newState.isNotEmpty()) {
                dfaTransitions[Pair(currentState, symbol)] = newState

                if (!dfaStates.contains(newState)) {
                    dfaStates.add(newState)
                    queue.add(newState)
                }

                if (newState.any { nfa.ends.contains(it) }) {
                    dfaEnds.add(newState)
                }
            }
        }
    }

    val dfaStatesForm = HashSet<String>()
    dfaStates.forEach { dfaStatesForm.add(it.toString()) }
    val dfaTransitionsForm = HashMap<Pair<String, String>, String>()
    dfaTransitions.forEach { dfaTransitionsForm[Pair(it.key.first.toString(), it.key.second)] = it.value.toString() }
    val dfaEndsForm = HashSet<String>()
    dfaEnds.forEach { dfaEndsForm.add(it.toString()) }

    return DFA(
        symb = nfa.symb,
        q = dfaStatesForm,
        transitions = dfaTransitionsForm,
        start = startState.toString(),
        ends = dfaEndsForm
    )
}