package com.abujava.project.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@FeignClient(value = "FileUploadFeign", url = "http://localhost:8081/your-upload-url")
public interface FileUploadFeign {
    /**
     * Upload a single file
     */
    @RequestMapping(value = "/single-upload", method = RequestMethod.POST, headers = "Content-Type=multipart/form-data")
    Object upload(@RequestPart("file") MultipartFile file);

    /**
     * Upload multiple files
     */
    @RequestMapping(value = "/multiple-upload", method = RequestMethod.POST, headers = "Content-Type=multipart/form-data")
    Object upload(@RequestPart("files") List<MultipartFile> files);
}
