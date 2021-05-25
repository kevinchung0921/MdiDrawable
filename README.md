[![GitHub license](https://img.shields.io/badge/license-Apache%202-blue.svg)](https://raw.githubusercontent.com/HITGIF/TextFieldBoxes/master/LICENSE)

# MdiDrawable

This library is a simple [material design icon](https://materialdesignicons.com/) icon generator. 
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
    implementation 'com.github.kevinchung0921:MdiDrawable:0.1.0'
}
```

#### Basic usage:
For example, use the following code when you need an alert icon.

```
MdiDrawable(context)
     .stringId(R.string.mdi_alert_decagram)
     .create()
```

<img width='300' src='https://github.com/kevinchung0921/MdiDrawable/blob/main/images/Screenshot_1621901926.png' />

And put the drawable into an ImageView or to the any view which support background drawable.

The icon string resource name could found in and add prefix *"mdi_"* and change dash *"-"* to underscope *"_"*
![](https://github.com/kevinchung0921/MdiDrawable/blob/main/images/Screenshot%20from%202021-05-25%2007-41-30.png)


#### Other settings:

```
MdiDrawable(context)
    .stringId(R.string.mdi_android)
    .size(60)
    .iconColor(Color.BLUE)
    .enableBackground(true)
    .useGradient(Color.DKGRAY, Color.LTGRAY)
    .radius(60)
    .create()
```


This project also come with a demo app which show different drawable configurations. 

<img width='300' src='https://github.com/kevinchung0921/MdiDrawable/blob/main/images/Screenshot_1621898935.png' />

And also provide UI allow you to create your own drawable.

<img width='300' src='https://github.com/kevinchung0921/MdiDrawable/blob/main/images/Screenshot_1621818157.png' />
