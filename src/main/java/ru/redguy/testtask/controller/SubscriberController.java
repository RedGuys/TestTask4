package ru.redguy.testtask.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.redguy.testtask.domain.Searcher;
import ru.redguy.testtask.service.SearcherService;

@RestController()
public class SubscriberController {
    @Autowired
    private SearcherService searcherService;

    @PutMapping("/subscriber")
    public Searcher create(
            @RequestParam(name = "email") String email,
            @RequestParam(name = "fio") String fio,
            @RequestParam(name = "city") String city,
            @RequestParam(name = "position") String position
    ) {
        return searcherService.create(email, fio, city, position);
    }

    @DeleteMapping("/subscriber")
    public Searcher delete(
            @RequestParam(name = "email") String email
    ) {
        return searcherService.delete(email);
    }
}
