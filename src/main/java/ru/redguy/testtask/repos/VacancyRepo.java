package ru.redguy.testtask.repos;

import org.springframework.data.repository.CrudRepository;
import ru.redguy.testtask.domain.Vacancy;

public interface VacancyRepo extends CrudRepository<Vacancy, Long> {

    Iterable<Vacancy> findByCreatedAfter(Long created);
    Iterable<Vacancy> findByPosition(String position);
    Iterable<Vacancy> findByCity(String city);
    Iterable<Vacancy> findByName(String name);
    Iterable<Vacancy> findByPositionAndCity(String position, String city);

    default Iterable<Vacancy> findCreatedToday() {
        return findByCreatedAfter(System.currentTimeMillis() / 1000 - 24 * 60 * 60);
    }
}
