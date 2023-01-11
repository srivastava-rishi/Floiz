package com.rsstudio.flobiz.di

import android.content.Context
import com.google.gson.GsonBuilder
import com.rsstudio.flobiz.app.App
import com.rsstudio.flobiz.data.local.preference.PreferenceProvider
import com.rsstudio.flobiz.data.network.apis.QuestionApiInterface
import com.rsstudio.flobiz.util.Constant
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun applicationContext( @ApplicationContext applicationContext: Context) : App {
        return applicationContext as App
    }

    @Singleton
    @Provides
    fun preferenceProvider(@ApplicationContext applicationContext: Context): PreferenceProvider {
        return PreferenceProvider(applicationContext)
    }

    @Singleton
    @Provides
    fun provideQuestionApi(): QuestionApiInterface =
        Retrofit.Builder()
            .baseUrl(Constant.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
            .create(QuestionApiInterface::class.java)

}