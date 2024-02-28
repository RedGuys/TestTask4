package ru.redguy.testtask.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.redguy.testtask.domain.Searcher;
import ru.redguy.testtask.repos.SearcherRepo;

@Service
public class SearcherService {
    @Autowired
    private SearcherRepo searcherRepo;

    public Searcher delete(String email) {
        Searcher searcher = searcherRepo.findByEmail(email);
        if(searcher == null) {
            return null;
        }
        searcherRepo.delete(searcher);
        return searcher;
    }

    public Searcher create(String email, String fio, String city, String position) {
        Searcher searcher = new Searcher(email, fio, city, position);
        searcherRepo.save(searcher);
        return searcher;
    }

    public Iterable<Searcher> getAll() {
        return searcherRepo.findAll();
    }
}
