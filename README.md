[![GitHub license](https://img.shields.io/badge/license-Apache%202-blue.svg)](https://raw.githubusercontent.com/HITGIF/TextFieldBoxes/master/LICENSE)
[![](https://jitpack.io/v/kevinchung0921/MdiDrawable.svg)](https://jitpack.io/#kevinchung0921/MdiDrawable)

# MdiDrawable

This library is a simple [Material Design Icon](https://materialdesignicons.com/) icon generator. 
Just simply provide the icon name resource id, and the library will generate the icon drawable for you at run time.
You don't need to download each png file from website everytime.

#### Gradle:
```groovy
allprojects {
    repositories {
      ...
      maven { url 'https://jitpack.io' }
    }
}
```
```groovy
dependencies {
    implementation 'com.github.kevinchung0921:MdiDrawable:0.1.1'
}
```

#### Basic usage:

Use the following code when you need an alert icon programmaticaly 
```kotlin
imageview.background = MdiDrawableConfig()
     .stringId(R.string.mdi_alert_decagram)
     .create(context)
```

<img width='300' src='https://github.com/kevinchung0921/MdiDrawable/blob/main/images/Screenshot_1621901926.png' />


Or use MdiView class to create it in layout file.

```xml
<com.kevinchung.mdi_drawable.MdiView
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="@string/mdi_calendar"
            android:textSize="40dp"
            android:padding="4dp"
            app:bgColor = "@color/blue_grey_600"
            app:useGradient="true"
            app:cornerRadius="6dp"
            app:gradientStartColor="@color/blue_grey_600"
            app:gradientEndColor="@color/blue_grey_100"
            app:gradientOrientation="rightLeft"
            app:strokeWidth="1dp"
            app:strokeColor="@color/black"
            />

```

And put the drawable into an ImageView or to the any view which support background drawable.

The icon string resource name could found in and add prefix *"mdi_"* and change dash *"-"* to underscope *"_"*
![](https://github.com/kevinchung0921/MdiDrawable/blob/main/images/Screenshot%20from%202021-05-25%2007-41-30.png)


#### More settings:

``` kotlin
imageview.background = MdiDrawableConfig()
    .stringId(R.string.mdi_android)
    .size(60)
    .radius(60)
    .iconColor(Color.BLUE)
    .enableBackground(true)
    .useGradient(Color.DKGRAY, Color.LTGRAY)
    .stroke(width=2, color.BLACK, length=10, gap=2)
    .shadow(color=Color.BLACK, radius=4, dx=10, dy=10)
    .create(context)
```


#### Demo:

This project also come with a demo app which show different drawable configurations. 

<img width='300' src='https://github.com/kevinchung0921/MdiDrawable/blob/main/images/Screenshot_20220602_142905.png' />

And also provide a dialog allow you to create drawable at runtime.

<img width='300' src='https://github.com/kevinchung0921/MdiDrawable/blob/main/images/Screenshot_20220602_143319.png' />

#### Update Library:

If you need to use latest icon whenever Material Font updated, just delete the folder "mdi" under the library project folder and execute *"donwload.py"*. This will download the latest Material Design Font package and generate string resource table.
