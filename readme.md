## ##初始化

```plain
Utils.init(this,new Utils.Builder()
.debug(true)
        .dbInitHelper(new DBManager.InitHelper<DaoSession>() {
            @Override
            public DaoSession init() {
                DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper
                        (getApplicationContext(), "test.db", null); 
                DaoMaster daoMaster = new DaoMaster(devOpenHelper.getWritableDatabase());
                return daoMaster.newSession();
            }
        })
);
```

## 工具类API文档

### AppUtils(App相关)

```plain
installApp                        : 安装 App（支持 8.0）
isAppInstalled                    : 判断 App 是否安装
launchApp                         : 打开 App
relaunchApp                       : 重启 App
exitApp                           : 关闭应用
getAppIcon                        : 获取 App 图标
getAppPackageName                 : 获取 App 包名
getAppName                        : 获取 App 名称 
getAppVersionName                 : 获取 App 版本号
getAppVersionCode                 : 获取 App 版本码
getAppSignatures                  : 获取 App 签名
getAppInfo                        : 获取 App 信息
getAppsInfo                       : 获取所有已安装 App 信息
getApkInfo                        : 获取 Apk 信息
```
### CleanUtils（清除相关）

```plain
cleanInternalCache   : 清除内部缓存
cleanInternalFiles   : 清除内部文件
cleanInternalDbs     : 清除内部数据库
cleanInternalDbByName: 根据名称清除数据库
cleanInternalSp      : 清除内部 SP
cleanExternalCache   : 清除外部缓存
cleanCustomDir       : 清除自定义目录下的文件
```
### ConvertUtils（转换相关）

```plain
int2HexString, hexString2Int            : int 与 hexString 互转
bytes2Bits, bits2Bytes                  : bytes 与 bits 互转
bytes2Chars, chars2Bytes                : bytes 与 chars 互转
bytes2HexString, hexString2Bytes        : bytes 与 hexString 互转
bytes2String, string2Bytes              : bytes 与 string 互转
bytes2JSONObject, jsonObject2Bytes      : bytes 与 JSONObject 互转
bytes2JSONArray, jsonArray2Bytes        : bytes 与 JSONArray 互转
bytes2Parcelable, parcelable2Bytes      : bytes 与 Parcelable 互转
bytes2Object, serializable2Bytes        : bytes 与 Object 互转
bytes2Bitmap, bitmap2Bytes              : bytes 与 Bitmap 互转
memorySize2Byte, byte2MemorySize        : 以 unit 为单位的内存大小与字节数互转
byte2FitMemorySize                      : 字节数转合适内存大小
timeSpan2Millis, millis2TimeSpan        : 以 unit 为单位的时间长度与毫秒时间戳互转
millis2FitTimeSpan                      : 毫秒时间戳转合适时间长度
input2OutputStream, output2InputStream  : inputStream 与 outputStream 互转
inputStream2Bytes, bytes2InputStream    : inputStream 与 bytes 互转
outputStream2Bytes, bytes2OutputStream  : outputStream 与 bytes 互转
inputStream2String, string2InputStream  : inputStream 与 string 按编码互转
outputStream2String, string2OutputStream: outputStream 与 string 按编码互转
inputStream2Lines                       : inputStream 转 文本行
```
### DeviceUtils(设备相关)

```plain
isDeviceRooted              : 判断设备是否 rooted
isAdbEnabled                : 判断设备 ADB 是否可用
getSDKVersionName           : 获取设备系统版本号
getSDKVersionCode           : 获取设备系统版本码
getAndroidID                : 获取设备 AndroidID
getMacAddress               : 获取设备 MAC 地址
getManufacturer             : 获取设备厂商
getModel                    : 获取设备型号
getABIs                     : 获取设备 ABIs
isTablet                    : 判断是否是平板
isEmulator                  : 判断是否是模拟器
isDevelopmentSettingsEnabled: 开发者选项是否打开
getUniqueDeviceId           : 获取唯一设备 ID
getDeviceSN                 : 获取设备唯一SN
```
### EncodeUtils(编码解码相关)

