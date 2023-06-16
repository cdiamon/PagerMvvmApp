package com.task1.models

data class Recipe(
    val id: Int,
    val title: String,
    val tags: List<String>,
) {

    override fun equals(other: Any?): Boolean {
        if (other == null || other !is Recipe) return false
        return id == other.id
    }
    override fun hashCode(): Int {
        return id
    }
}
