package com.nhanbt.demo_kotlin_native.features.category.domain.entities

data class Category(
    var id: String,
    var title: String,
) {
    constructor() : this("", "")
}
