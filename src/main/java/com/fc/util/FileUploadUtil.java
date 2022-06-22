package com.fc.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

// 文件上传工具类
@Component
public class FileUploadUtil {
    @Value("${file.localPath}")
    private String basePath;

    // 获取文件路径
    public String getBasePath() {
        return basePath;
    }

    public void setBasePath(String basePath) {
        this.basePath = basePath;
    }

    public String upload(MultipartFile file) {
        File pathFile = new File(basePath);

        if (!pathFile.exists()) {
            pathFile.mkdirs();
        }

        // 获取文件名
        String filename = file.getOriginalFilename();

        // 获取文件名的后缀名
        filename = filename.substring(filename.indexOf('.'));

        // 声明时间格式
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");

        // 获取当前时间的格式
        String format = dateFormat.format(new Date());

        // 拼接到文件名上
        filename = format + filename;

        try {
            // 文件上传
            file.transferTo(new File(pathFile, filename));
        } catch (IOException e) {
            e.printStackTrace();
            filename = null;
        }

        // 返回可以访问的路径
        return filename;
    }

    // 文件下载
    public void download(String name, HttpServletResponse response) {
        try {
            File file = new File(basePath + name);

            // 如果不存在该图片，则显示一张空图
            if (!file.exists()) {
                file = new File(basePath + "noImg.png");
            }

            //输入流，通过输入流读取文件内容
            FileInputStream fileInputStream = new FileInputStream(file);

            //输出流，通过输出流将文件写回浏览器
            ServletOutputStream outputStream = response.getOutputStream();

            response.setContentType("image/jpeg");

            int len;
            byte[] bytes = new byte[1024];
            while ((len = fileInputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, len);
                outputStream.flush();
            }

            //关闭资源
            outputStream.close();
            fileInputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
