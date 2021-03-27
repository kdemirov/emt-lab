package mk.ukim.finki.emt.lab.web.controllers;

import mk.ukim.finki.emt.lab.model.Country;
import mk.ukim.finki.emt.lab.service.CountryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/api/countries")
public class CountryController {

    private final CountryService countryService;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping()
    public List<Country> findAll(){
        return this.countryService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Country> findById(@PathVariable Long id){
        return this.countryService.findById(id)
                .map(country -> ResponseEntity.ok().body(country))
                .orElseGet(()->ResponseEntity.notFound().build());
    }
    @PostMapping("/add")
    public ResponseEntity<Country> save(@RequestParam String name,
                                        @RequestParam String continent)
    {
        return this.countryService.save(name,continent)
                .map(ResponseEntity::ok)
                .orElseGet(()->ResponseEntity.badRequest().build());
    }
    @PutMapping("/edit/{id}")
    public ResponseEntity<Country> edit(@PathVariable Long id,
                                        @RequestParam String name,
                                        @RequestParam String continent){
        return this.countryService.edit(id,name,continent)
                .map(ResponseEntity::ok)
                .orElseGet(()->ResponseEntity.badRequest().build());
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable Long id){
        this.countryService.deleteById(id);
        if(this.countryService.findById(id).isEmpty())
            return ResponseEntity.ok().build();
        return ResponseEntity.badRequest().build();
    }
}
