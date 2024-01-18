package com.thetrainline.compose_animation_workshop.animations

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.thetrainline.compose_animation_workshop.Screen

@Composable
fun AnimateContentSize(
    navigateTo: (Screen) -> Unit
) {
    Screen(
        screen = Screen.AnimateContentSize,
        description = "The animateContentSize modifier animates a size change. Modifier order matters! Make sure you place it before any size modifiers.",
        navigateTo = navigateTo
    ) {
        AnimateContentSize()
    }
}

@Composable
private fun AnimateContentSize() {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(5) {
            var expanded by remember {
                mutableStateOf(false)
            }
            Box(
                modifier = Modifier
                    .background(
                        color = MaterialTheme.colorScheme.primary,
                        shape = MaterialTheme.shapes.medium
                    )
                    .animateContentSize()
                    .height(if (expanded) 128.dp else 64.dp)
                    .fillMaxWidth()
                    .clickable(
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() }
                    ) {
                        expanded = !expanded
                    }
            )
        }
    }
}
