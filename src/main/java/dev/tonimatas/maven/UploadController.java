package dev.tonimatas.maven;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Controller
public class UploadController {
    @PostMapping("/upload/{id}")
    public ResponseEntity<String> handleFileUpload(@RequestParam("file") MultipartFile file, @PathVariable String id) {
        if (!file.isEmpty() && id.equals(Main.id)) {
            try {
                byte[] bytes = file.getBytes();

                File fileFolder = new File("files");
                fileFolder.delete();
                fileFolder.mkdir();

                String filePath = "files/" + file.getOriginalFilename();

                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(filePath));
                stream.write(bytes);
                stream.close();

                return new ResponseEntity<>("File " + file.getOriginalFilename() + " uploaded correctly.", HttpStatus.OK);
            } catch (IOException e) {
                return new ResponseEntity<>("Upload error. File: " + file.getOriginalFilename(), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity<>("The file is void.", HttpStatus.BAD_REQUEST);
        }
    }
}
