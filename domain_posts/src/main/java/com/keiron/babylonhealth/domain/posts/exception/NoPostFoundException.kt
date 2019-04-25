package com.keiron.babylonhealth.domain.posts.exception

class NoPostFoundException(id: Int) : Exception("No post found with id $id")