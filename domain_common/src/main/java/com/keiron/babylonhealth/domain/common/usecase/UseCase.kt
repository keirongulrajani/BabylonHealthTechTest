package com.keiron.babylonhealth.domain.common.usecase

abstract class UseCase<out Type, in Params> where Type : Any {
    abstract fun buildUseCase(params: Params): Type
}