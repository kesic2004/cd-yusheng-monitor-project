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

