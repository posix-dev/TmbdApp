package com.example.tmbdapp.detail_actor.data

import com.example.tmbdapp.detail_actor.data.api.PeopleService
import com.example.tmbdapp.detail_actor.domain.DetailPerson
import com.example.tmbdapp.detail_actor.domain.PeopleRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PeopleRepositoryImpl @Inject constructor(
    private val api: PeopleService,
    private val mapper: DetailPersonMapper
): PeopleRepository {

    override fun getPersonDetails(id: Int): Flow<DetailPerson> {
        return api.getPersonDetails(id).map(mapper::map)
    }

}