package mk.ukim.finki.emt.lab.service.impl;

import mk.ukim.finki.emt.lab.model.Country;
import mk.ukim.finki.emt.lab.model.exceptions.CountryNotFoundException;
import mk.ukim.finki.emt.lab.repository.CountryRepository;
import mk.ukim.finki.emt.lab.service.CountryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountryServiceImpl implements CountryService {

    private final CountryRepository countryRepository;

    public CountryServiceImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public List<Country> findAll() {
        return this.countryRepository.findAll();
    }

    @Override
    public Optional<Country> findById(Long id) {
        return this.countryRepository.findById(id);
    }

    @Override
    public Optional<Country> save(String name, String continent) {
       return Optional.of(this.countryRepository.save(new Country(name,continent)));
    }

    @Override
    public Optional<Country> edit(Long id, String name, String continent) {
        Country country=findById(id).orElseThrow(()->new CountryNotFoundException(id));
        country.setName(name);
        country.setContinent(continent);
        return Optional.of(country);
    }

    @Override
    public void deleteById(Long id) {
        this.countryRepository.deleteById(id);
    }
}
