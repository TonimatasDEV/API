package dev.tonimatas.maven;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
public class DownloadController {
    @GetMapping("1.20.1/download")
    public ResponseEntity<Resource> download1201() {
        return handleDownload("1201");
    }

    @GetMapping("1.19.3/download")
    public ResponseEntity<Resource> download1193() {
        return handleDownload("1193");
    }

    @GetMapping("1.18.2/download")
    public ResponseEntity<Resource> download1182() {
        return handleDownload("1182");
    }

    @GetMapping("1.12.2/download")
    public ResponseEntity<Resource> download1122() {
        return handleDownload("1122");
    }


    public static ResponseEntity<Resource> handleDownload(String version) {
        File filesFolder = new File(version);
        String fileName = null;

        File[] files = filesFolder.listFiles();

        if (files == null) {
            return ResponseEntity.internalServerError().build();
        }

        for (File file : files) {
            fileName = file.getName();
            break;
        }

        Path filePath = Paths.get(version, fileName);
        try {
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
        } catch (MalformedURLException e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
