package com.movieapp.domain.use_case

import com.movieapp.common.Resource
import com.movieapp.data.remote.dto.toHome
import com.movieapp.domain.model.HomeTypeModel
import com.movieapp.domain.repository.IRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject


class GetHomeUseCase @Inject constructor(
    private val repository: IRepository
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
                    ),
                    HomeTypeModel.Horizontal(
                        data = popular
                    ),
                    HomeTypeModel.Title(
                        title = "Top Rated"
                    ),
                    HomeTypeModel.Horizontal(
                        data = topRated
                    ),
                    HomeTypeModel.Title(
                        title = "Upcoming"
                    ),
                )
            )
            for (i in upcoming) {
                listData.add(
                    HomeTypeModel.Vertical(
                        id = i.id,
                        title = i.title,
                        image = i.image,
                        imdb = i.imdb
                    )
                )

            }

            emit(Resource.Success((listData)))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred."))
        } catch (e: IOException) {
            emit(Resource.Error(e.localizedMessage ?: "Couldn't reach server. Check your internet connection."))
        }
    }
}
