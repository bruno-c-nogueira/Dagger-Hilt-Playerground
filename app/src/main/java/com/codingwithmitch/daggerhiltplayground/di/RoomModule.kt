package com.codingwithmitch.daggerhiltplayground.di

import android.content.Context
import androidx.room.Room
import com.codingwithmitch.daggerhiltplayground.room.BlogDao
import com.codingwithmitch.daggerhiltplayground.room.BlogDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
object RoomModule {
    @Singleton
    @Provides
    fun provideDataBase(@ApplicationContext context: Context): BlogDataBase {
        return Room.databaseBuilder(context, BlogDataBase::class.java, BlogDataBase.DATABASE_NAME)
            .fallbackToDestructiveMigration().build()
    }

    @Singleton
    @Provides
    fun provideBlogDAO(dataBase: BlogDataBase): BlogDao{
        return dataBase.blogDao()
    }
}