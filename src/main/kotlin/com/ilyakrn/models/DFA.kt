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
        return "DFA(\nsymb=$symb, \nq=$q, \ntransitions=$transitionsString, \nstart='$start', \nends=$ends\n)"
    }
}
