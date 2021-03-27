package mk.ukim.finki.emt.lab.web.controllers;

import mk.ukim.finki.emt.lab.model.Book;
import mk.ukim.finki.emt.lab.model.dto.BookDto;
import mk.ukim.finki.emt.lab.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/api/books")
public class BookRestController {

    private final BookService bookService;

    public BookRestController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping()
    public List<Book> findAll(){
        return  this.bookService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> findById(@PathVariable Long id){
        return this.bookService.findById(id)
                .map(book->ResponseEntity.ok().body(book))
                .orElseGet(()->ResponseEntity.notFound().build());
    }
    @PostMapping("/add")
    public ResponseEntity<Book> save(@RequestBody BookDto bookDto){
        return this.bookService.save(bookDto)
                .map(book -> ResponseEntity.ok().body(book))
                .orElseGet(()->ResponseEntity.badRequest().build());
    }
    @PutMapping("/edit/{id}")
    public ResponseEntity<Book> edit(@PathVariable Long id,@RequestBody BookDto bookDto){
        return this.bookService.edit(id,bookDto)
                .map(book -> ResponseEntity.ok().body(book))
                .orElseGet(()->ResponseEntity.badRequest().build());
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable Long id){
        this.bookService.deleteById(id);
        if(this.bookService.findById(id).isEmpty())
            return ResponseEntity.ok().build();
        return ResponseEntity.badRequest().build();
    }
    @PostMapping("/taken/{id}")
    public ResponseEntity<Book> markAsTaken(@PathVariable Long id){
        return this.bookService.markAsTaken(id)
                .map(book -> ResponseEntity.ok().body(book))
                .orElseGet(()->ResponseEntity.notFound().build());
    }

}
