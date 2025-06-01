package com.example.tmbdapp.detail_actor.domain

import kotlinx.coroutines.flow.Flow

interface PeopleRepository {
    fun getPersonDetails(id: Int): Flow<DetailPerson>
}