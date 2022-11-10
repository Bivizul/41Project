package aaa.bivizul.a41project.data.network

import aaa.bivizul.a41project.domain.model.Funspo
import aaa.bivizul.a41project.domain.model.Funspos
import aaa.bivizul.a41project.domain.model.Funspog
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface FunspoService {

    @GET("41Project/funspos.json")
    suspend fun getFunspos(): Response<List<Funspos>>

    @POST("41Project/funspo.php")
    suspend fun getFunspo(@Body funspo: Funspo): Response<Funspog>

}