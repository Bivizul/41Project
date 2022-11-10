package aaa.bivizul.a41project.domain.repository

import aaa.bivizul.a41project.domain.model.Funspo
import aaa.bivizul.a41project.domain.model.Funspog
import retrofit2.Response

interface FunspoRepository {

    suspend fun getFunspo(funspo: Funspo) : Response<Funspog>

}