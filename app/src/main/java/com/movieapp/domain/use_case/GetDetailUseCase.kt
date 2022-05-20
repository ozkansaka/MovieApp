package com.movieapp.domain.use_case

import com.movieapp.common.Resource
import com.movieapp.data.remote.dto.toDetail
import com.movieapp.domain.model.Detail
import com.movieapp.domain.repository.IRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject


class GetDetailUseCase @Inject constructor(
    private val repository: IRepository
) {
    var id = 0
    operator fun invoke(): Flow<Resource<List<Detail>>> = flow {

        try {
            emit(Resource.Loading())

            val detail = listOf(repository.getDetail(id).toDetail())

            emit(Resource.Success((detail)))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred."))
        } catch (e: IOException) {
            emit(Resource.Error(e.localizedMessage ?: "Couldn't reach server. Check your internet connection."))
        }
    }
}
