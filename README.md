# ViewPager切换动画

```java
/**
 * ViewPager切换动画实现：
 * 1、API 3.0以上的需求，直接使用官方Demo去setPageTransformer()
 * 2、API 3.0以下的需求，修改官方ViewPager源码+使用nineoldandroids.jar包代替属性动画（属性动画3.0以上才有）
 * 3、自定义ViewPager（无需修改ViewPager源码）
 * 4、学习开源项目JazzyViewPager
 */
```

Google官方动画一：<br/>
![img01](https://github.com/ykmeory/ViewPagerAnim/blob/master/img_folder/img01.jpg "DepthPageTransformer")
<br/>

Google官方动画二：<br/>
![img02](https://github.com/ykmeory/ViewPagerAnim/blob/master/img_folder/img02.jpg "ZoomOutPageTransformer")
<br/>

修改ViewPager源码的自定义旋转动画：<br/>
![img03](https://github.com/ykmeory/ViewPagerAnim/blob/master/img_folder/img03.jpg "RotatePageTransformer")
<br/>

自定义ViewPager的旋转动画：<br/>
![img04](https://github.com/ykmeory/ViewPagerAnim/blob/master/img_folder/img04.jpg "custom_RotatePageTransformer")
<br/>
