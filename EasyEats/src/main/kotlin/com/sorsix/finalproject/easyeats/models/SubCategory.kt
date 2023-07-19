package com.sorsix.finalproject.easyeats.models

import jakarta.persistence.*

@Entity
@Table(name="sub_categories")
class SubCategory (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    val name: String,

    val description: String

    ){ constructor() : this(0, "", "") {}
}