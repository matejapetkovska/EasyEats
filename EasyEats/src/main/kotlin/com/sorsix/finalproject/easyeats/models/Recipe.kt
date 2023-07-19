package com.sorsix.finalproject.easyeats.models

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name="recipes")
class Recipe (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    val title: String,

    val description: String,

    @OneToMany
    val ingredients: List<Ingredient>?,

    val image: String,

    val date: LocalDateTime?,

    @ManyToOne
    val subCategory: SubCategory?

) { constructor() : this(0, "", "", null, "", null, null) {}
}