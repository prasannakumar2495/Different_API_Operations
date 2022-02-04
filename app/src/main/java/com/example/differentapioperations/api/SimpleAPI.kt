package com.example.differentapioperations.api

import com.example.differentapioperations.model.Post
import retrofit2.Response
import retrofit2.http.*

interface SimpleAPI {
    /*
   to avoid the application from crashing, when the URL is wrong, we should surround our
   return value with "Response"
    */
    @GET("posts/1")
    suspend fun getPost(): Response<Post>

    /*
    currently we are only hardcoding the endpoint of the URL, so to avoid that,
    we will be doing the following...
    We should enter the end point of the URL.
    Path --> This annotation will be used to put something in the URL.
     */
    @GET("posts/{postNumber}")
    suspend fun getPost2(@Path("postNumber") number: Int): Response<Post>

    /*
    with the help of Query --> we can search the JSON code.
    we should enter all the parameters mentioned below, either by hardcoding/through editText field.
    in the parameter _sort --> which data to be sorted(i.e)id/title/body...
    in the parameter _order --> asc/desc --> type of sorting...
     */
    @GET("posts")
    suspend fun getCustomPost(
        @Query("userId") userId: Int,
        @Query("_sort") sort: String,
        @Query("_order") order: String,
    ): Response<List<Post>>

    @GET("posts")
    suspend fun getCustomPost2(
        @Query("userId") userId: Int,
        @QueryMap options: Map<String, String>,
    ): Response<List<Post>>

    @POST("posts")
    suspend fun pushPost(@Body post: Post): Response<Post>

    /*
    In general data will be parsed in the JSON format, if we use @FormUrlEncoded,
    data will be parsed on the form of Key and Value pairs.
     */
    @FormUrlEncoded
    @POST("posts")
    suspend fun pushPost2(
        @Field("userId") userId: Int,
        @Field("id") id: Int,
        @Field("title") title: String,
        @Field("body") body: String,
    ): Response<Post>
}