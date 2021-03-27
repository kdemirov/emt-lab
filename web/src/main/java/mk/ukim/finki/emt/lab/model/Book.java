package mk.ukim.finki.emt.lab.model;

import lombok.Data;
import mk.ukim.finki.emt.lab.model.dto.BookDto;
import mk.ukim.finki.emt.lab.model.enumerations.BookCategories;

import javax.persistence.*;

@Data
@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    @Enumerated(EnumType.STRING)
    private BookCategories category;
    @ManyToOne
    private Author author;

    private Integer availableCopies;

    public Book(){ }

    public Book(String name,BookCategories category,Author author,Integer availableCopies){
        this.name=name;
        this.category=category;
        this.author=author;
        this.availableCopies=availableCopies;
    }

}
