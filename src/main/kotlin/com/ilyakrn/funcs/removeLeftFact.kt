package com.ilyakrn.funcs

import com.ilyakrn.models.CFGrammar

fun removeLeftFact(grammar: CFGrammar): CFGrammar {
    val newR = ArrayList<Pair<String, ArrayList<String>>>()
    val newNT = HashSet<String>()
    val allPrefixes = HashSet<ArrayList<String>>()
    grammar.nonTerminals.forEach { x ->
        val xRights = HashSet<ArrayList<String>>()
        grammar.rules.forEach {
            if (it.first == x){
                xRights.add(it.second)
            }
        }
        val prefixes = HashSet<ArrayList<String>>()

        var deep = 0
        xRights.forEach {
            if (deep < it.size) deep = it.size
        }
        for (i in 0 until deep) {
            prefixes.addAll(getPrefixes(hashSetOf(arrayListOf()), xRights, i))
        }
        if (prefixes.contains(emptyList()))
            prefixes.remove(emptyList())
        val newPrefixes = HashSet<ArrayList<String>>(prefixes)
        prefixes.forEach { prefix1 ->
            prefixes.forEach { prefix2 ->
                var count = 0
                xRights.forEach {
                    if (prefix2.size < it.size) count++
                }
                if (count < 2) {
                    newPrefixes.remove(prefix2)
                }
                else {
                    if (prefix1.size < prefix2.size) {
                        var fl = true
                        for (i in prefix1.indices) {
                            if (prefix1[i] != prefix2[i]) {
                                fl = false
                            }
                        }
                        if (fl || prefix1.isEmpty())
                            newPrefixes.remove(prefix1)
                    }
                }
            }
        }
        prefixes.clear()
        prefixes.addAll(newPrefixes)
        allPrefixes.addAll(prefixes)

        prefixes.forEach { prefix ->
            newNT.add("PREFIX_" + prefix.toString())
            for (rule in xRights.filter {
                for (i in prefix.indices) {
                    if (prefix[i] != it[i]) {
                        return@filter false
                    }
                }
                return@filter true
            }) {
                newR.add(Pair("PREFIX_" + prefix.toString(), rule.subList(prefix.size, rule.size).toCollection(ArrayList())))
            }
            newR.add(Pair(x, arrayListOf("PREFIX_" + prefix.toString())))
        }
    }
    println(newR)
    grammar.rules.forEach { rule ->
        var fl = true
        allPrefixes.forEach { prefix ->
            var fl1= true
            for (i in prefix.indices) {
                if (prefix.size < rule.second.size && prefix[i] != rule.second[i]) {
                    fl = false
                }
            }
            if (fl1) {
                fl1 = false
            }
        }
        if (fl)
            newR.add(rule)
    }
    newNT.addAll(grammar.nonTerminals)

    val temp = HashSet<Pair<String, ArrayList<String>>>(newR)
    newR.clear()
    temp.forEach {
        newR.add(it)
    }

    return CFGrammar(grammar.alphabet, newNT, newR, grammar.start)

}

fun getPrefixes(prefixes: HashSet<ArrayList<String>>, rights: HashSet<ArrayList<String>>, deep: Int): HashSet<ArrayList<String>>{
    prefixes.forEach { prefix ->
        if (deep == prefix.size) return prefixes
    }
    if (prefixes.isEmpty())
        return hashSetOf()
    val addedPrefixes = ArrayList<ArrayList<String>>()
    prefixes.forEach { prefix ->
        rights.forEach { right ->
            if(prefix.size < right.size){
                var fl = true
                for (i in prefix.indices){
                    if (prefix[i] != right[i]){
                        fl = false
                        break
                    }
                }
                if (fl){
                    val newPrefix = ArrayList<String>(prefix)
                    newPrefix.add(right[prefix.size])
                    addedPrefixes.add(newPrefix)
                }
            }
        }
    }

    val temp = HashSet<ArrayList<String>>()
    val nextPrefixes = ArrayList<ArrayList<String>>()
    addedPrefixes.forEach { next ->
        if (temp.contains(next)){
            nextPrefixes.add(next)
        }
        temp.add(next)
    }
    return getPrefixes(nextPrefixes.toHashSet(), rights, deep)

}

fun main() {
    println(getPrefixes(hashSetOf(arrayListOf()), hashSetOf(
        arrayListOf("d", "b", "z", "Z" ),
        arrayListOf("d", "b", "z")
    ), 3))
}