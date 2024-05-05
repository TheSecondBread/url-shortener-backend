package com.todo.todo.run;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
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
    public String addUrl(@RequestBody Url url){
        return urlRepository.AddUrl(url);
    }
    @GetMapping("/urls/{url}")
    public String GetLink(@PathVariable String url){
        if(urlRepository.GetLink(url)==null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"no such link");

        }
        else {
            return urlRepository.GetLink(url);
        }
    }
}
