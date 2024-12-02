package io.github.mohammedalaamorsi.app

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import io.github.mohammedalaamorsi.colorpicker.ColorPicker
import io.github.mohammedalaamorsi.colorpicker.ColorPickerDialog
import io.github.mohammedalaamorsi.colorpicker.ColorPickerType
import io.github.mohammedalaamorsi.colorpicker.ext.argb
import io.github.mohammedalaamorsi.colorpicker.ext.toHex
import io.github.mohammedalaamorsi.colorpicker.ext.transparentBackground

@OptIn(ExperimentalComposeUiApi::class)
@Composable
internal fun App()  {
    Column(
        modifier = Modifier.padding(32.dp),
        horizontalAlignment = CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        var color by remember {
            mutableStateOf(Color.Red)
        }
        var colorPickerType by remember {
            mutableStateOf<ColorPickerType>(ColorPickerType.Classic(initialColor = Color.Green))
        }
        var showDialog by remember {
            mutableStateOf(false)
        }
        ColorPickerDialog(
            show = showDialog,
            type = colorPickerType,
            properties = DialogProperties(),
            onDismissRequest = {
                showDialog = false
            },
            onPickedColor = {
                showDialog = false
                color = it
            },
        )
        ColorPicker(type = colorPickerType) {
            color = it
        }
        Spacer(modifier = Modifier.height(20.dp))
        Row(
            horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
        ) {
            val (alpha, red, green, blue) = color.argb()
            Column(horizontalAlignment = CenterHorizontally) {
                Text(text = "Hex")
                Text(text = "#${color.toHex()}")
            }
            Column(horizontalAlignment = CenterHorizontally) {
                Text(text = "Alpha")
                Text(text = alpha.toString())
            }
            Column(horizontalAlignment = CenterHorizontally) {
                Text(text = "Red")
                Text(text = red.toString())
            }
            Column(horizontalAlignment = CenterHorizontally) {
                Text(text = "Green")
                Text(text = green.toString())
            }
            Column(horizontalAlignment = CenterHorizontally) {
                Text(text = "Blue")
                Text(text = blue.toString())
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        Box(
            modifier = Modifier
                .size(80.dp, 50.dp)
                .clip(RoundedCornerShape(50))
                .border(0.3.dp, Color.LightGray, RoundedCornerShape(50))
                .transparentBackground(verticalBoxesAmount = 8)
                .background(color)
        )
        Spacer(modifier = Modifier.height(24.dp))
        Text(text = "Color Picker Type", color = Color.Black, fontWeight = FontWeight.Medium)
        Spacer(modifier = Modifier.height(20.dp))
        LazyVerticalGrid(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            columns = GridCells.Fixed(2),
            content = {
                item {
                    OutlinedButton(onClick = {
                        colorPickerType = ColorPickerType.Classic(initialColor = Color.Green)
                    }, shape = RoundedCornerShape(50)) {
                        Text(text = "Classic")
                    }
                }
                item {
                    OutlinedButton(onClick = {
                        colorPickerType = ColorPickerType.Circle(initialColor = Color.Green)
                    }, shape = RoundedCornerShape(50)) {
                        Text(text = "Circle")
                    }
                }
                item {
                    OutlinedButton(onClick = {
                        colorPickerType = ColorPickerType.Ring(initialColor = Color.Green)
                    }, shape = RoundedCornerShape(50)) {
                        Text(text = "Ring")
                    }
                }
                item {
                    OutlinedButton(onClick = {
                        colorPickerType = ColorPickerType.SimpleRing(initialColor = Color.Green)
                    }, shape = RoundedCornerShape(50)) {
                        Text(text = "Simple Ring")
                    }
                }
            })
        OutlinedButton(onClick = {
            showDialog = true
        }, shape = RoundedCornerShape(50)) {
            Text(text = "Open dialog")
        }
    }

}
