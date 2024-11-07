package com.ilyakrn.models

data class DFA(
    var symb: HashSet<String>,
    var q: HashSet<String>,
    var transitions: HashMap<Pair<String, String>, String>,
    var start: String,
    var ends: HashSet<String>


) {
    override fun toString(): String {
        var transitionsString = "{"
        transitions.forEach {
            transitionsString += "${it.key} -> ${it.value},\n"
        }
        transitionsString += "\n}\n"
        var result = ""
        result += "alphabet:\n"
        symb.forEach {
            result += "${it}\n"
        }
        result += "states:\n"
        q.forEach {
            result += "${it}\n"
        }
        result += "transitions:\n"
        transitions.forEach{
            result += "${it.key.first} ${it.key.second} -> ${it.value}\n"
        }
        result += "start:\n${start}\n"
        result += "end:\n"
        ends.forEach {
            result += "$it\n"
        }
        return result
    }
}
