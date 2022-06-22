- [Background](#background)
- [Change Log](#change-log)
  - [v0.1.0](#v010)
  - [v0.0.1](#v001)
- [Install](#install)
- [Usage](#usage)
- [advance](#advance)
- [Reports](#reports)

## Background

小学期结束后的练习项目，初期使用 SpringBoot + MyBatis Plus + Vue 实现。后期会逐步添加各种中间件实现和完善功能。目的是为了对学习过的内容进行巩固和归纳总结，并且还能加深对项目业务的理解。如果同学们多提供良好的 PR 和 issue 来帮助改善此项目。

## Change Log

### v0.1.0

- 完善`README.md` 
- 使用`DelayQueue`处理超时订单

### v0.0.1

- 项目搭建
- 基础功能实现
- 数据脱敏

## Install

1. 拿到项目后先修改 Maven 仓库的地址为自己的本地路径更新依赖
2. util --> JasyptUtil 为数据库脱敏的工具类，使用自定义秘钥将自己邮件服务器用户名和密码进行加密后替换掉 yml 配置文件中的邮件用户名、邮件密码、数据脱敏秘钥即可
3. util --> EmailUtil 为邮件发送工具类，项目中直接获取了邮件服务器的用户名作为发送者和接受者，也可以自定义。【不配置此项将无法进行前台登录】

## Usage

后台商家登录地址：[饱了吗后台管理端](http://localhost:8080/backend/page/login/login.html)

前台用户登录地址：[饱了吗](http://localhost:8080/front/page/login.html)

## advance

外卖员登录地址：（待完善）

Redis 缓存（待优化）

Shiro 权限管理（待优化）

前后端分离（待优化）

微信小程序（待优化）

## Reports

<div align="center">  
  <img src="https://github-readme-streak-stats.herokuapp.com?user=BufferC&theme=onedark&date_format=M%20j%5B%2C%20Y%5D" />
</div>