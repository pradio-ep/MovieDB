package com.pradioep.test.di

import com.pradioep.test.repository.Repository
import org.koin.dsl.module

val RepositoryModule = module {
    single { Repository(get()) }
}