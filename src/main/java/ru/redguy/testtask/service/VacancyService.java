package ru.redguy.testtask.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.redguy.testtask.domain.Vacancy;
import ru.redguy.testtask.repos.VacancyRepo;

@Service
public class VacancyService {
    @Autowired
    private VacancyRepo vacancyRepo;

    public Iterable<Vacancy> searchByName(String name) {
        return vacancyRepo.findByName(name);
    }

    public Iterable<Vacancy> searchByPosition(String position) {
        return vacancyRepo.findByPosition(position);
    }

    public Iterable<Vacancy> searchByPositionAndCity(String position, String city) {
        return vacancyRepo.findByPositionAndCity(position, city);
    }

    public Iterable<Vacancy> searchByCity(String city) {
        return vacancyRepo.findByCity(city);
    }

    public Iterable<Vacancy> searchCreatedToday() {
        return vacancyRepo.findCreatedToday();
    }

    public Vacancy create(String name, String description, String position, int salary, String experience, String city) {
        Vacancy vacancy = new Vacancy(name, description, position, salary, experience, city);
        vacancyRepo.save(vacancy);
        return vacancy;
    }
}
