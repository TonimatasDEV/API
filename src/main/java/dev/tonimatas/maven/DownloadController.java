package dev.tonimatas.maven;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
public class DownloadController {

    @RequestMapping("/")
    public String test() {
        return "Test";
    }

    private static final String FILE_DIRECTORY = "C:\\Users\\amata\\Servidores\\Magma [1.18.2]"; // Change this to your file directory

    @GetMapping("/download/")
    public ResponseEntity<Resource> downloadFile() throws IOException {
        Path filePath = Paths.get(FILE_DIRECTORY, "magma-1.18.2-40.2.10-9c8fb2e4-server.jar");
        Resource resource = new UrlResource(filePath.toUri());

        if (resource.exists() && resource.isReadable()) {
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + "magma-1.18.2-40.2.10-9c8fb2e4-server.jar");
            return ResponseEntity.ok()
                    .headers(headers)
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .body(resource);
        } else {
            // Handle file not found or inaccessible
            return ResponseEntity.notFound().build();
        }
    }
}
