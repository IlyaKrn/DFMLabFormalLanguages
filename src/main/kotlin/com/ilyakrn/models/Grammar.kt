package com.ilyakrn.models;

data class Grammar (
    var terminals: HashSet<String>,
    var nonTerminals: HashSet<String>,
    var rules: ArrayList<Triple<String, String, String?>>,
    var start: String,
    var isRight: Boolean

) {
    override fun toString(): String {
        return "Grammar(\nterminals=$terminals, \nnonTerminals=$nonTerminals, \nrules=$rules, \nstart='$start', \nisRight=$isRight\n)"
    }
}