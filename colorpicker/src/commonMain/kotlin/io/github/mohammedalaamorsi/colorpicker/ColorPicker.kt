package io.github.mohammedalaamorsi.colorpicker

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import io.github.mohammedalaamorsi.colorpicker.ext.toHex
import io.github.mohammedalaamorsi.colorpicker.ext.transparentBackground
import io.github.mohammedalaamorsi.colorpicker.pickers.CircleColorPicker
import io.github.mohammedalaamorsi.colorpicker.pickers.ClassicColorPicker
import io.github.mohammedalaamorsi.colorpicker.pickers.RingColorPicker
import io.github.mohammedalaamorsi.colorpicker.pickers.SimpleRingColorPicker

sealed class ColorPickerType {
    /**
     * @param showAlphaBar Sets the visibility of the alpha bar.
     */
    class Classic(
        val showAlphaBar: Boolean = true,
        val initialColor: Color = Color.Red
    ) : ColorPickerType()

    /**
     * @param showBrightnessBar Sets the visibility of the brightness bar.
     * @param showAlphaBar Sets the visibility of the alpha bar.
     * @param lightCenter Changes the center of the circle to black or white.
     */
    class Circle(
        val showBrightnessBar: Boolean = true,
        val showAlphaBar: Boolean = true,
        val lightCenter: Boolean = true,
        val initialColor: Color = Color.Red
    ) : ColorPickerType()

    /**
     * @param ringWidth Sets the color ring width.
     * @param previewRadius Sets the radius of the center color preview circle.
     * @param showLightnessBar Sets the visibility of the lightness bar.
     * @param showDarknessBar Sets the visibility of the darkness bar.
     * @param showAlphaBar Sets the visibility of the alpha bar.
     * @param showColorPreview Sets the visibility of the center color preview circle.
     */
    class Ring(
        val ringWidth: Dp = 10.dp,
        val previewRadius: Dp = 80.dp,
        val showLightnessBar: Boolean = true,
        val showDarknessBar: Boolean = true,
        val showAlphaBar: Boolean = true,
        val showColorPreview: Boolean = true,
        val initialColor: Color = Color.Red
    ) : ColorPickerType()

    /**
     * @param colorWidth Arc width of all colors.
     * @param tracksCount Amount of the tracks.
     * @param sectorsCount Amount of the sectors for each track.
     */
    class SimpleRing(
        val colorWidth: Dp = 20.dp,
        val tracksCount: Int = 5,
        val sectorsCount: Int = 24,
        val initialColor: Color = Color.Red
    ) : ColorPickerType()
}

/**
 * @param type Color picker type example [ColorPickerType.Circle].
 * @param onPickedColor Executes when the user selects a color.
 */
@ExperimentalComposeUiApi
@Composable
fun ColorPicker(
    modifier: Modifier = Modifier,
    type: ColorPickerType = ColorPickerType.Classic(),
    onPickedColor: (Color) -> Unit
) {
    Box(modifier = modifier) {
        when (type) {
            is ColorPickerType.Classic -> ClassicColorPicker(
                showAlphaBar = type.showAlphaBar,
                initialColor = type.initialColor,
                onPickedColor = onPickedColor,
            )

            is ColorPickerType.Circle -> CircleColorPicker(
                showAlphaBar = type.showAlphaBar,
                showBrightnessBar = type.showBrightnessBar,
                lightCenter = type.lightCenter,
                initialColor = type.initialColor,
                onPickedColor = onPickedColor,

                )

            is ColorPickerType.Ring -> RingColorPicker(
                ringWidth = type.ringWidth,
                previewRadius = type.previewRadius,
                showLightColorBar = type.showLightnessBar,
                showDarkColorBar = type.showDarknessBar,
                showAlphaBar = type.showAlphaBar,
                showColorPreview = type.showColorPreview,
                initialColor = type.initialColor,
                onPickedColor = onPickedColor
            )

            is ColorPickerType.SimpleRing -> SimpleRingColorPicker(
                colorWidth = type.colorWidth,
                tracksCount = type.tracksCount,
                sectorsCount = type.sectorsCount,
                initialColor = type.initialColor,
                onPickedColor = onPickedColor
            )
        }
    }
}

/**
 * @param show Dialog Visibility.
 * @param onDismissRequest Executes when the user tries to dismiss the dialog.
 * @param properties [DialogProperties] for further customization of this dialog's behavior.
 * @param type Color picker type example [ColorPickerType.Classic].
 * @param onPickedColor Executes when the user selects a color from the color picker dialog.
 */
@ExperimentalComposeUiApi
@Composable
fun ColorPickerDialog(
    show: Boolean,
    onDismissRequest: () -> Unit,
    properties: DialogProperties = DialogProperties(),
    type: ColorPickerType = ColorPickerType.Classic(),
    onPickedColor: (Color) -> Unit
) {
    var showDialog by remember(show) {
        mutableStateOf(show)
    }
    var color by remember {
        mutableStateOf(Color.White)
    }
    if (showDialog) {
        Dialog(onDismissRequest = {
            onDismissRequest()
            showDialog = false
        }, properties = properties) {
            Box(
                modifier = Modifier
                    .width(IntrinsicSize.Max)
                    .clip(RoundedCornerShape(32.dp))
                    .background(Color.White)
            ) {
                Box(modifier = Modifier.padding(32.dp)) {
                    Column {
                        ColorPicker(type = type, onPickedColor = {
                            color = it
                        })
                        Spacer(modifier = Modifier.height(16.dp))
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(50.dp, 30.dp)
                                    .clip(RoundedCornerShape(50))
                                    .border(0.3.dp, Color.LightGray, RoundedCornerShape(50))
                                    .transparentBackground(verticalBoxesAmount = 4)
                                    .background(color)
                            )
                            Text(
                                text = buildAnnotatedString {
                                    withStyle(SpanStyle(color = Color.Gray)) {
                                        append("#")
                                    }
                                    withStyle(SpanStyle(fontWeight = FontWeight.Bold)) {
                                        append(color.toHex())
                                    }
                                },
                                fontSize = 14.sp,
                                fontFamily = FontFamily.Monospace,
                            )
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                        OutlinedButton(
                            modifier = Modifier.fillMaxWidth(),
                            onClick = {
                                onPickedColor(color)
                                showDialog = false
                            },
                            shape = RoundedCornerShape(50)
                        ) {
                            Text(text = "Select")
                        }
                    }
                }
            }
        }
    }
}