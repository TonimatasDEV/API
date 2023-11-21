package dev.tonimatas.maven;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
public class DownloadController {
    @GetMapping("1.20.1/download/")
    public ResponseEntity<Resource> downloadFile() throws IOException {
        File filesFolder = new File("1201");
        String fileName = null;

        if (!filesFolder.exists()) {
            return ResponseEntity.internalServerError().build();
        }

        File[] files = filesFolder.listFiles();

        if (files == null) {
            return ResponseEntity.internalServerError().build();
        }

        for (File file : files) {
            fileName = file.getName().split("-")[3];
            break;
        }

        Path filePath = Paths.get("1201", fileName);
        Resource resource = new UrlResource(filePath.toUri());

        if (resource.exists() && resource.isReadable()) {
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName);
            return ResponseEntity.ok()
                    .headers(headers)
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .body(resource);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
