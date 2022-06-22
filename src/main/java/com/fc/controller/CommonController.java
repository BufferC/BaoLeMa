package com.fc.controller;

import com.fc.util.FileUploadUtil;
import com.fc.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

/**
 * 文件上传和下载
 */
@RestController
@RequestMapping("common")
@Slf4j
public class CommonController {
    @Autowired
    private FileUploadUtil fileUploadUtil;

    /**
     * 文件上传
     */
    @PostMapping("upload")
    public ResultVO<String> upload(@RequestPart MultipartFile file) {
        //file是一个临时文件，需要转存到指定位置，否则本次请求完成后临时文件会删除
        log.info(file.toString());

        String fileName = fileUploadUtil.upload(file);

        if (fileName != null) {
            return ResultVO.success(fileName);
        }

        return ResultVO.error(null);
    }

    /**
     * 文件下载
     */
    @GetMapping("download")
    public void download(String name, HttpServletResponse response) {
        fileUploadUtil.download(name, response);
    }
}
