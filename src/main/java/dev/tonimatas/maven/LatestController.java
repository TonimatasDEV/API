package dev.tonimatas.maven;

import dev.tonimatas.maven.api.Version;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LatestController {
    @GetMapping("1.20.1/latest")
    public Version latest1201() {
        return new Version("1201");
    }

    @GetMapping("1.19.3/latest")
    public Version latest1193() {
        return new Version("1193");
    }

    @GetMapping("1.18.2/latest")
    public Version latest1182() {
        return new Version("1182");
    }
    @GetMapping("1.12.2/latest")
    public Version latest1122() {
        return new Version("1122");
    }

}