```plain
urlEncode          : URL 编码
urlDecode          : URL 解码
base64Encode       : Base64 编码
base64Encode2String: Base64 编码
base64Decode       : Base64 解码
htmlEncode         : Html 编码
htmlDecode         : Html 解码
binaryEncode       : 二进制编码
binaryDecode       : 二进制解码
```
### EncryptUtils(加密解密相关)

```plain
encryptMD2, encryptMD2ToString                        : MD2 加密
encryptMD5, encryptMD5ToString                        : MD5 加密
encryptMD5File, encryptMD5File2String                 : MD5 加密文件
encryptSHA1, encryptSHA1ToString                      : SHA1 加密
encryptSHA224, encryptSHA224ToString                  : SHA224 加密
encryptSHA256, encryptSHA256ToString                  : SHA256 加密
encryptSHA384, encryptSHA384ToString                  : SHA384 加密
encryptSHA512, encryptSHA512ToString                  : SHA512 加密
encryptHmacMD5, encryptHmacMD5ToString                : HmacMD5 加密
encryptHmacSHA1, encryptHmacSHA1ToString              : HmacSHA1 加密
encryptHmacSHA224, encryptHmacSHA224ToString          : HmacSHA224 加密
encryptHmacSHA256, encryptHmacSHA256ToString          : HmacSHA256 加密
encryptHmacSHA384, encryptHmacSHA384ToString          : HmacSHA384 加密
encryptHmacSHA512, encryptHmacSHA512ToString          : HmacSHA512 加密
encryptDES, encryptDES2HexString, encryptDES2Base64   : DES 加密
decryptDES, decryptHexStringDES, decryptBase64DES     : DES 解密
encrypt3DES, encrypt3DES2HexString, encrypt3DES2Base64: 3DES 加密
decrypt3DES, decryptHexString3DES, decryptBase64_3DES : 3DES 解密
encryptAES, encryptAES2HexString, encryptAES2Base64   : AES 加密
decryptAES, decryptHexStringAES, decryptBase64AES     : AES 解密
encryptRSA, encryptRSA2HexString, encryptRSA2Base64   : RSA 加密
decryptRSA, decryptHexStringRSA, decryptBase64RSA     : RSA 解密
rc4                                                   : rc4 加解密
```
### FileIOUtils(文件流相关)

```plain
writeFileFromIS            : 将输入流写入文件
writeFileFromBytesByStream : 将字节数组写入文件
writeFileFromBytesByChannel: 将字节数组写入文件
writeFileFromBytesByMap    : 将字节数组写入文件
writeFileFromString        : 将字符串写入文件
readFile2List              : 读取文件到字符串链表中
readFile2String            : 读取文件到字符串中
readFile2BytesByStream     : 读取文件到字节数组中
readFile2BytesByChannel    : 读取文件到字节数组中
readFile2BytesByMap        : 读取文件到字节数组中
setBufferSize              : 设置缓冲区尺寸
```
### FileUtils(文件相关)

```plain
getFileByPath             : 根据文件路径获取文件
isFileExists              : 判断文件是否存在
rename                    : 重命名文件
isDir                     : 判断是否是目录
isFile                    : 判断是否是文件
createOrExistsDir         : 判断目录是否存在，不存在则判断是否创建成功
createOrExistsFile        : 判断文件是否存在，不存在则判断是否创建成功
createFileByDeleteOldFile : 判断文件是否存在，存在则在创建之前删除
copy                      : 复制文件或目录
move                      : 移动文件或目录
delete                    : 删除文件或目录
deleteAllInDir            : 删除目录下所有内容
deleteFilesInDir          : 删除目录下所有文件
deleteFilesInDirWithFilter: 删除目录下所有过滤的文件
listFilesInDir            : 获取目录下所有文件
listFilesInDirWithFilter  : 获取目录下所有过滤的文件
getFileLastModified       : 获取文件最后修改的毫秒时间戳
getFileCharsetSimple      : 简单获取文件编码格式
getFileLines              : 获取文件行数
getSize                   : 获取文件或目录大小
getLength                 : 获取文件或目录长度
getFileMD5                : 获取文件的 MD5 校验码
getFileMD5ToString        : 获取文件的 MD5 校验码
getDirName                : 根据全路径获取最长目录
getFileName               : 根据全路径获取文件名
getFileNameNoExtension    : 根据全路径获取文件名不带拓展名
getFileExtension          : 根据全路径获取文件拓展名
notifySystemToScan        : 通知系统扫描文件
getFsTotalSize            : 获取文件系统总大小
getFsAvailableSize        : 获取文件系统可用大小
```
### FragmentUtils(Fragment相关)

