
package com.thetrainline.compose_animation_workshop.animations

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.SizeTransform
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.thetrainline.compose_animation_workshop.Screen

@Composable
fun AnimatedContentScreen(
    navigateTo: (Screen) -> Unit
) {
    Screen(
        screen = Screen.AnimatedContent,
        description = "The AnimatedContent composable animates its content as it changes based on a target state.",
        navigateTo = navigateTo
    ) {
        AnimatedContentExample()
    }
}

@Composable
private fun AnimatedContentExample() {
    var count by remember {
        mutableIntStateOf(5)
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 40.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        IconButton(
            onClick = {
                count--
            }
        ) {
            Icon(
                modifier = Modifier.size(32.dp),
                imageVector = Icons.Filled.Remove,
                contentDescription = "minus"
            )
        }
        Spacer(modifier = Modifier.width(12.dp))
        AnimatedContent(
            targetState = count,
            label = "count",
            transitionSpec = {
                when {
                    targetState > initialState -> {
                        slideInVertically { height -> height } + fadeIn() togetherWith slideOutVertically { height -> -height } + fadeOut()
                    }
                    else -> {
                        slideInVertically { height -> -height } + fadeIn() togetherWith slideOutVertically { height -> height } + fadeOut()
                    }
                }
                .using(SizeTransform(clip = false))
            }
        ) {
            Text(
                text = "$it",
                style = MaterialTheme.typography.headlineLarge
            )
        }
        Spacer(modifier = Modifier.width(12.dp))
        IconButton(
            onClick = {
                count++
            }
        ) {
            Icon(
                modifier = Modifier.size(32.dp),
                imageVector = Icons.Filled.Add,
                contentDescription = "minus"
            )
        }
    }
}
