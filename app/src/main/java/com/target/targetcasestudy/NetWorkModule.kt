package com.target.targetcasestudy

import com.target.targetcasestudy.api.BASE_URL
import com.target.targetcasestudy.api.DealApiKtx
import com.target.targetcasestudy.api.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object Graph {

    @Provides
    @Singleton
    fun retrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun dealApi(retrofit: Retrofit): DealApiKtx {
        return retrofit.create(DealApiKtx::class.java)
    }

    @Provides
    @Singleton
    fun dealRepository(dealApi: DealApiKtx): Repository {
        return Repository(dealApi)
    }
}