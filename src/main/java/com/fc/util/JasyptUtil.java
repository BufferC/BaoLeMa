package com.fc.util;

import org.jasypt.util.text.BasicTextEncryptor;

// 加密工具类
public class JasyptUtil {
    public static void main(String[] args) {
        // 在此输入要加密的真实内容
        String username = "";
        String password = "";
        BasicTextEncryptor encryptor = new BasicTextEncryptor();
        // 指定秘钥，与配置文件中jasypt.encryptor.password属性值保持一致
        //encryptor.setPassword(System.getProperty("jasypt.encryptor.password"));
        encryptor.setPassword("qwertyui");
        // 密码进行加密
        String newUsername = encryptor.encrypt(username);
        String newPassword = encryptor.encrypt(password);
        // 获取加密后的内容，与配置文件中填入的属性值保持一致
        System.out.println("加密后账号：" + newUsername);
        System.out.println("加密后密码：" + newPassword);
    }
}