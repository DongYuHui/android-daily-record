# Android 开发日常整理 #

记录日常工作与学习中的有用控件及一些常用的封装，比如各个项目中都可能用到的固定布局，常用工具类，对基础的 Activiy Fragment 的封装等等。

目前添加进的内容有如下：

### 工具类 ###

1. DiaplayUtil：用于屏幕单位之间的转化（dp、px、sp）
2. EncodeUtil：用于对字符串进行一些编码转化（MD5）
3. ImageLoader：将 Glide 包装了一层，易于维护修改
4. HttpUtil：通过封装 OkHttp 实现简单的网络请求（同步的 GET POST 方法，异步的 GET POST 方法）
5. JsonUtil：通过封装 Gson 库实现一些字符串到实体类的转化
6. AppInfoUtil：用于获取当前手机的一些信息的工具类
7. UriUtil：用于将 Uri 转换成 String 路径的工具类

### 矩形布局 ###

能够按固定比例显示的矩形布局，可以自定义宽高的比例，可以指明是宽度跟随高度还是高度跟随宽度。

### 圆形 ImageView ###

继承 ImageView 实现圆形图片，可以自定义边框宽度和颜色。

### RecyclerView ###

通过 ItemType 实现能够加载更多的 RecyclerView，并解决 ScrollView 嵌套 RecyclerView 在 Lollipop 以上失去滑动惯性的问题。

### UCrop 裁剪库 ###

UCrop 是一个非常强大的裁剪库，这里集成了它的 Demo，可配置性非常强，使用体验也十分出众。

### 九宫格图片 ###

实现常见社交应用中的九宫格图片。

### 仿微信多图片选择 ###

仿照微信的样式实现多图片选择功能。