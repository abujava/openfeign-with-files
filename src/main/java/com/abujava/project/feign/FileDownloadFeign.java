package com.abujava.project.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;


@FeignClient(value = "FileDownloadAPI", url = "http://localhost:8081/your-download-url", configuration = FeignConfig.class)
public interface FileDownloadFeign {
    @GetMapping("/{fileId}")
    MultipartFile download(@PathVariable String fileId);
}
