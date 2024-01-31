package kr.ac.kumoh.ce.prof01.jet20retrofit

import retrofit2.http.GET

data class Song(
    val id: String,
    val title: String,
    val singer: String,
)

data class SongResponse(
    val page: Int,
    val perPage: Int,
    val totalItems: Int,
    val totalPages: Int,
    val items: List<Song>,
)

interface SongApi {
    @GET("api/collections/song/records?fields=id,title,singer")
    suspend fun getSongs(): SongResponse
}