package com.omni.pro.characters.commons.mapper

interface Mapper<I, O> {
    fun map(input: I): O?

    fun mapAll(input: List<I>): List<O?> {
        return input.map {
            this.map(it)
        }
    }
}