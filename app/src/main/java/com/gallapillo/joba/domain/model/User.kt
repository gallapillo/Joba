package com.gallapillo.joba.domain.model

data class User (
    var name: String = "",
    var surName: String = "",
    var userId: String = "",
    var email : String = "",
    var password: String = "",
    var userRole: String = "worker",
    var resume: List<Resume> = emptyList(),
    var vacancy: List<Vacancy> = emptyList()
)