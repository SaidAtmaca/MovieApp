package com.example.movieapp.domain.use_case

import com.example.movieapp.data.local.entity.User
import com.example.movieapp.domain.repository.AppRepository
import kotlinx.coroutines.flow.Flow


class UserLiveUseCase (
    private val repository : AppRepository
){


    fun getJustUser():Flow<User> = repository.getUser()






}