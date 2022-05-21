package com.movieapp.domain.use_case

import com.movieapp.common.Resource
import com.movieapp.data.remote.dto.toDetail
import com.movieapp.data.remote.dto.toSearch
import com.movieapp.domain.model.Detail
import com.movieapp.domain.model.Search
import com.movieapp.domain.repository.IRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject


class GetSearchUseCase @Inject constructor(
    private val repository: IRepository
) {
    var query = "a"
    operator fun invoke(): Flow<Resource<List<Search>>> = flow {

        try {
            emit(Resource.Loading())

            val search = repository.getSearch(query).results.map { it.toSearch() }

            emit(Resource.Success((search)))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred."))
        } catch (e: IOException) {
            emit(Resource.Error(e.localizedMessage ?: "Couldn't reach server. Check your internet connection."))
        }
    }
}
