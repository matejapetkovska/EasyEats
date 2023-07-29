package com.sorsix.finalproject.easyeats.models

import jakarta.persistence.*
import java.awt.TextArea
import java.time.LocalDateTime

@Entity
@Table(name="recipes")
class Recipe(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    val title: String,

    @Column(columnDefinition = "TEXT")
    val description: String,

    @OneToMany(cascade = [CascadeType.PERSIST])
    var ingredients: MutableList<Ingredient>,

    val image: String,

    val date: LocalDateTime?,

    @ManyToOne
    val category: Category?,

    @ManyToOne
    val subCategory: SubCategory?,

    @ManyToOne
    var user: User?

) { constructor() : this(0, "", "", mutableListOf(), "", null, null, null, null) {}
}
