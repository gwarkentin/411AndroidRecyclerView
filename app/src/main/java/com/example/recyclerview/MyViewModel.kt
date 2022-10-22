package com.example.recyclerview

import androidx.lifecycle.ViewModel

class MyViewModel: ViewModel() {

    val people = mutableListOf<Person>()

    init {
       for (i in 0 until 100) {
           val person = Person()

           person.name = "Gary the ${i}ith"
           person.age = 18 + i
           person.isSuperCool = (person.age % 10 == 0)
           people += person
       }

    }
}
