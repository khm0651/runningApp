package com.example.runningappvita.di.module

import android.content.Context
import com.example.runningappvita.VitaAsyncStorage
import com.example.runningappvita.utils.UserAuth
import com.example.runningappvita.utils.VitaSharePre
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
class VitaModule {

    @Singleton
    @Provides
    fun provideVitaAsyncStorage (userAuth: UserAuth, vitaSharePre: VitaSharePre): VitaAsyncStorage = VitaAsyncStorage(userAuth,vitaSharePre)

    @Singleton
    @Provides
    fun provideUserAuth(@ApplicationContext context: Context): UserAuth = UserAuth(context)

    @Singleton
    @Provides
    fun provideVitaSharePre(@ApplicationContext context: Context): VitaSharePre = VitaSharePre(context)

}