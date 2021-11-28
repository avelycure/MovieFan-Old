package com.avelycure.moviefan.domain.mappers

import com.avelycure.moviefan.domain.models.Person
import com.avelycure.moviefan.domain.models.PersonInfo

fun Person.setProperties(personInfo: PersonInfo): Boolean {
    var isChanged = false
    if (!personInfo.birthday.isNullOrBlank()) {
        this.birthday = personInfo.birthday
        isChanged = true
    }
    if (personInfo.biography.isNotBlank()) {
        this.biography = personInfo.biography
        isChanged = true
    }
    if (!personInfo.deathDay.isNullOrBlank()) {
        this.deathDay = personInfo.deathDay
        isChanged = true
    }
    if (personInfo.alsoKnownAs.isNotEmpty()) {
        this.alsoKnownAs = personInfo.alsoKnownAs
        isChanged = true
    }
    if (personInfo.gender != -1) {
        this.gender = personInfo.gender
        isChanged = true
    }
    if (!personInfo.placeOfBirth.isNullOrBlank()) {
        this.placeOfBirth = personInfo.placeOfBirth
        isChanged = true
    }
    if (!personInfo.homepage.isNullOrBlank()) {
        this.homepage = personInfo.homepage
        isChanged = true
    }
    if (personInfo.profileImages.isNotEmpty()) {
        this.profileImages = personInfo.profileImages
        isChanged = true
    }
    return isChanged
}