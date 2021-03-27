package mk.ukim.finki.emt.lab.model.dto;

import lombok.Data;
import mk.ukim.finki.emt.lab.model.Author;
import mk.ukim.finki.emt.lab.model.enumerations.BookCategories;

@Data
public class BookDto {

    private String name;
    private BookCategories category;
    private Long author;
    private Integer availableCopies;

    public BookDto(String name, BookCategories category, Long authorId, Integer availableCopies) {
        this.name = name;
        this.category = category;
        this.author = authorId;
        this.availableCopies = availableCopies;
    }
}
