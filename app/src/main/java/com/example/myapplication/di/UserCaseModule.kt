package com.example.myapplication.di

import com.example.myapplication.domain.usecase.GetProductListUseCase
import org.koin.dsl.module

val userCaseModule = module {
single { GetProductListUseCase(get()) }
}