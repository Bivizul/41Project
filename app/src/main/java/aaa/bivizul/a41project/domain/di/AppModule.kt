package aaa.bivizul.a41project.domain.di

import dagger.Module

@Module(includes = [NetworkModule::class, AppBindModule::class])
class AppModule