package com.ilyakrn

import com.ilyakrn.funcs.getAutomate
import com.ilyakrn.funcs.removeUnattainableStates

fun main() {

    var a = getAutomate(System.`in`, System.out)
    println(a)
    a = removeUnattainableStates(a)
    println(a)

}
