package mk.ukim.finki.emt.lab.service.impl;

import mk.ukim.finki.emt.lab.model.Author;
import mk.ukim.finki.emt.lab.model.Book;
import mk.ukim.finki.emt.lab.model.dto.BookDto;
import mk.ukim.finki.emt.lab.model.exceptions.AuthorNotFoundException;
import mk.ukim.finki.emt.lab.model.exceptions.BookNotFoundException;
import mk.ukim.finki.emt.lab.repository.BookRepository;
import mk.ukim.finki.emt.lab.service.AuthorService;
import mk.ukim.finki.emt.lab.service.BookService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorService authorService;
    public BookServiceImpl(BookRepository bookRepository, AuthorService authorService) {
        this.bookRepository = bookRepository;
        this.authorService = authorService;
    }

    @Override
    public List<Book> findAll() {
        return this.bookRepository.findAll();
    }

    @Override
    public Optional<Book> findById(Long id) {
        return this.bookRepository.findById(id);
    }

    @Override
    public Optional<Book> save(BookDto bookDto) {
        Author author=this.authorService.findById(bookDto.getAuthor()).orElseThrow(()->new AuthorNotFoundException(bookDto.getAuthor()));
        return Optional.of(this.bookRepository.save(new Book(bookDto.getName(),bookDto.getCategory(),author,bookDto.getAvailableCopies())));
    }

    @Override
    public Optional<Book> edit(Long id, BookDto bookDto) {
        Book book = findById(id).orElseThrow(()->new BookNotFoundException(id));
        Author author=this.authorService.findById(bookDto.getAuthor()).orElseThrow(()->new AuthorNotFoundException(bookDto.getAuthor()));
        book.setAuthor(author);
        book.setAvailableCopies(bookDto.getAvailableCopies());
        book.setCategory(bookDto.getCategory());
        book.setName(bookDto.getName());
        return Optional.of(this.bookRepository.save(book));
    }

    @Override
    public void deleteById(Long id) {
        this.bookRepository.deleteById(id);
    }

    @Override
    public Optional<Book> markAsTaken(Long id) {
        Book book=findById(id).orElseThrow(()->new BookNotFoundException((id)));
        Integer availableCopies=book.getAvailableCopies();
        if(availableCopies==0)
            return Optional.empty();
        availableCopies-=1;
        book.setAvailableCopies(availableCopies);
        return Optional.of(this.bookRepository.save(book));
    }
}
