package dev.tonimatas.maven;

import dev.tonimatas.maven.api.Version;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LatestController {
    @GetMapping("/latest")
    public Version getLatestVersion() {
        return new Version();
    }
}
