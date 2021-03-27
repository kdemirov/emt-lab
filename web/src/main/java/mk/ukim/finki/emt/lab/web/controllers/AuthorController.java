package mk.ukim.finki.emt.lab.web.controllers;

import mk.ukim.finki.emt.lab.model.Author;
import mk.ukim.finki.emt.lab.service.AuthorService;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/api/authors")
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping()
    public List<Author> findAll(){
        return this.authorService.findAll();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Author> findById(@PathVariable Long id){
        return this.authorService.findById(id)
                .map(author -> ResponseEntity.ok().body(author))
                .orElseGet(()->ResponseEntity.notFound().build());
    }
    @PostMapping("/add")
    public ResponseEntity<Author> save(@RequestParam String name,@RequestParam String surname,@RequestParam Long countryId){
        return this.authorService.save(name,surname,countryId)
                .map(author -> ResponseEntity.ok().body(author))
                .orElseGet(()->ResponseEntity.badRequest().build());
    }
    @PutMapping("/edit/{id}")
    public ResponseEntity<Author> edit(@PathVariable Long id,
                                       @RequestParam String name,
                                       @RequestParam String surname,
                                       @RequestParam Long countryId){
        return this.authorService.edit(id,name,surname,countryId)
                .map(author -> ResponseEntity.ok().body(author))
                .orElseGet(()->ResponseEntity.badRequest().build());
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable Long id){
        this.authorService.deleteById(id);
        if(this.authorService.findById(id).isEmpty())
            return ResponseEntity.ok().build();
        return ResponseEntity.badRequest().build();
    }
}
