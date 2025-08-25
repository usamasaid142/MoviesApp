package com.example.moviesapp.di


import android.content.Context
import androidx.room.Room
import com.example.moviesapp.data.local.MovieDataBase
import com.example.moviesapp.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppDataBaseModule {

    @Singleton
    @Provides
    fun provideRunDatabse(
        @ApplicationContext app: Context
    )= Room.databaseBuilder(app, MovieDataBase::class.java, Constants.Movies_Db).build()

    @Singleton
    @Provides
    fun provideRunDao(db:MovieDataBase)= db.moviesDao()


}