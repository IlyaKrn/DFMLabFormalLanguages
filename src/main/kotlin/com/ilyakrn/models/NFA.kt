package com.ilyakrn.models

data class NFA(
    var symb: HashSet<String>,
    var q: HashSet<String>,
    var transitions: HashMap<Pair<String, String>, HashSet<String?>>,
    var start: String,
    var ends: HashSet<String>


) {
    override fun toString(): String {
        return "NFA(\nsymb=$symb, \nq=$q, \ntransitions=$transitions, \nstart='$start', \nends=$ends\n)"
    }
}