```plain
add                   : 增加 fragment
show                  : 显示 fragment
hide                  : 隐藏 fragment
showHide              : 先显示后隐藏 fragment
replace               : 替换 fragment
pop                   : 出栈 fragment
popTo                 : 出栈到指定 fragment
popAll                : 出栈所有 fragment
remove                : 移除 fragment
removeTo              : 移除到指定 fragment
removeAll             : 移除所有 fragment
getTop                : 获取顶部 fragment
getTopInStack         : 获取栈中顶部 fragment
getTopShow            : 获取顶部可见 fragment
getTopShowInStack     : 获取栈中顶部可见 fragment
getFragments          : 获取同级别的 fragment
getFragmentsInStack   : 获取同级别栈中的 fragment
getAllFragments       : 获取所有 fragment
getAllFragmentsInStack: 获取栈中所有 fragment
findFragment          : 查找 fragment
dispatchBackPress     : 处理 fragment 回退键
setBackgroundColor    : 设置背景色
setBackgroundResource : 设置背景资源
setBackground         : 设置背景
```
### GsonUtils(Gson相关)

```plain
setGsonDelegate: 设置默认的 Gson 代理对象
setGson        : 设置 Gson 对象
getGson        : 获取 Gson 对象
toJson         : 对象转 Json 串
fromJson       : Json 串转对象
getListType    : 获取链表类型
getSetType     : 获取集合类型
getMapType     : 获取字典类型
getArrayType   : 获取数组类型
getType        : 获取类型
```
### IntentUtils(Intent相关)

```plain
isIntentAvailable                : 判断意图是否可用
getInstallAppIntent              : 获取安装 App 的意图
getUninstallAppIntent            : 获取卸载 App 的意图
getLaunchAppIntent               : 获取打开 App 的意图
getLaunchAppDetailsSettingsIntent: 获取 App 具体设置的意图
getShareTextIntent               : 获取分享文本的意图
getShareImageIntent              : 获取分享图片的意图
getShareTextImageIntent          : 获取分享图文的意图
getComponentIntent               : 获取其他应用组件的意图
getShutdownIntent                : 获取关机的意图
getCaptureIntent                 : 获取拍照的意图
```
### KeyboardUtils(软键盘相关)

```plain
showSoftInput                     : 显示软键盘
hideSoftInput                     : 隐藏软键盘
toggleSoftInput                   : 切换键盘显示与否状态
isSoftInputVisible                : 判断软键盘是否可见
registerSoftInputChangedListener  : 注册软键盘改变监听器
unregisterSoftInputChangedListener: 注销软键盘改变监听器
fixAndroidBug5497                 : 修复安卓 5497 BUG
fixSoftInputLeaks                 : 修复软键盘内存泄漏
clickBlankArea2HideSoftInput      : 点击屏幕空白区域隐藏软键盘
```
### NetworkUtils(网络相关)

```plain
openWirelessSettings                    : 打开网络设置界面
isConnected                             : 判断网络是否连接
isAvailable[Async]                      : 判断网络是否可用
isAvailableByPing[Async]                : 用 ping 判断网络是否可用
isAvailableByDns[Async]                 : 用 DNS 判断网络是否可用
getMobileDataEnabled                    : 判断移动数据是否打开
isMobileData                            : 判断网络是否是移动数据
is4G                                    : 判断网络是否是 4G
getWifiEnabled                          : 判断 wifi 是否打开
setWifiEnabled                          : 打开或关闭 wifi
isWifiConnected                         : 判断 wifi 是否连接状态
isWifiAvailable[Async]                  : 判断 wifi 数据是否可用
getNetworkOperatorName                  : 获取移动网络运营商名称
getNetworkType                          : 获取当前网络类型
getIPAddress[Async]                     : 获取 IP 地址
getDomainAddress[Async]                 : 获取域名 IP 地址
getIpAddressByWifi                      : 根据 WiFi 获取网络 IP 地址
getGatewayByWifi                        : 根据 WiFi 获取网关 IP 地址
getNetMaskByWifi                        : 根据 WiFi 获取子网掩码 IP 地址
getServerAddressByWifi                  : 根据 WiFi 获取服务端 IP 地址
registerNetworkStatusChangedListener    : 注册网络状态改变监听器
isRegisteredNetworkStatusChangedListener: 判断是否注册网络状态改变监听器
unregisterNetworkStatusChangedListener  : 注销网络状态改变监听器
getWifiScanResult                       : 获取 WIFI 列表
addOnWifiChangedConsumer                : 增加 WIFI 改变监听
removeOnWifiChangedConsumer             : 移除 WIFI 改变监听
```
### ObjectUtils(对象相关)

