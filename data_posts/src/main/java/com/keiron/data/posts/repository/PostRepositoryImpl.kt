package com.keiron.data.posts.repository

import com.keiron.babylonhealth.domain.posts.model.Comment
import com.keiron.babylonhealth.domain.posts.model.Post
import com.keiron.babylonhealth.domain.posts.repository.PostRepository
import com.keiron.data.posts.datasource.PostDataSource
import com.keiron.data.posts.mapper.CommentDtoToCommentMapper
import com.keiron.data.posts.mapper.PostDtoToPostMapper
import io.reactivex.Single
import javax.inject.Inject

class PostRepositoryImpl @Inject constructor(
    private val postDataSource: PostDataSource,
    private val commentDtoToCommentMapper: CommentDtoToCommentMapper,
    private val postDtoToPostMapper: PostDtoToPostMapper
) : PostRepository {
    override fun getAllPosts(): Single<List<Post>> {
        return postDataSource.getAllPosts().map { postDtoToPostMapper.mapToDomain(it) }
    }

    override fun getAllComments(): Single<List<Comment>> {
        return postDataSource.getAllComments().map { commentDtoToCommentMapper.mapToDomain(it) }
    }
}