package com.gallapillo.joba.common

object Constants {
    // const for firebase
    const val USERS_COLLECTION = "Users"

    // const for registration
    val GENDERS_LIST = listOf<String>("Мужской", "Женский", "Другой")

    // const for create Resume
    val CATEGORY_LIST = listOf("Дизайн", "Разработка", "Копирайтинг", "Безопастность", "Видео")
    val WORK_FIRST_CATEGORY_LIST = listOf("UX/UI дизайнер", "Дизайнер интерьера", "Веб дизайнер")
    val WORK_SECOND_CATEGORY_LIST = listOf("Front-end разработчик", "Back-end разработчик", "Android разработчик", "IOS разработчик")
    val WORK_THIRD_CATEGORY_LIST = listOf("Писатель", "Переводчик", "SMM-щик")
    val WORK_FOURTH_CATEGORY_LIST = listOf("Сетвая безопастность", "Безопастность внутреней системы")
    val WORK_FIFTH_CATEGORY_LIST = listOf("Монтажер", "Сценарист", "Моушен дизайн")

    // const for education
    val EDUCATION_LIST = listOf("Среднее специальное", "Высшее специальное", "Дополнительное образование")

    enum class SearchStatus {
        IN_ACTIVE_SEARCH, OPEN_FOR_OFFERS, DO_NOT_FOUND_JOB
    }
}