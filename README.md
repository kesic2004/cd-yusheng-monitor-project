# gas_client_h5

> gas project html5 client

## Build Setup

``` bash
# install dependencies
npm install

# serve with hot reload at localhost:8080
npm run dev

# build for production with minification
npm run build

# build for production and view the bundle analyzer report
npm run build --report
```

For a detailed explanation on how things work, check out the [guide](http://vuejs-templates.github.io/webpack/) and [docs for vue-loader](http://vuejs.github.io/vue-loader).
# steps
```bash
npm install -g @vue/cli --registry=https://registry.npm.taobao.org
npm i -g @vue/cli-init
```bash

[gasvue2@VM-4-8-centos ~]$ vue init webpack gas_client_h5

/bin/sh: git: command not found
? Project name gas_client_h5
? Project description gas project html5 client
? Author radl
? Vue build standalone
? Install vue-router? Yes
? Use ESLint to lint your code? Yes
? Pick an ESLint preset Standard
? Set up unit tests No
? Setup e2e tests with Nightwatch? No
? Should we run `npm install` for you after the project has been created? (recommended) npm


``` bash
vue init webpack gas_client_h5
cd gas_client_h5
npm install
npm i element-ui -S
npm install echarts
npm install axios
npm install uuid4
npm install element-china-area-data -S
npm install vue-directive-image-previewer -S
```

Download the Vue Devtools extension for a better development experience:
https://github.com/vuejs/vue-devtools

# 路径信息
@/components/main   菜单工作空间
@/components/charge 菜单罐装系统
@/components/site   菜单站点信息
@/components/bottle 菜单气瓶配送

备份在D:

# https
From : https://blog.csdn.net/l508742729/article/details/107820099
```bash
D:\OpenSSL-Win64\bin\openssl.exe genrsa -out E:\workspace\userprofile\node-v12_19_0-win-x64\vue2-user-profile\gas_client_h5\build\private.key 2048
ser-profile\gas_client_h5\build\private.key 2048
Generating RSA private key, 2048 bit long modulus (2 primes)
..+++++
......................................+++++
e is 65537 (0x010001)

D:\OpenSSL-Win64\bin\openssl.exe req -new -key E:\workspace\userprofile\node-v12_19_0-win-x64\vue2-user-profile\gas_client_h5\build\private.key -out E:\workspace\userprofile\node-v12_19_0-win-x64\vue2-user-profile\gas_client_h5\build\crs.key
You are about to be asked to enter information that will be incorporated
into your certificate request.
What you are about to enter is what is called a Distinguished Name or a DN.
There are quite a few fields but you can leave some blank
For some fields there will be a default value,
If you enter '.', the field will be left blank.
-----
Country Name (2 letter code) [AU]:cn
State or Province Name (full name) [Some-State]:sichuan
Locality Name (eg, city) []:chengtu
Organization Name (eg, company) [Internet Widgits Pty Ltd]:cdxinrui
Organizational Unit Name (eg, section) []:company
Common Name (e.g. server FQDN or YOUR name) []:radl
Email Address []:wx3121956595@qq.com

Please enter the following 'extra' attributes
to be sent with your certificate request
A challenge password []:jyXR{js}2023
An optional company name []:cdxinrui

D:\OpenSSL-Win64\bin\openssl.exe x509 -req -days 3650 -in E:\workspace\userprofile\node-v12_19_0-win-x64\vue2-user-profile\gas_client_h5\build\crs.key -signkey E:\workspace\userprofile\node-v12_19_0-win-x64\vue2-user-profile\gas_client_h5\build\private.key -out E:\workspace\userprofile\node-v12_19_0-win-x64\vue2-user-profile\gas_client_h5\build\file.crt
Signature ok
subject=C = cn, ST = sichuan, L = chengtu, O = cdxinrui, OU = company, CN = radl, emailAddress = wx3121956595@qq.com
Getting Private key

[css]
https://cdn.staticfile.org/ionicons/2.0.1/css/ionicons.min.css 20231111

[uuid4]
E:\workspace\userprofile\node-v12_19_0-win-x64\vue2-user-profile>npm install uuid4
npm WARN saveError ENOENT: no such file or directory, open 'E:\workspace\userprofile\node-v12_19_0-win-x64\vue2-user-profile\package.json'
npm notice created a lockfile as package-lock.json. You should commit this file.
npm WARN enoent ENOENT: no such file or directory, open 'E:\workspace\userprofile\node-v12_19_0-win-x64\vue2-user-profile\package.json'
npm WARN vue2-user-profile No description
npm WARN vue2-user-profile No repository field.
npm WARN vue2-user-profile No README data
npm WARN vue2-user-profile No license field.

+ uuid4@2.0.3
added 1 package from 1 contributor in 5.522s

E:\workspace\userprofile\node-v12_19_0-win-x64\vue2-user-profile>echo %errorlevel%
0

E:\workspace\userprofile\node-v12_19_0-win-x64\vue2-user-profile\gas_client_h5>npm install element-china-area-data -S
npm WARN update-browserslist-db@1.0.13 requires a peer of browserslist@>= 4.21.0 but none is installed. You must install peer dependencies yourself.
npm WARN optional SKIPPING OPTIONAL DEPENDENCY: fsevents@1.2.13 (node_modules\webpack-dev-server\node_modules\fsevents):
npm WARN notsup SKIPPING OPTIONAL DEPENDENCY: Unsupported platform for fsevents@1.2.13: wanted {"os":"darwin","arch":"any"} (current: {"os":"win32","arch":"x64"})
npm WARN optional SKIPPING OPTIONAL DEPENDENCY: fsevents@2.3.3 (node_modules\fsevents):
npm WARN notsup SKIPPING OPTIONAL DEPENDENCY: Unsupported platform for fsevents@2.3.3: wanted {"os":"darwin","arch":"any"} (current: {"os":"win32","arch":"x64"})
npm WARN optional SKIPPING OPTIONAL DEPENDENCY: fsevents@1.2.13 (node_modules\watchpack-chokidar2\node_modules\fsevents):
npm WARN notsup SKIPPING OPTIONAL DEPENDENCY: Unsupported platform for fsevents@1.2.13: wanted {"os":"darwin","arch":"any"} (current: {"os":"win32","arch":"x64"})

+ element-china-area-data@6.1.0
added 2 packages from 1 contributor in 272.484s

95 packages are looking for funding
  run `npm fund` for details


E:\workspace\userprofile\node-v12_19_0-win-x64\vue2-user-profile\gas_client_h5>echo %errorlevel%
0
[gasvue2@VM-4-8-centos gas_client_h5]$ npm install element-china-area-data -S
npm WARN update-browserslist-db@1.0.13 requires a peer of browserslist@>= 4.21.0 but none is installed. You must install peer dependencies yourself.
npm WARN optional SKIPPING OPTIONAL DEPENDENCY: fsevents@2.3.3 (node_modules/fsevents):
npm WARN notsup SKIPPING OPTIONAL DEPENDENCY: Unsupported platform for fsevents@2.3.3: wanted {"os":"darwin","arch":"any"} (current: {"os":"linux","arch":"x64"})
npm WARN optional SKIPPING OPTIONAL DEPENDENCY: fsevents@1.2.13 (node_modules/watchpack-chokidar2/node_modules/fsevents):
npm WARN notsup SKIPPING OPTIONAL DEPENDENCY: Unsupported platform for fsevents@1.2.13: wanted {"os":"darwin","arch":"any"} (current: {"os":"linux","arch":"x64"})
npm WARN optional SKIPPING OPTIONAL DEPENDENCY: fsevents@1.2.13 (node_modules/webpack-dev-server/node_modules/fsevents):
npm WARN notsup SKIPPING OPTIONAL DEPENDENCY: Unsupported platform for fsevents@1.2.13: wanted {"os":"darwin","arch":"any"} (current: {"os":"linux","arch":"x64"})

+ element-china-area-data@6.1.0
added 2 packages from 1 contributor and audited 1467 packages in 17.289s

95 packages are looking for funding
  run `npm fund` for details

found 128 vulnerabilities (2 low, 105 moderate, 18 high, 3 critical)
  run `npm audit fix` to fix them, or `npm audit` for details
[gasvue2@VM-4-8-centos gas_client_h5]$ echo $?
0

[20231219]
Microsoft Windows [Version 10.0.19045.3803]
(c) Microsoft Corporation. All rights reserved.

E:\workspace\userprofile\node-v12_19_0-win-x64\vue2-user-profile>npm install vue-directive-image-previewer -S
npm WARN saveError ENOENT: no such file or directory, open 'E:\workspace\userprofile\node-v12_19_0-win-x64\vue2-user-profile\package.json'
npm WARN enoent ENOENT: no such file or directory, open 'E:\workspace\userprofile\node-v12_19_0-win-x64\vue2-user-profile\package.json'
npm WARN vue2-user-profile No description
npm WARN vue2-user-profile No repository field.
npm WARN vue2-user-profile No README data
npm WARN vue2-user-profile No license field.

+ vue-directive-image-previewer@2.2.2
added 11 packages from 45 contributors in 24.085s

2 packages are looking for funding
  run `npm fund` for details


E:\workspace\userprofile\node-v12_19_0-win-x64\vue2-user-profile>echo %errorlevel%
0

E:\workspace\userprofile\node-v12_19_0-win-x64\vue2-user-profile>npm fund
E:\workspace\userprofile\node-v12_19_0-win-x64\vue2-user-profile
+-- https://opencollective.com/postcss/
| `-- postcss@8.4.32
+-- https://tidelift.com/funding/github/npm/postcss
| `-- postcss@8.4.32
`-- https://github.com/sponsors/ai
  `-- postcss@8.4.32, nanoid@3.3.7


E:\workspace\userprofile\node-v12_19_0-win-x64\vue2-user-profile>

Microsoft Windows [Version 10.0.19045.3803]
(c) Microsoft Corporation. All rights reserved.

E:\workspace\userprofile\node-v12_19_0-win-x64\vue2-user-profile>cd gas_client_h5

E:\workspace\userprofile\node-v12_19_0-win-x64\vue2-user-profile\gas_client_h5>npm install vue-directive-image-previewer -S
npm WARN update-browserslist-db@1.0.13 requires a peer of browserslist@>= 4.21.0 but none is installed. You must install peer dependencies yourself.
npm WARN optional SKIPPING OPTIONAL DEPENDENCY: fsevents@1.2.13 (node_modules\webpack-dev-server\node_modules\fsevents):
npm WARN notsup SKIPPING OPTIONAL DEPENDENCY: Unsupported platform for fsevents@1.2.13: wanted {"os":"darwin","arch":"any"} (current: {"os":"win32","arch":"x64"})
npm WARN optional SKIPPING OPTIONAL DEPENDENCY: fsevents@2.3.3 (node_modules\fsevents):
npm WARN notsup SKIPPING OPTIONAL DEPENDENCY: Unsupported platform for fsevents@2.3.3: wanted {"os":"darwin","arch":"any"} (current: {"os":"win32","arch":"x64"})
npm WARN optional SKIPPING OPTIONAL DEPENDENCY: fsevents@1.2.13 (node_modules\watchpack-chokidar2\node_modules\fsevents):
npm WARN notsup SKIPPING OPTIONAL DEPENDENCY: Unsupported platform for fsevents@1.2.13: wanted {"os":"darwin","arch":"any"} (current: {"os":"win32","arch":"x64"})

+ vue-directive-image-previewer@2.2.2
added 1 package from 1 contributor in 10.763s

95 packages are looking for funding
  run `npm fund` for details


E:\workspace\userprofile\node-v12_19_0-win-x64\vue2-user-profile\gas_client_h5>echo %errorlevel%
0

E:\workspace\userprofile\node-v12_19_0-win-x64\vue2-user-profile\gas_client_h5>npm fund
gas_client_h5@1.0.0
+-- https://opencollective.com/browserslist
| `-- caniuse-lite@1.0.30001561, browserslist@4.22.1, update-browserslist-db@1.0.13
+-- https://tidelift.com/funding/github/npm/caniuse-lite
| `-- caniuse-lite@1.0.30001561
+-- https://github.com/sponsors/ai
| `-- caniuse-lite@1.0.30001561, browserslist@4.22.1, update-browserslist-db@1.0.13, postcss@8.4.31, nanoid@3.3.6
+-- https://github.com/sponsors/RubenVerborgh
| `-- follow-redirects@1.15.3
+-- https://github.com/sponsors/sindresorhus
| `-- is-finite@1.1.0
+-- https://github.com/sponsors/ljharb
| `-- minimist@1.2.8, defined@1.0.1, array-includes@3.1.7, array.prototype.findlastindex@1.2.3, array.prototype.flat@1.3.2, array.prototype.flatmap@1.3.2, is-core-module@2.13.1, object.fromentries@2.0.7, object.values@1.1.7, call-bind@1.0.5, define-properties@1.2.1, es-abstract@1.22.3, get-intrinsic@1.2.2, is-string@1.0.7, function-bind@1.1.2, gopd@1.0.1, has-property-descriptors@1.0.1, array-buffer-byte-length@1.0.0, arraybuffer.prototype.slice@1.0.2, available-typed-arrays@1.0.5, es-to-primitive@1.2.1, function.prototype.name@1.1.6, get-symbol-description@1.0.0, globalthis@1.0.3, has-proto@1.0.1, has-symbols@1.0.3, is-array-buffer@3.0.2, is-callable@1.2.7, is-negative-zero@2.0.2, is-regex@1.1.4, is-shared-array-buffer@1.0.2, is-typed-array@1.1.12, is-weakref@1.0.2, object-inspect@1.13.1, object.assign@4.1.4, regexp.prototype.flags@1.5.1, safe-array-concat@1.0.1, safe-regex-test@1.0.0, string.prototype.trim@1.2.8, string.prototype.trimend@1.0.7, string.prototype.trimstart@1.0.7, typed-array-byte-length@1.0.0, typed-array-byte-offset@1.0.0, typed-array-length@1.0.4, unbox-primitive@1.0.2, which-typed-array@1.1.13, has-tostringtag@1.0.0, is-date-object@1.0.5, is-symbol@1.0.4, functions-have-names@1.2.3, side-channel@1.0.4, has-bigints@1.0.2, which-boxed-primitive@1.0.2, is-bigint@1.0.4, is-boolean-object@1.1.2, is-number-object@1.0.7, resolve@1.22.8, supports-preserve-symlinks-flag@1.0.0, util.promisify@1.0.1, object.getownpropertydescriptors@2.1.7, array.prototype.reduce@1.0.6, qs@6.11.2, qs@6.11.0, deep-equal@1.1.1, is-arguments@1.1.1, object-is@1.1.5
+-- https://github.com/sponsors/isaacs
| `-- glob@7.2.3
+-- https://github.com/sponsors/wooorm
| `-- vendors@1.0.4
+-- https://github.com/sponsors/epoberezkin
| `-- ajv@6.12.6
+-- https://github.com/sponsors/fb55
| `-- css-select@4.3.0, htmlparser2@6.1.0, css-what@6.1.0, domelementtype@2.3.0, css-what@3.4.2
+-- https://github.com/fb55/htmlparser2?sponsor=1
| `-- htmlparser2@6.1.0
+-- https://github.com/fb55/domhandler?sponsor=1
| `-- domhandler@4.3.1
+-- https://github.com/fb55/domutils?sponsor=1
| `-- domutils@2.8.0
+-- https://github.com/fb55/nth-check?sponsor=1
| `-- nth-check@2.1.1
+-- https://github.com/cheeriojs/dom-serializer?sponsor=1
| `-- dom-serializer@1.4.1
+-- https://github.com/fb55/entities?sponsor=1
| `-- entities@2.2.0
+-- https://opencollective.com/postcss/
| `-- postcss@7.0.39, postcss-load-config@2.1.2, postcss@8.4.31
+-- https://tidelift.com/funding/github/npm/browserslist
| `-- browserslist@4.22.1, update-browserslist-db@1.0.13
+-- https://tidelift.com/funding/github/npm/postcss
| `-- postcss@8.4.31
+-- https://github.com/sponsors/feross
| `-- base64-js@1.5.1, ieee754@1.2.1, safe-buffer@5.2.1
+-- https://www.patreon.com/feross
| `-- base64-js@1.5.1, ieee754@1.2.1, safe-buffer@5.2.1
+-- https://feross.org/support
| `-- base64-js@1.5.1, ieee754@1.2.1, safe-buffer@5.2.1
+-- https://paulmillr.com/funding/
| `-- chokidar@3.5.3, async-each@1.0.6
+-- https://github.com/sponsors/jonschlinkert
| `-- picomatch@2.3.1
`-- https://tidelift.com/funding/github/npm/loglevel
  `-- loglevel@1.8.1

[20231221]
Microsoft Windows [Version 10.0.19045.3803]
(c) Microsoft Corporation. All rights reserved.

E:\workspace\userprofile\node-v12_19_0-win-x64\vue2-user-profile>cd gas_client_h5

E:\workspace\userprofile\node-v12_19_0-win-x64\vue2-user-profile\gas_client_h5>dir
 Volume in drive E is HOME
 Volume Serial Number is 0884-C3E1

 Directory of E:\workspace\userprofile\node-v12_19_0-win-x64\vue2-user-profile\gas_client_h5

11/05/23  07:54 PM    <DIR>          .
11/05/23  07:54 PM    <DIR>          ..
11/05/23  07:54 PM               230 .babelrc
11/05/23  07:54 PM               147 .editorconfig
11/05/23  07:54 PM                30 .eslintignore
11/05/23  07:54 PM               791 .eslintrc.js
12/19/23  03:54 AM               167 .gitignore
11/05/23  07:54 PM               246 .postcssrc.js
11/05/23  07:54 PM    <DIR>          build
11/05/23  07:54 PM    <DIR>          config
11/05/23  08:13 PM            27,737 gas_client_h5.log
11/18/23  08:04 AM               521 index.html
11/05/23  07:57 PM    <DIR>          node_modules
12/19/23  04:56 AM             2,372 package.json
10/29/23  07:59 PM               229 jsconfig.json
11/05/23  07:54 PM    <DIR>          src
11/05/23  07:54 PM    <DIR>          static
10/10/23  09:24 AM    <DIR>          .vscode
11/05/23  08:11 PM             2,232 package.json.orig
12/19/23  04:32 AM            13,753 README.md
11/05/23  09:19 PM             2,154 vue.config.js.bak
10/28/23  05:30 AM             1,898 cookie_20231028.txt
11/09/23  07:58 PM               280 index.html.orig
11/14/23  01:41 AM             4,286 bitbug_favicon.ico
11/18/23  08:01 AM            10,911 nginx-logo-1.png
11/18/23  07:55 AM            12,064 nginx-logo.png
11/18/23  08:03 AM             4,286 bitbug_nginx.ico
11/18/23  08:06 AM    <DIR>          dist
12/19/23  04:57 AM           553,305 package-lock.json
              20 File(s)        637,639 bytes
               9 Dir(s)  11,190,843,392 bytes free

E:\workspace\userprofile\node-v12_19_0-win-x64\vue2-user-profile\gas_client_h5>ren package-lock.json package_lock

E:\workspace\userprofile\node-v12_19_0-win-x64\vue2-user-profile\gas_client_h5>ren node_modules node_modules.bak
@
E:\workspace\userprofile\node-v12_19_0-win-x64\vue2-user-profile\gas_client_h5>move node_modules.bak ..\gas_client_h5_5@bak

E:\workspace\userprofile\node-v12_19_0-win-x64\vue2-user-profile\gas_client_h5>npm cache clear --force
npm WARN using --force I sure hope you know what you are doing.

E:\workspace\userprofile\node-v12_19_0-win-x64\vue2-user-profile\gas_client_h5>npm install --save-dev --save-exact prettier@~1.12.0
npm notice created a lockfile as package-lock.json. You should commit this file.
+ prettier@1.12.1
added 1 package from 1 contributor in 2.408s

E:\workspace\userprofile\node-v12_19_0-win-x64\vue2-user-profile\gas_client_h5>npm install
npm WARN deprecated babel-eslint@8.2.6: babel-eslint is now @babel/eslint-parser. This package will no longer receive updates.
npm WARN deprecated eslint-loader@1.9.0: This loader has been deprecated. Please use eslint-webpack-plugin
npm WARN deprecated extract-text-webpack-plugin@3.0.2: Deprecated. Please use https://github.com/webpack-contrib/mini-css-extract-plugin
npm WARN deprecated html-webpack-plugin@2.30.1: out of support
npm WARN deprecated browserslist@2.11.3: Browserslist 2 could fail on reading Browserslist >3.0 config used in other tools.
npm WARN deprecated uglify-es@3.3.9: support for ECMAScript is superseded by `uglify-js` as of v3.13.0
npm WARN deprecated consolidate@0.14.5: Please upgrade to consolidate v1.0.0+ as it has been modernized with several long-awaited fixes implemented. Maintenance is supported by Forward Email at https://forwardemail.net ; follow/watch https://github.com/ladjs/consolidate for updates and release changelog
npm WARN deprecated bfj-node4@5.3.1: Switch to the `bfj` package for fixes and new features!
npm WARN deprecated chokidar@2.1.8: Chokidar 2 does not receive security updates since 2019. Upgrade to chokidar 3 with 15x fewer dependencies
npm WARN deprecated acorn-dynamic-import@2.0.2: This is probably built in to whatever tool you're using. If you still need it... idk
npm WARN deprecated core-js@2.6.12: core-js@<3.23.3 is no longer maintained and not recommended for usage due to the number of issues. Because of the V8 engine whims, feature detection in old core-js versions could cause a slowdown up to 100x even if nothing is polyfilled. Some versions have web compatibility issues. Please, upgrade your dependencies to the actual version of core-js.
npm WARN deprecated flatten@1.0.3: flatten is deprecated in favor of utility frameworks such as lodash.
npm WARN deprecated fsevents@1.2.13: The v1 package contains DANGEROUS / INSECURE binaries. Upgrade to safe fsevents v2
npm WARN deprecated uuid@3.4.0: Please upgrade  to version 7 or higher.  Older versions may use Math.random() in certain circumstances, which is known to be problematic.  See https://v8.dev/blog/math-random for details.
npm WARN deprecated browserslist@1.7.7: Browserslist 2 could fail on reading Browserslist >3.0 config used in other tools.
npm WARN deprecated svgo@0.7.2: This SVGO version is no longer supported. Upgrade to v2.x.x.
npm WARN deprecated circular-json@0.3.3: CircularJSON is in maintenance only, flatted is its successor.
npm WARN deprecated svgo@1.3.2: This SVGO version is no longer supported. Upgrade to v2.x.x.
npm WARN deprecated source-map-resolve@0.5.3: See https://github.com/lydell/source-map-resolve#deprecated
npm WARN deprecated stable@0.1.8: Modern JS already guarantees Array#sort() is a stable sort, so this library is deprecated. See the compatibility table on MDN: https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/Array/sort#browser_compatibility
npm WARN deprecated resolve-url@0.2.1: https://github.com/lydell/resolve-url#deprecated
npm WARN deprecated source-map-url@0.4.1: See https://github.com/lydell/source-map-url#deprecated
npm WARN deprecated urix@0.1.0: Please see https://github.com/lydell/urix#deprecated
[            ......] | refresh-package-json:babylon: sill refresh-package-json E:\workspace\userprofile\node-v12_19_0-wi
> core-js@2.6.12 postinstall E:\workspace\userprofile\node-v12_19_0-win-x64\vue2-user-profile\gas_client_h5\node_modules\core-js
> node -e "try{require('./postinstall')}catch(e){}"

Thank you for using core-js ( https://github.com/zloirock/core-js ) for polyfilling JavaScript standard library!

The project needs your help! Please consider supporting of core-js on Open Collective or Patreon:
> https://opencollective.com/core-js
> https://www.patreon.com/zloirock

Also, the author of core-js ( https://github.com/zloirock ) is looking for a good job -)


> ejs@2.7.4 postinstall E:\workspace\userprofile\node-v12_19_0-win-x64\vue2-user-profile\gas_client_h5\node_modules\ejs
> node ./postinstall.js

Thank you for installing EJS: built with the Jake JavaScript build tool (https://jakejs.com/)


> es5-ext@0.10.62 postinstall E:\workspace\userprofile\node-v12_19_0-win-x64\vue2-user-profile\gas_client_h5\node_modules\es5-ext
>  node -e "try{require('./_postinstall')}catch(e){}" || exit 0


> uglifyjs-webpack-plugin@0.4.6 postinstall E:\workspace\userprofile\node-v12_19_0-win-x64\vue2-user-profile\gas_client_h5\node_modules\webpack\node_modules\uglifyjs-webpack-plugin
> node lib/post_install.js

npm WARN optional SKIPPING OPTIONAL DEPENDENCY: fsevents@~2.3.2 (node_modules\chokidar\node_modules\fsevents):
npm WARN notsup SKIPPING OPTIONAL DEPENDENCY: Unsupported platform for fsevents@2.3.3: wanted {"os":"darwin","arch":"any"} (current: {"os":"win32","arch":"x64"})
npm WARN optional SKIPPING OPTIONAL DEPENDENCY: fsevents@^1.2.7 (node_modules\watchpack-chokidar2\node_modules\chokidar\node_modules\fsevents):
npm WARN notsup SKIPPING OPTIONAL DEPENDENCY: Unsupported platform for fsevents@1.2.13: wanted {"os":"darwin","arch":"any"} (current: {"os":"win32","arch":"x64"})
npm WARN optional SKIPPING OPTIONAL DEPENDENCY: fsevents@^1.2.7 (node_modules\webpack-dev-server\node_modules\chokidar\node_modules\fsevents):
npm WARN notsup SKIPPING OPTIONAL DEPENDENCY: Unsupported platform for fsevents@1.2.13: wanted {"os":"darwin","arch":"any"} (current: {"os":"win32","arch":"x64"})
npm WARN update-browserslist-db@1.0.13 requires a peer of browserslist@>= 4.21.0 but none is installed. You must install peer dependencies yourself.

added 1458 packages from 743 contributors in 548.977s

96 packages are looking for funding
  run `npm fund` for details


E:\workspace\userprofile\node-v12_19_0-win-x64\vue2-user-profile\gas_client_h5>npm fund
gas_client_h5@1.0.0
+-- https://opencollective.com/browserslist
| `-- caniuse-lite@1.0.30001570, browserslist@4.22.2, update-browserslist-db@1.0.13
+-- https://tidelift.com/funding/github/npm/caniuse-lite
| `-- caniuse-lite@1.0.30001570
+-- https://github.com/sponsors/ai
| `-- caniuse-lite@1.0.30001570, browserslist@4.22.2, update-browserslist-db@1.0.13, postcss@8.4.32, nanoid@3.3.7
+-- https://github.com/sponsors/RubenVerborgh
| `-- follow-redirects@1.15.3
+-- https://github.com/sponsors/sindresorhus
| `-- is-finite@1.1.0, component-emitter@1.3.1
+-- https://github.com/sponsors/ljharb
| `-- minimist@1.2.8, defined@1.0.1, array-includes@3.1.7, array.prototype.findlastindex@1.2.3, array.prototype.flat@1.3.2, array.prototype.flatmap@1.3.2, is-core-module@2.13.1, object.fromentries@2.0.7, object.values@1.1.7, call-bind@1.0.5, define-properties@1.2.1, es-abstract@1.22.3, get-intrinsic@1.2.2, is-string@1.0.7, function-bind@1.1.2, gopd@1.0.1, has-property-descriptors@1.0.1, array-buffer-byte-length@1.0.0, arraybuffer.prototype.slice@1.0.2, available-typed-arrays@1.0.5, es-to-primitive@1.2.1, function.prototype.name@1.1.6, get-symbol-description@1.0.0, globalthis@1.0.3, has-proto@1.0.1, has-symbols@1.0.3, is-array-buffer@3.0.2, is-callable@1.2.7, is-negative-zero@2.0.2, is-regex@1.1.4, is-shared-array-buffer@1.0.2, is-typed-array@1.1.12, is-weakref@1.0.2, object-inspect@1.13.1, object.assign@4.1.5, regexp.prototype.flags@1.5.1, safe-array-concat@1.0.1, safe-regex-test@1.0.0, string.prototype.trim@1.2.8, string.prototype.trimend@1.0.7, string.prototype.trimstart@1.0.7, typed-array-byte-length@1.0.0, typed-array-byte-offset@1.0.0, typed-array-length@1.0.4, unbox-primitive@1.0.2, which-typed-array@1.1.13, has-tostringtag@1.0.0, is-date-object@1.0.5, is-symbol@1.0.4, functions-have-names@1.2.3, side-channel@1.0.4, has-bigints@1.0.2, which-boxed-primitive@1.0.2, is-bigint@1.0.4, is-boolean-object@1.1.2, is-number-object@1.0.7, resolve@1.22.8, supports-preserve-symlinks-flag@1.0.0, util.promisify@1.0.1, object.getownpropertydescriptors@2.1.7, array.prototype.reduce@1.0.6, qs@6.11.2, qs@6.11.0, deep-equal@1.1.2, is-arguments@1.1.1, object-is@1.1.5
+-- https://github.com/sponsors/isaacs
| `-- glob@7.2.3
+-- https://github.com/sponsors/wooorm
| `-- vendors@1.0.4
+-- https://github.com/sponsors/epoberezkin
| `-- ajv@6.12.6
+-- https://github.com/sponsors/fb55
| `-- css-select@4.3.0, htmlparser2@6.1.0, css-what@6.1.0, domelementtype@2.3.0, css-what@3.4.2
+-- https://github.com/fb55/htmlparser2?sponsor=1
| `-- htmlparser2@6.1.0
+-- https://github.com/fb55/domhandler?sponsor=1
| `-- domhandler@4.3.1
+-- https://github.com/fb55/domutils?sponsor=1
| `-- domutils@2.8.0
+-- https://github.com/fb55/nth-check?sponsor=1
| `-- nth-check@2.1.1
+-- https://github.com/cheeriojs/dom-serializer?sponsor=1
| `-- dom-serializer@1.4.1
+-- https://github.com/fb55/entities?sponsor=1
| `-- entities@2.2.0
+-- https://opencollective.com/postcss/
| `-- postcss@7.0.39, postcss-load-config@2.1.2, postcss@8.4.32
+-- https://tidelift.com/funding/github/npm/browserslist
| `-- browserslist@4.22.2, update-browserslist-db@1.0.13
+-- https://tidelift.com/funding/github/npm/postcss
| `-- postcss@8.4.32
+-- https://github.com/sponsors/feross
| `-- base64-js@1.5.1, ieee754@1.2.1, safe-buffer@5.2.1
+-- https://www.patreon.com/feross
| `-- base64-js@1.5.1, ieee754@1.2.1, safe-buffer@5.2.1
+-- https://feross.org/support
| `-- base64-js@1.5.1, ieee754@1.2.1, safe-buffer@5.2.1
+-- https://paulmillr.com/funding/
| `-- chokidar@3.5.3, async-each@1.0.6
+-- https://github.com/sponsors/jonschlinkert
| `-- picomatch@2.3.1
`-- https://tidelift.com/funding/github/npm/loglevel
  `-- loglevel@1.8.1


E:\workspace\userprofile\node-v12_19_0-win-x64\vue2-user-profile\gas_client_h5>
https://prettier.cn/docs//install.html                       Install · Prettier 中文网
https://www.cnblogs.com/panie2015/p/16709547.html           【Vue项目实践】 Unsupported engine
https://blog.csdn.net/u011280778/article/details/88107472    { parser_ _babylon_ } is deprecated; we now treat it as { parser_ _babel_ }._ted; we now treat it as { parser_ _babel_ }. 75% a-CSDN博客


E:\workspace\userprofile\node-v12_19_0-win-x64\vue2-user-profile\gas_client_h5>npm install
npm WARN update-browserslist-db@1.0.13 requires a peer of browserslist@>= 4.21.0 but none is installed. You must install peer dependencies yourself.
npm WARN optional SKIPPING OPTIONAL DEPENDENCY: fsevents@1.2.13 (node_modules\webpack-dev-server\node_modules\fsevents):
npm WARN notsup SKIPPING OPTIONAL DEPENDENCY: Unsupported platform for fsevents@1.2.13: wanted {"os":"darwin","arch":"any"} (current: {"os":"win32","arch":"x64"})
npm WARN optional SKIPPING OPTIONAL DEPENDENCY: fsevents@2.3.3 (node_modules\fsevents):
npm WARN notsup SKIPPING OPTIONAL DEPENDENCY: Unsupported platform for fsevents@2.3.3: wanted {"os":"darwin","arch":"any"} (current: {"os":"win32","arch":"x64"})
npm WARN optional SKIPPING OPTIONAL DEPENDENCY: fsevents@1.2.13 (node_modules\watchpack-chokidar2\node_modules\fsevents):
npm WARN notsup SKIPPING OPTIONAL DEPENDENCY: Unsupported platform for fsevents@1.2.13: wanted {"os":"darwin","arch":"any"} (current: {"os":"win32","arch":"x64"})

up to date in 8.326s

96 packages are looking for funding
  run `npm fund` for details


E:\workspace\userprofile\node-v12_19_0-win-x64\vue2-user-profile\gas_client_h5>npm install axios@1.5.0 --save
npm WARN update-browserslist-db@1.0.13 requires a peer of browserslist@>= 4.21.0 but none is installed. You must install peer dependencies yourself.
npm WARN optional SKIPPING OPTIONAL DEPENDENCY: fsevents@1.2.13 (node_modules\watchpack-chokidar2\node_modules\fsevents):
npm WARN notsup SKIPPING OPTIONAL DEPENDENCY: Unsupported platform for fsevents@1.2.13: wanted {"os":"darwin","arch":"any"} (current: {"os":"win32","arch":"x64"})
npm WARN optional SKIPPING OPTIONAL DEPENDENCY: fsevents@2.3.3 (node_modules\fsevents):
npm WARN notsup SKIPPING OPTIONAL DEPENDENCY: Unsupported platform for fsevents@2.3.3: wanted {"os":"darwin","arch":"any"} (current: {"os":"win32","arch":"x64"})
npm WARN optional SKIPPING OPTIONAL DEPENDENCY: fsevents@1.2.13 (node_modules\webpack-dev-server\node_modules\fsevents):
npm WARN notsup SKIPPING OPTIONAL DEPENDENCY: Unsupported platform for fsevents@1.2.13: wanted {"os":"darwin","arch":"any"} (current: {"os":"win32","arch":"x64"})

+ axios@1.5.0
updated 1 package in 12.107s

96 packages are looking for funding
  run `npm fund` for details


E:\workspace\userprofile\node-v12_19_0-win-x64\vue2-user-profile\gas_client_h5>echo %errorlevel%
0

E:\workspace\userprofile\node-v12_19_0-win-x64\vue2-user-profile\gas_client_h5>npm install
npm WARN update-browserslist-db@1.0.13 requires a peer of browserslist@>= 4.21.0 but none is installed. You must install peer dependencies yourself.
npm WARN optional SKIPPING OPTIONAL DEPENDENCY: fsevents@2.3.3 (node_modules\fsevents):
npm WARN notsup SKIPPING OPTIONAL DEPENDENCY: Unsupported platform for fsevents@2.3.3: wanted {"os":"darwin","arch":"any"} (current: {"os":"win32","arch":"x64"})
npm WARN optional SKIPPING OPTIONAL DEPENDENCY: fsevents@1.2.13 (node_modules\watchpack-chokidar2\node_modules\fsevents):
npm WARN notsup SKIPPING OPTIONAL DEPENDENCY: Unsupported platform for fsevents@1.2.13: wanted {"os":"darwin","arch":"any"} (current: {"os":"win32","arch":"x64"})
npm WARN optional SKIPPING OPTIONAL DEPENDENCY: fsevents@1.2.13 (node_modules\webpack-dev-server\node_modules\fsevents):
npm WARN notsup SKIPPING OPTIONAL DEPENDENCY: Unsupported platform for fsevents@1.2.13: wanted {"os":"darwin","arch":"any"} (current: {"os":"win32","arch":"x64"})

up to date in 8.444s

96 packages are looking for funding
  run `npm fund` for details


E:\workspace\userprofile\node-v12_19_0-win-x64\vue2-user-profile\gas_client_h5>
https://blog.csdn.net/qq_50526424/article/details/134862934    vue axios 引用报错Module parse failed_ Unexpected token (5_2) You may need an appropriate loader to hand-CSDN博客
