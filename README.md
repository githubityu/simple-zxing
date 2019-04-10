# simple-zxing
很简单的一个库，相册读取二维码，相机读取二维码。
[![](https://jitpack.io/v/githubityu/simple-zxing.svg)](https://jitpack.io/#githubityu/simple-zxing)
## 目录
-[功能特点](#功能特点)<br>
-[集成方式](#集成方式)<br>
-[常见错误](#常见错误)<br>
# 功能特点
* 1.打开相机扫描二维码
* 2.从相册读取图片获取二维码
* 3.生成二维码(或者去掉二维码白边)
* 4.扫一扫页面样式自己定
* 5.生成带图标的二维码
重要的事情说三遍记得添加权限
```
 <uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE" />
 <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
 <uses-permission android:name="android.permission.CAMERA" />
```
## 集成方式

方式一 compile引入

```
dependencies {
    implementation 'com.github.githubityu:simple-zxing:1.0'
}
```
项目根目录build.gradle加入

```
allprojects {
   repositories {
      jcenter()
      maven { url 'https://jitpack.io' }
   }
}
```

## 常见错误
问题一：
无法扫描或者读取二维码：
添加权限