```plain
isEmpty          : 判断对象是否为空
isNotEmpty       : 判断对象是否非空
equals           : 判断对象是否相等
compare          : 比较对象大小
requireNonNull(s): 要求对象非空
getOrDefault     : 获取非空或默认对象
toString         : 转字符串
hashCode(s)      : 获取对象哈希值
```
### PermissionUtils(权限相关)

```plain
request : 获取权限
```
### ScreenUtils(屏幕相关)

```plain
getScreenWidth     : 获取屏幕的宽度（单位：px）
getScreenHeight    : 获取屏幕的高度（单位：px）
getAppScreenWidth  : 获取应用屏幕的宽度（单位：px）
getAppScreenHeight : 获取应用屏幕的高度（单位：px）
getScreenDensity   : 获取屏幕密度
getScreenDensityDpi: 获取屏幕密度 DPI
setFullScreen      : 设置屏幕为全屏
setNonFullScreen   : 设置屏幕为非全屏
toggleFullScreen   : 切换屏幕为全屏与否状态
isFullScreen       : 判断屏幕是否为全屏
setLandscape       : 设置屏幕为横屏
setPortrait        : 设置屏幕为竖屏
isLandscape        : 判断是否横屏
isPortrait         : 判断是否竖屏
getScreenRotation  : 获取屏幕旋转角度
screenShot         : 截屏
isScreenLock       : 判断是否锁屏
setSleepDuration   : 设置进入休眠时长
getSleepDuration   : 获取进入休眠时长
```
### ServiceUtils(服务相关)

```plain
getAllRunningServices: 获取所有运行的服务
startService         : 启动服务
stopService          : 停止服务
bindService          : 绑定服务
unbindService        : 解绑服务
isServiceRunning     : 判断服务是否运行
```
### ShellUtils(命令相关)

```plain
execCmd[Async]: 执行命令
```
### SizeUtils(尺寸相关)

```plain
dp2px, px2dp     : dp 与 px 转换
sp2px, px2sp     : sp 与 px 转换
applyDimension   : 各种单位转换
forceGetViewSize : 在 onCreate 中获取视图的尺寸
measureView      : 测量视图尺寸
getMeasuredWidth : 获取测量视图宽度
getMeasuredHeight: 获取测量视图高度
```
### SpUtils(Sp相关)

```plain
getInstance        : 获取 SP 实例
Instance.put       : SP 中写入数据
Instance.getString : SP 中读取 String
Instance.getInt    : SP 中读取 int
Instance.getLong   : SP 中读取 long
Instance.getFloat  : SP 中读取 float
Instance.getBoolean: SP 中读取 boolean
Instance.getAll    : SP 中获取所有键值对
Instance.contains  : SP 中是否存在该 key
Instance.remove    : SP 中移除该 key
Instance.clear     : SP 中清除所有数据
```
### StringUtils(字符串相关)

```plain
isEmpty         : 判断字符串是否为 null 或长度为 0
isTrimEmpty     : 判断字符串是否为 null 或全为空格
isSpace         : 判断字符串是否为 null 或全为空白字符
equals          : 判断两字符串是否相等
equalsIgnoreCase: 判断两字符串忽略大小写是否相等
null2Length0    : null 转为长度为 0 的字符串
length          : 返回字符串长度
upperFirstLetter: 首字母大写
lowerFirstLetter: 首字母小写
reverse         : 反转字符串
toDBC           : 转化为半角字符
toSBC           : 转化为全角字符
getString       : 获取字符资源
getStringArray  : 获取字符数组资源
format          : 格式化字符串
```
### ThreadUtils(线程相关)

