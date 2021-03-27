package mk.ukim.finki.emt.lab.web.controllers;

import mk.ukim.finki.emt.lab.model.enumerations.BookCategories;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/api/categories")
public class CategoriesController {

    @GetMapping
    public List<BookCategories> findAll(){
        return Arrays.asList(BookCategories.values().clone());
    }
}
