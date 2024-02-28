package ru.redguy.testtask.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.redguy.testtask.domain.Searcher;
import ru.redguy.testtask.domain.Vacancy;
import ru.redguy.testtask.service.EmailService;
import ru.redguy.testtask.service.SearcherService;
import ru.redguy.testtask.service.VacancyService;

import java.text.SimpleDateFormat;
import java.time.Instant;

@Component
public class ScheduledTasks {
    @Autowired
    private VacancyService vacancyService;
    @Autowired
    private SearcherService searcherService;
    @Autowired
    private EmailService emailService;

    @Scheduled(cron = "0 0 12 * * ?", zone = "Europe/Moscow")
    public void sendNewVacancies() {
        Instant now = Instant.now();
        String date = new SimpleDateFormat("dd.MM.yyyy").format(now.toEpochMilli());

        Iterable<Searcher> searchers = searcherService.getAll();
        for (Searcher searcher : searchers) {
            Iterable<Vacancy> vacancies = vacancyService.searchByPositionAndCity(searcher.getPosition(), searcher.getCity());
            for (Vacancy vacancy : vacancies) {
                String text = String.format("Здравствуйте, %s!\n" +
                                            "Информируем вас о новой вакансии на должность %s.\n" +
                                            "Наименование: %s\n" +
                                            "Описание: %s\n" +
                                            "Уровень зарплаты: %s\n" +
                                            "Требуемый опыт работы: %s\n" +
                                            "С уважением,\n" +
                                            "Цифровое Будущее\n" +
                                            "%s", searcher.getFio(), vacancy.getPosition(), vacancy.getName(), vacancy.getDescription(), vacancy.getSalary(), vacancy.getExperience(), date);
                emailService.sendEmail(searcher.getEmail(), "Новая вакансия!", text);
            }
        }
    }
}
