package com.gallapillo.joba.domain.model

data class Resume (
    var id: String,
    var name: String,
    var user: User?,
    var description: String,
    var experience: List<Experience> = emptyList(),
    var education: List<Education> = emptyList(),
    var ownerId: String,
    var wannaSalary: String = "",
    var categoryId: Int,
    var subCategoryId: Int
)