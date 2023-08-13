package com.sorsix.finalproject.easyeats.models

import jakarta.persistence.*

@Entity
@Table(name = "ingredients")
class Ingredient(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    val name: String,

    val quantity: Int,

    val measurementUnit: String,

    ) {
    constructor() : this(0, "", 0, "") {}
}