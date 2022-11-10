package aaa.bivizul.a41project.domain.repository

import aaa.bivizul.a41project.domain.model.Funspos
import retrofit2.Response

interface FunsposRepository {

    suspend fun getFunspos() : Response<List<Funspos>>

}