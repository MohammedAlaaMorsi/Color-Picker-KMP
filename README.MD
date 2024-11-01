# ColorPicker Compose Multiplatform

🎨 Color Picker for compose multiplatform inspired from [compose-color-picker](https://github.com/mhssn95/compose-color-picker) by [mhssn95](https://github.com/mhssn95)

![colorPickers](/assets/colorPickers.png)


# Download

```groovy
implementation 'io.github.mohammedalaamorsi.colorpicker:colorpicker-runtime:1.0.0'
```
# Usage

```kotlin
ColorPicker {
    color = it
}
```

You can provide a type for the color picker as the following.

```kotlin
ColorPicker(
    type = ColorPickerType.Classic()
) {
    color = it
}
```
#### Color picker types
- Classic

  ```kotlin
  ColorPickerType.Classic(
      showAlphaBar = true
  )
  ```

- Circle

  ```kotlin
  ColorPickerType.Circle(
      showBrightnessBar = true,
      showAlphaBar = true,
      lightCenter = true
  )
  ```

  ![circleColorPickers](/assets/circleColorPicker.png)


- Ring

  ```kotlin
  ColorPickerType.Ring(
      ringWidth = 10.dp,
      previewRadius = 80.dp,
      showLightColorBar = true,
      showDarkColorBar = true,
      showAlphaBar = true,
      showColorPreview = true
  )
  ```



- Simple Ring

  ```kotlin
  ColorPickerType.SimpleRing(
      colorWidth = 20.dp,
      tracksCount = 5,
      sectorsCount = 24
  )
  ```

## Color Picker Dialog

```kotlin
ColorPickerDialog(
    show = showDialog,
    type = colorPickerType,
    properties = DialogProperties(),
    onDismissRequest = {
    	showDialog = false
    },
    onPickedColor = {
    	color = it
    },
)
```

## Useful Extensions

- ```kotlin
  val color = Color(0xffe1a1c1)
  val alpha = color.alpha() //return alpha value as integer => 255
  val red = color.red() //return red value as integer => 225
  val green = color.green() //return green value as integer => 161
  val blue = color.blue() //return blue value as integer => 193
  val hex = color.toHex() //return argb color as hex string
  val (alpha, red, green, blue) = color.argb() //return an array for all color channels value
  ```

- add `Modifier.transparentBackground` to draw behind your composable element a transparent effect background

  ```kotlin
  Box(
      modifier = Modifier
      .width(205.dp)
      .height(300.dp)
      .clip(RoundedCornerShape(32.dp))
      .transparentBackground(verticalBoxesCount = 16)
      .background(Color.Cyan.copy(alpha = 0.2f))
  )
  ```

  ![transparentBackground](/assets/transparentBackground.png)


## Before running!
 - check your system with [KDoctor](https://github.com/Kotlin/kdoctor)
 - install JDK 17 or higher on your machine
 - add `local.properties` file to the project root and set a path to Android SDK there

### Android
To run the application on android device/emulator:  
 - open project in Android Studio and run imported android run configuration

To build the application bundle:
 - run `./gradlew :sample:composeApp:assembleDebug`
 - find `.apk` file in `sample/composeApp/build/outputs/apk/debug/composeApp-debug.apk`

### Desktop
Run the desktop application: `./gradlew :sample:composeApp:run`

### iOS
To run the application on iPhone device/simulator:
 - Open `sample/iosApp/iosApp.xcproject` in Xcode and run standard configuration
 - Or use [Kotlin Multiplatform Mobile plugin](https://plugins.jetbrains.com/plugin/14936-kotlin-multiplatform-mobile) for Android Studio


# License

```
Copyright 2024 Mohammed Alaa

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```

