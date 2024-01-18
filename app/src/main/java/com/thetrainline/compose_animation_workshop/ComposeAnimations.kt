@file:OptIn(ExperimentalFoundationApi::class)

package com.thetrainline.compose_animation_workshop

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ComposeAnimations(
    navigateTo: (Screen) -> Unit
) {
    Screen(
        screen = Screen.ComposeAnimations,
        navigateTo = navigateTo
    ) {
        LazyColumn(
            verticalArrangement = Arrangement.SpaceBetween,
            contentPadding = PaddingValues(top = 24.dp)
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
