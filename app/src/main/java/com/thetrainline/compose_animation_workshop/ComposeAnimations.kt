@file:OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)

package com.thetrainline.compose_animation_workshop

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ComposeAnimations(
    navigateTo: (Screen) -> Unit
) {
    Scaffold(
        topBar = {
            MediumTopAppBar(
                title = {
                    Text(
                        text = Screen.ComposeAnimations.title,
                        style = MaterialTheme.typography.headlineLarge
                    )
                }
            )
        }
    ) {
        LazyColumn(
            contentPadding = PaddingValues(top = 24.dp + it.calculateTopPadding())
        ) {
            stickyHeader {
                Divider()
            }
            Screen.entries
                .filterNot { it == Screen.ComposeAnimations }
                .forEach {
                    item {
                        ListItem(
                            modifier = Modifier.clickable {
                                navigateTo(it)
                            },
                            headlineContent = {
                                Text(
                                    text = it.title,
                                    style = MaterialTheme.typography.bodyLarge
                                )
                            },
                            trailingContent = {
                                Icon(
                                    modifier = Modifier.size(16.dp),
                                    imageVector = Icons.Filled.ArrowForward,
                                    contentDescription = null
                                )
                            }
                        )
                        Divider()
                    }
                }
        }
    }
}
