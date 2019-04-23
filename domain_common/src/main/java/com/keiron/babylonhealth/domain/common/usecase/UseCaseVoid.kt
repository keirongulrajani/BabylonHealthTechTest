package com.keiron.babylonhealth.domain.common.usecase

abstract class UseCaseVoid<out Type> where Type : Any {
    abstract fun buildUseCase(): Type
}