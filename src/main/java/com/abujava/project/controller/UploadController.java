package com.abujava.project.controller;

import com.abujava.project.feign.FileUploadFeign;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * This class not documented :(
 *
 * @author Muhammad Mo'minov
 * @since 12/17/2022
 */
@RestController
@RequestMapping("/api/upload")
@RequiredArgsConstructor
public class UploadController {
    private final FileUploadFeign uploadFeign;

    @Value("${app.upload-path}")
    private String uploadPath;

    @GetMapping("/{fileId}")
    public String upload(@PathVariable String fileId) {
        File file = new File(uploadPath + fileId);
        try {
            FileInputStream input = new FileInputStream(file);
            MultipartFile multipartFile = new MockMultipartFile(fileId, file.getName(), "image/jpg", IOUtils.toByteArray(input));
            Object upload = uploadFeign.upload(multipartFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return "File uploaded";
    }

    @PostMapping("/transfer")
    public String transfer(MultipartHttpServletRequest request) {
        List<MultipartFile> multipartFileList = new ArrayList<>();

        Iterator<String> fileNames = request.getFileNames();
        while (fileNames.hasNext()) {
            multipartFileList.add(request.getFile(fileNames.next()));
        }

        if (multipartFileList.isEmpty()) return "Empty file list";

        Object upload = uploadFeign.upload(multipartFileList);
        System.out.println(upload.toString());

        return "All files uploaded";
    }

}
