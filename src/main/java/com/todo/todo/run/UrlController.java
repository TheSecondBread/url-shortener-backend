package com.todo.todo.run;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class UrlController {
//    public final UrlRepository urlRepository;
    public final UrlRepository urlRepository;

    public UrlController(UrlRepository urlRepository){
        this.urlRepository = urlRepository;
    }
    @GetMapping("/urls")
    public List<Url> getAllUrls(){
        return urlRepository.GetUrls();
    }
    @PostMapping("urls")
    public Url addUrl(@RequestBody Url url){
        return urlRepository.AddUrl(url);
    }
    @GetMapping("/{url}")
    public ResponseEntity<String> getLink(@PathVariable String url) {
        String redirect = urlRepository.GetLink(url);
        System.out.println("--------------------------------");
        System.out.println(redirect);
        System.out.println("--------------------------------");
        if(redirect != null && !redirect.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.MOVED_PERMANENTLY)
                    .header("Location", redirect)
                    .build();
        } else {
            // Handle the case when the redirect URL is null or empty
            // For example, you can return a ResponseEntity with an appropriate status code
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Redirect URL not found");
        }
    }
}
