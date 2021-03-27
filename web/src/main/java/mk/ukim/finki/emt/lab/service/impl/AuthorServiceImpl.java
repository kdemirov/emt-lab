package mk.ukim.finki.emt.lab.service.impl;

import mk.ukim.finki.emt.lab.model.Author;
import mk.ukim.finki.emt.lab.model.Country;
import mk.ukim.finki.emt.lab.model.exceptions.AuthorNotFoundException;
import mk.ukim.finki.emt.lab.model.exceptions.CountryNotFoundException;
import mk.ukim.finki.emt.lab.repository.AuthorRepository;
import mk.ukim.finki.emt.lab.service.AuthorService;
import mk.ukim.finki.emt.lab.service.CountryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private final CountryService countryService;

    public AuthorServiceImpl(AuthorRepository authorRepository, CountryService countryService) {
        this.authorRepository = authorRepository;
        this.countryService = countryService;
    }


    @Override
    public List<Author> findAll() {
        return this.authorRepository.findAll();
    }

    @Override
    public Optional<Author> findById(Long id) {
        return this.authorRepository.findById(id);
    }

    @Override
    public Optional<Author> save(String name, String surname, Long countryId) {
        Country country=countryService.findById(countryId).orElseThrow(()->new CountryNotFoundException(countryId));
        return Optional.of(this.authorRepository.save(new Author(name,surname,country)));
    }

    @Override
    public Optional<Author> edit(Long id, String name, String surname, Long countryId) {
        Author author=findById(id).orElseThrow(()->new AuthorNotFoundException(id));
        Country country=this.countryService.findById(countryId).orElseThrow(()->new CountryNotFoundException(countryId));
        author.setName(name);
        author.setCountry(country);
        author.setSurname(surname);
        return Optional.of(this.authorRepository.save(author));

    }

    @Override
    public void deleteById(Long id) {
        this.authorRepository.deleteById(id);
    }
}
