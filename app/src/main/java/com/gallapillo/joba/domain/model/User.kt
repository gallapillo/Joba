package com.gallapillo.joba.domain.model

import com.gallapillo.joba.common.Constants

data class User(
    var name: String = "",
    var surName: String = "",
    var userId: String = "",
    var email: String = "",
    var password: String = "",
    var userRole: String = "worker",
    var birthDay: String = "",
    var gender: String = "Other",
    var resume: MutableList<Resume> = emptyList<Resume>().toMutableList(),
    var vacancy: List<Vacancy> = emptyList(),
    var company: Company = Company(),
    var wannaFindJob: Int = Constants.SearchStatus.IN_ACTIVE_SEARCH.ordinal
)