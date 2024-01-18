package com.thetrainline.compose_animation_workshop.animations

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import com.thetrainline.compose_animation_workshop.Screen

@Composable
fun AnimateAsStateScreen(
    navigateTo: (Screen) -> Unit
) {
    Screen(
        screen = Screen.AnimateAsState,
        description = "The animate*AsState functions are the simplest animation APIs in Compose for animating a single value. You only provide the target value (or end value), and the API starts animation from the current value to the specified value.",
        navigateTo = navigateTo
    ) {
        AnimateFloatAsStateExample()
    }
}

@Composable
private fun AnimateFloatAsStateExample() {
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp - 32.dp

    var opacityValue by remember {
        mutableFloatStateOf(0f)
    }

    var widthValue by remember {
        mutableStateOf(0.dp)
    }

    Box(
        modifier = Modifier
            .height(256.dp)
            .width(widthValue)
            .background(
                color = MaterialTheme.colorScheme.primary.copy(alpha = opacityValue),
                shape = MaterialTheme.shapes.medium
            )
    )
    Spacer(modifier = Modifier.height(24.dp))

    Text(text = "Opacity")
    Slider(
        value = opacityValue,
        onValueChange = {
            opacityValue = it
        },
        steps = 2
    )

    Text(text = "Width")
    Slider(
        value = widthValue.value,
        onValueChange = {
            widthValue = it.dp
        },
        valueRange = (0f..screenWidth.value),
        steps = 2
    )
}
