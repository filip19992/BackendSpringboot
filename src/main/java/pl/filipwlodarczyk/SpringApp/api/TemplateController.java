package pl.filipwlodarczyk.SpringApp.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class TemplateController {

    @GetMapping("main")
    public String mainPage() {
        return "Main Page";
    }
}
