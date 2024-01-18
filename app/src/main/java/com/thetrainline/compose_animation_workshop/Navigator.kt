@file:OptIn(ExperimentalMaterial3Api::class)

package com.thetrainline.compose_animation_workshop

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.Crossfade
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.thetrainline.compose_animation_workshop.animations.AnimateAsState
import com.thetrainline.compose_animation_workshop.animations.AnimateContentSize
import com.thetrainline.compose_animation_workshop.animations.AnimatedContent
import com.thetrainline.compose_animation_workshop.animations.AnimatedVisibility
import com.thetrainline.compose_animation_workshop.animations.Crossfade
import com.thetrainline.compose_animation_workshop.animations.InfiniteTransition

enum class Screen(val title: String) {
    ComposeAnimations("Compose Animation"),
    AnimateAsState("animate*AsState"),
    AnimateContentSize("AnimateContentSize"),
    AnimatedContent("AnimatedContent"),
    AnimatedVisibility("AnimatedVisibility"),
    Crossfade("Crossfade"),
    InfiniteTransition("InfiniteTransition"),
}

@Composable
fun Navigator() {
    var currentScreen by remember {
        mutableStateOf(Screen.ComposeAnimations)
    }

    val navigateTo: (Screen) -> Unit = remember {
        { screen -> currentScreen = screen }
    }

    BackHandler {
        navigateTo(Screen.ComposeAnimations)
    }

    Crossfade(
        targetState = currentScreen,
        label = "navigation"
    ) { screen ->
        when (screen) {
            Screen.ComposeAnimations -> ComposeAnimations(navigateTo)
            Screen.AnimateAsState -> AnimateAsState(navigateTo)
            Screen.AnimateContentSize -> AnimateContentSize(navigateTo)
            Screen.AnimatedContent -> AnimatedContent(navigateTo)
            Screen.AnimatedVisibility -> AnimatedVisibility(navigateTo)
            Screen.Crossfade -> Crossfade(navigateTo)
            Screen.InfiniteTransition -> InfiniteTransition(navigateTo)
        }
    }
}

@Composable
fun Screen(
    screen: Screen,
    description: String? = null,
    navigateTo: (Screen) -> Unit,
    content: @Composable () -> Unit,
) {
    Scaffold(
        topBar = {
            MediumTopAppBar(
                navigationIcon = {
                    AnimatedVisibility(
                        visible = screen != Screen.ComposeAnimations,
                        enter = fadeIn(),
                        exit = fadeOut()
                    ) {
                        IconButton(
                            onClick = {
                                navigateTo(Screen.ComposeAnimations)
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Filled.ArrowBack,
                                contentDescription = "Back"
                            )
                        }
                    }
                },
                title = {
                    Text(
                        text = screen.title,
                        style = MaterialTheme.typography.headlineMedium
                    )
                }
            )
        }
    ) {
        Column(
            modifier = Modifier.padding(it).padding(horizontal = 16.dp)
        ) {
            description?.let {
                Text(
                    text = description
                )
            }
            Spacer(modifier = Modifier.height(24.dp))
            content()
        }
    }
}
