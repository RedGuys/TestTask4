package ru.redguy.testtask.repos;

import org.springframework.data.repository.CrudRepository;
import ru.redguy.testtask.domain.Searcher;

public interface SearcherRepo extends CrudRepository<Searcher, Long> {
    Searcher findByEmail(String email);
}
