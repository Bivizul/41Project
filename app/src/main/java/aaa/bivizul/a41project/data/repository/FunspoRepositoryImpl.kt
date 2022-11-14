package aaa.bivizul.a41project.data.repository

import aaa.bivizul.a41project.data.network.FunspoService
import aaa.bivizul.a41project.domain.model.Funspo
import aaa.bivizul.a41project.domain.model.Funspog
import aaa.bivizul.a41project.domain.repository.FunspoRepository
import retrofit2.Response
import javax.inject.Inject

class FunspoRepositoryImpl @Inject constructor(val funspoService: FunspoService):FunspoRepository {

    override suspend fun getFunspog(funspo: Funspo) : Response<Funspog> {
        return funspoService.getFunspo(funspo = funspo)
    }

}