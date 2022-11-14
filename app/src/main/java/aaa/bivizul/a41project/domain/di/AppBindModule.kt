package aaa.bivizul.a41project.domain.di

import aaa.bivizul.a41project.data.repository.FunspoRepositoryImpl
import aaa.bivizul.a41project.data.repository.FunsposRepositoryImpl
import aaa.bivizul.a41project.domain.repository.FunspoRepository
import aaa.bivizul.a41project.domain.repository.FunsposRepository
import dagger.Binds
import dagger.Module

@Module
interface AppBindModule {

    @Suppress("FunctionName")
    @Binds
    fun bindFunspoRepositoryImpl_to_FunspoRepository(
        funspoRepositoryImpl: FunspoRepositoryImpl
    ): FunspoRepository

    @Suppress("FunctionName")
    @Binds
    fun bindFunsposRepositoryImpl_to_FunsposRepository(
        funsposRepositoryImpl: FunsposRepositoryImpl
    ): FunsposRepository

}