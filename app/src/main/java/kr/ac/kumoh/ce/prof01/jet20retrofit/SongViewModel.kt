package kr.ac.kumoh.ce.prof01.jet20retrofit

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SongViewModel : ViewModel() {
    private val serverUrl = "https://jetpack-server.pockethost.io/"
    private val songApi: SongApi

    private val _songs = mutableStateListOf<Song>()
    val songs: List<Song>
        get() = _songs

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(serverUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        songApi = retrofit.create(SongApi::class.java)

        select()
    }

    fun select() {
        viewModelScope.launch {
            val response: SongResponse

            try {
                response = songApi.getSongs()
            } catch (e: Exception) {
                Log.e("Error select()", e.toString())
                return@launch
            }

            //Log.i("select()", response.toString())
            _songs.clear()
            response.items.forEach {
                _songs.add(it)
            }
        }
    }
}