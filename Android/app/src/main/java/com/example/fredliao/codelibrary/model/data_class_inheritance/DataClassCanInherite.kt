package com.example.fredliao.codelibrary.model.data_class_inheritance

// https://proandroiddev.com/kotlin-data-classes-enough-boilerplate-c4647e475485
interface Person {
    val name: String
    val age: Int
    val email: String

    fun hasResponsibilities(): Boolean
}

data class Adult(override val name: String, override val age: Int, override val email: String) : Person {
    // we can have extra fields
    var isMarried: Boolean = false

    override fun hasResponsibilities() = true
}

data class Child(override val name: String, override val age: Int, override val email: String) : Person {
    override fun hasResponsibilities() = false
}