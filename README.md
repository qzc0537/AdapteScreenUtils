# AdapteScreenUtils
基于头条方案的屏幕适配库

[![](https://jitpack.io/v/qzc0537/AdapteScreenUtils.svg)](https://jitpack.io/#qzc0537/AdapteScreenUtils)

使用
--
1.project build.gradle下添加：
maven { url 'https://jitpack.io' }

如下：

```
allprojects {
    repositories {
        maven { url "https://jitpack.io" }
    }
}
```

2.app build.gradle下添加依赖 ：

```
implementation 'com.github.qzc0537:AdapteScreenUtils:1.0.9'
```

3.愉快的使用：
```
AdaptScreenUtils.adaptScreen(mContext, intArrayOf(360, 640), isVerticalSlide)
