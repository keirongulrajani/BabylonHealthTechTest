package com.keiron.library.common.di

abstract class ComponentHolder<COMPONENT, in FACTORY : ComponentHolder.Factory<COMPONENT>> {

    private var component: COMPONENT? = null

    fun create(factory: FACTORY): COMPONENT {
        component = factory.build()

        return component!!
    }

    fun destroy() {
        component = null
    }

    fun get(): COMPONENT {
        return component!!
    }

    protected abstract fun getComponentClass(): Class<COMPONENT>

    abstract class Factory<COMPONENT> {
        abstract fun build(): COMPONENT
    }
}