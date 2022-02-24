package com.unnamedcardgame.di

import androidx.compose.ui.unit.Constraints
import com.google.gson.Gson
import com.unnamedcardgame.network.ApiService
import com.unnamedcardgame.repo.Repo
import com.unnamedcardgame.repo.Repo_impl
import com.unnamedcardgame.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.invoke.ConstantCallSite
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideString() = "t 1 2 3"

    @Singleton
    @Provides
    fun provideApiService(): ApiService {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(Constants.BASE_URL)
            .build()
            .create(ApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideRepository(apiService: ApiService) : Repo
    {
        return Repo_impl(apiService)
    }
}