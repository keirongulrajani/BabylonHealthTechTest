package com.keiron.library.common.mapper

import java.util.ArrayList

abstract class BaseMapperToPresentation<SOURCE, TARGET> {

    /**
     * Transforms a type into another
     *
     * @param toBeTransformed source that will be transformed
     * @return the transformed object
     */
    abstract fun mapToPresentation(toBeTransformed: SOURCE): TARGET

    /**
     * Transforms a list of types into another type
     *
     * @param list list of sources that will be transformed
     * @return the list of transformed objects
     */
    fun mapToPresentation(list: List<SOURCE>): List<TARGET> {
        val targetList = ArrayList<TARGET>()

        for (source in list) {
            targetList.add(mapToPresentation(source))
        }

        return targetList
    }
}
