package com.ilyakrn.models

data class NFA(
    var symb: HashSet<String>,
    var q: HashSet<String>,
    var transitions: HashMap<Pair<String, String>, HashSet<String>>,
    var start: String,
    var ends: HashSet<String>


) {
    override fun toString(): String {
        var transitionsString = "{"
        transitions.forEach {
            transitionsString += "${it.key} -> ${it.value},\n"
        }
        transitionsString += "\n}\n"
        return "FA(\nsymb=$symb, \nq=$q, \ntransitions=$transitionsString, \nstart='$start', \nends=$ends\n)"
    }
}
