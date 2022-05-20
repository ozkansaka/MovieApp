package com.movieapp.domain.use_case

import com.movieapp.common.Resource
import com.movieapp.data.remote.dto.toCategories
import com.movieapp.data.remote.dto.toDetail
import com.movieapp.data.remote.dto.toHome
import com.movieapp.domain.model.Categories
import com.movieapp.domain.model.Detail
import com.movieapp.domain.repository.IRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject


class GetCategoriesUseCase @Inject constructor(
    private val repository: IRepository
) {
    operator fun invoke(): Flow<Resource<List<Categories>>> = flow {

        try {
            emit(Resource.Loading())

            val categories = repository.getCategories().genres.map { it.toCategories() }

            emit(Resource.Success((categories)))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred."))
        } catch (e: IOException) {
            emit(Resource.Error(e.localizedMessage ?: "Couldn't reach server. Check your internet connection."))
        }
    }
}
