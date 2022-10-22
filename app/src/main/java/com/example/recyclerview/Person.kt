package com.example.recyclerview

import java.util.UUID

data class Person(
    val id: UUID = UUID.randomUUID()
)
{
    var name: String = ""
    var age: Int = 0
    var isSuperCool: Boolean = false

}