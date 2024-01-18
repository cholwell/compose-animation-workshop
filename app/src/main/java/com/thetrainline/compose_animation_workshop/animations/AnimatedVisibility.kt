package com.thetrainline.compose_animation_workshop.animations

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.unit.dp
import com.thetrainline.compose_animation_workshop.Screen

@Composable
fun AnimatedVisibilityScreen(
    navigateTo: (Screen) -> Unit
) {
    Screen(
        screen = Screen.AnimatedVisibility,
        description = "The AnimatedVisibility composable animates the appearance and disappearance of its content.",
        navigateTo = navigateTo
    ) {
        AnimatedVisibilityExample()
    }
}

@Composable
private fun AnimatedVisibilityExample() {
    var expanded by remember {
        mutableStateOf(false)
    }

    val iconRotation by animateFloatAsState(targetValue = if (expanded) -180f else 0f, label = "triangle")

    Column {
        Row(
            modifier = Modifier
                .clip(
                    shape = MaterialTheme.shapes.medium
                )
                .border(
                    width = 1.dp,
                    color = MaterialTheme.colorScheme.outline,
                    shape = MaterialTheme.shapes.medium
                )
                .clickable {
                    expanded = !expanded
                }
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Things"
            )
            Icon(
                modifier = Modifier.rotate(iconRotation),
                imageVector = Icons.Filled.ArrowDropDown,
                contentDescription = null
            )
        }
        AnimatedVisibility(
            visible = expanded
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                (1..3).forEach {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = "Thing $it"
                    )
                }
            }
        }
    }
}
