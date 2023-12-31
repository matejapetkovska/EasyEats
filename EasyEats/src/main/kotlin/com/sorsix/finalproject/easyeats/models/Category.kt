package com.sorsix.finalproject.easyeats.models

import jakarta.persistence.*

@Entity
@Table(name = "categories")
class Category(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    val name: String,

    val description: String,


    val image: String,

    ) {
    constructor() : this(0, "", "", "") {}
}