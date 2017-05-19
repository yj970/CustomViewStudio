# CustomViewStudio
## 1.ClockView

Use：<br>
```Java
<demo.yj.cn.customviewstudio.view.ClockView
        android:layout_width="match_parent"
        android:layout_height="match_parent" />
```
![](https://github.com/yj970/CustomViewStudio/raw/master/app/src/main/res/mipmap-xxhdpi/Gif_ClockView.gif)  


## 2.SvgView

Use:<br>
```Java
<demo.yj.cn.customviewstudio.view.SvgView
        android:background="#fff"
        android:layout_width="400px"
        android:layout_height="200px"
        android:id="@+id/svg"/>
```
```Java
  SvgView svgView = (SvgView) findViewById(R.id.svg);
        String json = "{\"lines\":[[[94,105.47],[94,107.47]],[[215,125.47],[215,127.47]]]}";
        svgView.setTrace(json);
```
![](https://github.com/yj970/CustomViewStudio/raw/master/app/src/main/res/mipmap-xxhdpi/svg.png)  

