package com.gallapillo.joba.domain.model

data class FeedBack (
    var owner: User?,
    var raiting: Int = 0,
    var description: String
)