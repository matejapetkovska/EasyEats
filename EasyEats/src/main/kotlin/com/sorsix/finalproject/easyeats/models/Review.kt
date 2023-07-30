package com.sorsix.finalproject.easyeats.models

import jakarta.persistence.*

@Entity
@Table(name="reviews")
class Review (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    val rating: Int,

    val comment: String,

    @ManyToOne
    val user: User?,

    @ManyToOne
    val recipe: Recipe?

) { constructor() : this(0, 0, "", null, null) {}
}