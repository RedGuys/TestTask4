package ru.redguy.testtask.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.redguy.testtask.domain.Vacancy;
import ru.redguy.testtask.service.VacancyService;

@RestController()
public class VacancyController {
    @Autowired
    private VacancyService vacancyService;

    @GetMapping("/vacancy")
    public Iterable<Vacancy> search(
            @RequestParam(name = "name", required = false, defaultValue = "") String name,
            @RequestParam(name = "position", required = false, defaultValue = "") String position,
            @RequestParam(name = "city", required = false, defaultValue = "") String city
    ) {
        if (!name.isEmpty()) {
            return vacancyService.searchByName(name);
        }
        if (!position.isEmpty()) {
            return vacancyService.searchByPosition(position);
        }
        if (!city.isEmpty()) {
            return vacancyService.searchByCity(city);
        }
        return vacancyService.searchCreatedToday();
    }

    @PutMapping("/vacancy")
    public Vacancy create(
            @RequestParam(name = "name") String name,
            @RequestParam(name = "description") String description,
            @RequestParam(name = "position") String position,
            @RequestParam(name = "salary") int salary,
            @RequestParam(name = "experience") String experience,
            @RequestParam(name = "city") String city
    ) {
        return vacancyService.create(name, description, position, salary, experience, city);
    }
}
