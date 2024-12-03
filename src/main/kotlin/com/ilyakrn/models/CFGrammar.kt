package com.ilyakrn.models;

data class CFGrammar (
    var alphabet: HashSet<String>,
    var nonTerminals: HashSet<String>,
    var rules: ArrayList<Pair<String, ArrayList<String>>>,
    var start: String
) {
    override fun toString(): String {
        var alphabetS = "alphabet: "
        alphabet.forEach {
            alphabetS += "$it "
        }
        var nonTerminalsS = "nonTerminals: "
        nonTerminals.forEach {
            nonTerminalsS += "$it "
        }
        var rulesS = "rules:\n"
        rules.forEach {
            var r = ""
            it.second.forEach { it1 ->
                r += "$it1 "
            }
            rulesS += "${it.first} -> $r\n"
        }
        return "CFGrammar:\n$alphabetS\n$nonTerminalsS\n${rulesS}start: $start\n"
    }
}