package com.avelycure.moviefan.domain.mappers

import com.avelycure.moviefan.domain.models.Person
import com.avelycure.moviefan.domain.models.PersonInfo

fun Person.setProperties(personInfo: PersonInfo) {
    if (!personInfo.birthday.isNullOrBlank())
        this.birthday = personInfo.birthday
    if (personInfo.biography.isNotBlank())
        this.biography = personInfo.biography
    if (!personInfo.deathDay.isNullOrBlank())
        this.deathDay = personInfo.deathDay
    if (personInfo.knownForDepartment.isNotBlank())
        this.knownForDepartment = personInfo.knownForDepartment
    if (personInfo.alsoKnownAs.isNotEmpty())
        this.alsoKnownAs = personInfo.alsoKnownAs
    if (personInfo.gender != -1)
        this.gender = personInfo.gender
    if (!personInfo.placeOfBirth.isNullOrBlank())
        this.placeOfBirth = personInfo.placeOfBirth
    if (!personInfo.homepage.isNullOrBlank())
        this.homepage = personInfo.homepage
    if(personInfo.profileImages.isNotEmpty())
        this.profileImages = personInfo.profileImages
}