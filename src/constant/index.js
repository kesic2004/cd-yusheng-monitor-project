/**
 * 全局变量
 */
var constant = {}
/**
 * 定义常量Token
 */
constant.TOKEN = 'token'
/**
 * 定义常量Menu
 */
constant.MENU = 'menu'

/**
 * 定义常量Station
 */
constant.STATION = 'station'

/**
 * 定义常量登录路径
 */
constant.LOGIN_PATH = '/Login'

/**
 * 定义常量根路径
 */
constant.ROOT_PATH = '/'

/**
 * 开放路径
 */
constant.OPENED_FULLPATH = [
  '/Login'
]

/**
 * 灌装秤后端访问前缀
 */
constant.GAS_SERVER_PREFIX = '/gas'
constant.GAS_IMAGE_PREFIX = '/__images__'
/**
 * constant.GAS_SERVER_PREFIX + /attachment
 */
constant.GAS_SERVER_ATTACHMENT_PREFIX = '/gas/attachment'
/**
 * 常用图片扩展名，在Windows环境时自动忽略大小写
 */
constant.GAS_SERVER_ATTACHMENT_PICTURE_EXT = '.APNG, .AVIF, .GIF, .JPG, .JPEG, .JFIF, .PJPEG, .PJP, .PNG, .SVG, .WEBP'

export default constant
