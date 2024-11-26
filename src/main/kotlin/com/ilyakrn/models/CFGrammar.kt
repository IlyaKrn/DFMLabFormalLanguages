package com.ilyakrn.models;

data class CFGrammar (
    var alphabet: HashSet<String>,
    var nonTerminals: HashSet<String>,
    var rules: ArrayList<Pair<String, ArrayList<String>>>,
    var start: String
) {
    override fun toString(): String {
        return "CFGrammar(alphabet=$alphabet, nonTerminals=$nonTerminals, rules=$rules, start='$start')"
    }
}