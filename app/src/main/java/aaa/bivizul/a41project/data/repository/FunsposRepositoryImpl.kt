package aaa.bivizul.a41project.data.repository

import aaa.bivizul.a41project.data.network.FunspoService
import aaa.bivizul.a41project.domain.model.Funspos
import aaa.bivizul.a41project.domain.repository.FunsposRepository
import retrofit2.Response
import javax.inject.Inject

class FunsposRepositoryImpl @Inject constructor(val funspoService: FunspoService) : FunsposRepository {

    override suspend fun getFunspos() : Response<List<Funspos>> {
        return funspoService.getFunspos()
    }
}