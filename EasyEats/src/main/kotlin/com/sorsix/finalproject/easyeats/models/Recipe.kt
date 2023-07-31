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

    var title: String,

    @Column(columnDefinition = "TEXT")
    var description: String,

    @OneToMany(cascade = [CascadeType.PERSIST])
    var ingredients: MutableList<Ingredient>,

    var image: String,

    val date: LocalDateTime?,

    @ManyToOne
    var category: Category?,

    @ManyToOne
    var subCategory: SubCategory?,

    @ManyToOne
    var user: User?

) { constructor() : this(0, "", "", mutableListOf(), "", null, null, null, null) {}
}