```plain
isMainThread            : 判断当前是否主线程
getMainHandler          : 获取主线程 Handler
runOnUiThread           : 运行在主线程
runOnUiThreadDelayed    : 延时运行在主线程
getFixedPool            : 获取固定线程池
getSinglePool           : 获取单线程池
getCachedPool           : 获取缓冲线程池
getIoPool               : 获取 IO 线程池
getCpuPool              : 获取 CPU 线程池
executeByFixed          : 在固定线程池执行任务
executeByFixedWithDelay : 在固定线程池延时执行任务
executeByFixedAtFixRate : 在固定线程池按固定频率执行任务
executeBySingle         : 在单线程池执行任务
executeBySingleWithDelay: 在单线程池延时执行任务
executeBySingleAtFixRate: 在单线程池按固定频率执行任务
executeByCached         : 在缓冲线程池执行任务
executeByCachedWithDelay: 在缓冲线程池延时执行任务
executeByCachedAtFixRate: 在缓冲线程池按固定频率执行任务
executeByIo             : 在 IO 线程池执行任务
executeByIoWithDelay    : 在 IO 线程池延时执行任务
executeByIoAtFixRate    : 在 IO 线程池按固定频率执行任务
executeByCpu            : 在 CPU 线程池执行任务
executeByCpuWithDelay   : 在 CPU 线程池延时执行任务
executeByCpuAtFixRate   : 在 CPU 线程池按固定频率执行任务
executeByCustom         : 在自定义线程池执行任务
executeByCustomWithDelay: 在自定义线程池延时执行任务
executeByCustomAtFixRate: 在自定义线程池按固定频率执行任务
cancel                  : 取消任务的执行
setDeliver              : 设置任务结束后交付的线程
```
### TimeUtils(时间相关)

```plain
getSafeDateFormat       : 获取安全的日期格式
millis2String           : 将时间戳转为时间字符串
string2Millis           : 将时间字符串转为时间戳
string2Date             : 将时间字符串转为 Date 类型
date2String             : 将 Date 类型转为时间字符串
date2Millis             : 将 Date 类型转为时间戳
millis2Date             : 将时间戳转为 Date 类型
getTimeSpan             : 获取两个时间差（单位：unit）
getFitTimeSpan          : 获取合适型两个时间差
getNowMills             : 获取当前毫秒时间戳
getNowString            : 获取当前时间字符串
getNowDate              : 获取当前 Date
getTimeSpanByNow        : 获取与当前时间的差（单位：unit）
getFitTimeSpanByNow     : 获取合适型与当前时间的差
getFriendlyTimeSpanByNow: 获取友好型与当前时间的差
getMillis               : 获取与给定时间等于时间差的时间戳
getString               : 获取与给定时间等于时间差的时间字符串
getDate                 : 获取与给定时间等于时间差的 Date
getMillisByNow          : 获取与当前时间等于时间差的时间戳
getStringByNow          : 获取与当前时间等于时间差的时间字符串
getDateByNow            : 获取与当前时间等于时间差的 Date
isToday                 : 判断是否今天
isLeapYear              : 判断是否闰年
getChineseWeek          : 获取中式星期
getUSWeek               : 获取美式式星期
isAm                    : 判断是否上午
isPm                    : 判断是否下午
getValueByCalendarField : 根据日历字段获取值
getChineseZodiac        : 获取生肖
getZodiac               : 获取星座
```
### UriUtils(Uri相关)

```plain
res2Uri  : res 转 uri
file2Uri : file 转 uri
uri2File : uri 转 file
uri2Bytes: uri 转 bytes
```
### ZipUtils(文件压缩相关)
```plain
zipFiles          : 批量压缩文件
zipFile           : 压缩文件
unzipFile         : 解压文件
unzipFileByKeyword: 解压带有关键字的文件
getFilesPath      : 获取压缩文件中的文件路径链表
getComments       : 获取压缩文件中的注释链表
```