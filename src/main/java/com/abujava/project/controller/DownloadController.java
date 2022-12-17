package com.abujava.project.controller;

import com.abujava.project.feign.FileDownloadFeign;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * This class not documented :(
 *
 * @author Muhammad Mo'minov
 * @since 12/17/2022
 */
@RestController
@RequestMapping("/api/download")
@RequiredArgsConstructor
public class DownloadController {
    private final FileDownloadFeign downloadFeign;

    @Value("${app.download-path}")
    private String downloadPath;

    @GetMapping("/{fileId}")
    public String download(@PathVariable String fileId) {
        MultipartFile multipartFile = downloadFeign.download(fileId);
        File destFile = new File(downloadPath, multipartFile.getOriginalFilename());

        try {
            FileUtils.copyInputStreamToFile(multipartFile.getInputStream(), destFile);
            return "File downloaded and saved";
        } catch (IOException e) {
            e.printStackTrace();
            return "Error happened!";
        }
    }

}
