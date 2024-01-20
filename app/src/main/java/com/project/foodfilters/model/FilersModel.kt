package com.project.foodfilters.model

data class RestaurantData(
    val data: ArrayList<Category>
)

data class Category(
    val name: String,
    val slug: String,
    val taxonomies: ArrayList<Taxonomy>
)

data class Taxonomy(
    val id: Int,
    val Guid: String,
    val slug: String,
    val name: String
)

data class Neighbourhoods(
    val city: String,
    val locations: ArrayList<Location>
)

data class Location(
    val id: Int,
    val Guid: String,
    val slug: String,
    val name: String
)

data class SortBy(
    val name: String,
    val slug: String
)

data class PriceRange(
    val name: String,
    val slug: String
)
