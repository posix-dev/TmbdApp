package com.example.tmbdapp.detail_actor.data

import com.example.tmbdapp.detail_actor.domain.DetailPerson
import javax.inject.Inject

class DetailPersonMapper @Inject constructor() {
    fun map(from: DetailPersonResponse): DetailPerson = from.run {
        DetailPerson(
            adult = adult ?: false,
            alsoKnownAs = alsoKnownAs,
            biography = biography.orEmpty(),
            birthday = birthday.orEmpty(),
            deathday = deathday.orEmpty(),
            gender = gender,
            homepage = homepage.orEmpty(),
            id = id,
            imdbId = imdbId,
            knownForDepartment = knownForDepartment.orEmpty(),
            name = name.orEmpty(),
            placeOfBirth = placeOfBirth.orEmpty(),
            popularity = popularity,
            profilePath = profilePath
        )
    }
}
