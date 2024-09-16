package com.omni.pro.characters.android.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.Pager
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.itemKey
import com.omni.pro.characters.domain.CharacterEntity
import com.omni.pro.characters.android.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharacterList(
    pager: Pager<Int, CharacterEntity>,
    modifier: Modifier,
    onClick: (CharacterEntity) -> Unit,
) {
    val pullToRefreshState = rememberPullToRefreshState()
    val lazyState = rememberLazyListState()

    val lazyPagingItems = pager.flow.collectAsLazyPagingItems()
    if (lazyPagingItems.loadState.refresh is LoadState.Error && lazyPagingItems.itemCount == 0) {
        Text(
            stringResource(R.string.load_error), Modifier
                .padding(16.dp)
                .clickable {
                    lazyPagingItems.refresh()
                }, textAlign = TextAlign.Center
        )
    }

    PullToRefreshBox(
        modifier = Modifier.fillMaxSize(),
        state = pullToRefreshState,
        isRefreshing = lazyPagingItems.loadState.refresh == LoadState.Loading,
        onRefresh = {
            lazyPagingItems.refresh()
        }
    )
    {
        CharactersLazyList(modifier, lazyState, lazyPagingItems, onClick)
    }
}

@Composable
fun CharactersLazyList(
    modifier: Modifier,
    lazyState: LazyListState,
    lazyPagingItems: LazyPagingItems<CharacterEntity>,
    onClick: (CharacterEntity) -> Unit
) {
    LazyColumn(modifier = modifier, lazyState) {
        items(
            lazyPagingItems.itemCount,
            key = lazyPagingItems.itemKey { it.id },
        ) { index ->
            CharacterRow(
                lazyPagingItems[index] ?: CharacterEntity(),
                onClick = onClick,
            )
        }
    }
}

