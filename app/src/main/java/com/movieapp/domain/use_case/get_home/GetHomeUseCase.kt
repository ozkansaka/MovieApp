package com.movieapp.domain.use_case.get_home

import com.movieapp.common.Resource
import com.movieapp.data.remote.dto.toHome
import com.movieapp.domain.model.HomeTypeModel
import com.movieapp.domain.repository.HomeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject


class GetHomeUseCase @Inject constructor(
    private val repository: HomeRepository
) {
    operator fun invoke(): Flow<Resource<List<HomeTypeModel>>> = flow {
        try {
            emit(Resource.Loading())
            val popular = repository.getPopular().results.map { it.toHome() }
            val topRated = repository.getTopRated().results.map { it.toHome() }
            val upcoming = repository.getUpcoming().results.map { it.toHome() }

            val listData: ArrayList<HomeTypeModel> = arrayListOf()

            listData.addAll(
                arrayListOf(
                    HomeTypeModel.Title(
                        title = "Popular"
                    ), HomeTypeModel.Horizontal(
                        data = popular
                    ), HomeTypeModel.Title(
                        title = "Top Rated"
                    ), HomeTypeModel.Horizontal(
                        data = topRated
                    ), HomeTypeModel.Title(
                        title = "Upcoming"
                    ), HomeTypeModel.Vertical(
                        data = upcoming
                    )
                )
            )

            emit(Resource.Success((listData)))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred."))
        } catch (e: IOException) {
            emit(Resource.Error(e.localizedMessage ?: "Couldn't reach server. Check your internet connection."))
        }
    }
}
