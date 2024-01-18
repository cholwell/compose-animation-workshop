package com.thetrainline.compose_animation_workshop.animations

import androidx.compose.runtime.Composable
import com.thetrainline.compose_animation_workshop.Screen

@Composable
fun Crossfade(
    navigateTo: (Screen) -> Unit
) {
    Screen(
        screen = Screen.Crossfade,
        navigateTo = navigateTo
    ) {

    }
}
