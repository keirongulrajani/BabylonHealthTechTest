package com.keiron.babylonhealth.domain.posts.exception

class NoPostFoundException(val id: Int) : Exception("No post found with id $id")