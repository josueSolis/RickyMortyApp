package com.omni.pro.characters.android.repository

import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.omni.pro.characters.android.events.MessageEvent
import com.omni.pro.characters.domain.CharacterEntity
import com.omni.pro.characters.repository.CharsApi
import com.omni.pro.characters.repository.dto.CharacterFilters
import org.greenrobot.eventbus.EventBus

class CharactersDataSource(
    private val api: CharsApi,
    private val filter: CharacterFilters?,
) : PagingSource<Int, CharacterEntity>() {

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CharacterEntity> {
        try {
            // Start refresh at page 1 if undefined.
            val nextPageNumber = params.key ?: 1
            val response = api.characters(nextPageNumber, filter)
            return LoadResult.Page(
                data = response?.rows ?: emptyList(),
                prevKey = null, // Only paging forward.
                nextKey = response?.next
            )
        } catch (e: Exception) {
            EventBus.getDefault().post(MessageEvent.Error("On error", e))
            return LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, CharacterEntity>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}