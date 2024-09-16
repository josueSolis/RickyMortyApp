package com.omni.pro.characters.commons

import com.apollographql.apollo.api.Optional

fun String?.toOptional(): Optional<String> {
    return Optional.presentIfNotNull(this)
}

fun String.initials(): String {
    if (this.isEmpty()) return this
    val data = this.split(" ")
    val res = data.fold("") { acc, next ->
        if (acc.length >= 2) {
            acc
        } else {
            acc + next[0]
        }
    }
    return res
}