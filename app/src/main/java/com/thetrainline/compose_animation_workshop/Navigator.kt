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
import com.thetrainline.compose_animation_workshop.animations.AnimateAsStateScreen
import com.thetrainline.compose_animation_workshop.animations.AnimateContentSizeScreen
import com.thetrainline.compose_animation_workshop.animations.AnimatedContentScreen
import com.thetrainline.compose_animation_workshop.animations.AnimatedVisibilityScreen

enum class Screen(val title: String) {
    ComposeAnimations("Compose Animation"),
    AnimateAsState("animate*AsState"),
    AnimatedContent("AnimatedContent"),
    AnimateContentSize("AnimateContentSize"),
    AnimatedVisibility("AnimatedVisibility")
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
            Screen.AnimateAsState -> AnimateAsStateScreen(navigateTo)
            Screen.AnimateContentSize -> AnimateContentSizeScreen(navigateTo)
            Screen.AnimatedContent -> AnimatedContentScreen(navigateTo)
            Screen.AnimatedVisibility -> AnimatedVisibilityScreen(navigateTo)
        }
    }
}

@Composable
fun Screen(
    screen: Screen,
    description: String,
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
            description.let {
                Text(
                    text = description
                )
            }
            Spacer(modifier = Modifier.height(24.dp))
            content()
        }
    }
}
