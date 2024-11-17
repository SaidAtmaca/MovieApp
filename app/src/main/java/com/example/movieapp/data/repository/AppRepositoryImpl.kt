package com.example.movieapp.data.repository

import android.util.Log
import com.example.movieapp.core.utils.Resource
import com.example.movieapp.data.local.RoomDatabaseDao
import com.example.movieapp.data.local.entity.User
import com.example.movieapp.data.model.MovieDetailModel
import com.example.movieapp.data.model.NowPlayingResponseModel
import com.example.movieapp.data.model.PopularMoviesResponseModel
import com.example.movieapp.data.remote.APIService
import com.example.movieapp.domain.repository.AppRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException

class AppRepositoryImpl(
    private val apiService: APIService,
    private val dao : RoomDatabaseDao
): AppRepository {
    override fun getUser(): Flow<User> = flow{

        try {

            val list = dao.getAllUsers()
            if (list.isNotEmpty()) {
                emit(list.first())
            }


        }catch(e: IOException) {

            e.printStackTrace()

        }


    }

    override fun insertUserFlow(user: User) : Flow<User> = flow {

        try {
            dao.deleteUserTable()
            dao.insertUser(user)
            val newUser = dao.getAllUsers()

            if (newUser.isNotEmpty()){
                //CurrentUser = newUser.first()
                emit(newUser.first())
            }
        }catch (e:Exception){

            e.printStackTrace()
        }



    }

    override fun getNowPlaying(pageValue: Int): Flow<Resource<NowPlayingResponseModel>> = flow{
        emit(Resource.Loading())

        try {

            val response = apiService.getNowPlayingMovies(pageValue)
            emit(Resource.Success(response))

        }catch (e:Exception){
            emit(Resource.Error(e.message.toString()))
        }
    }

    override fun getMovieDetails(movieId: Int): Flow<Resource<MovieDetailModel>> = flow{
        emit(Resource.Loading())


        try {

            val response = apiService.getMovieDetail(movieId)
            Log.e("detailResponse",response.toString())
            emit(Resource.Success(response))

        }catch (e:Exception){
            emit(Resource.Error(e.message.toString()))
        }
    }

    override fun getPopularMovies(pageValue: Int): Flow<Resource<PopularMoviesResponseModel>> = flow{
        emit(Resource.Loading())
        try {

            val response = apiService.getPopularMovies(pageValue)
            emit(Resource.Success(response))

        }catch (e:Exception){
            emit(Resource.Error(e.message.toString()))
        }
    }


}