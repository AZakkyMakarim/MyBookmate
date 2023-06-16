package com.azakkymakarim.mybookmate.di

import com.azakkymakarim.mybookmate.data.Repository

object Injection {
    fun provideRepository(): Repository {
        return Repository.getInstance()
    }
}