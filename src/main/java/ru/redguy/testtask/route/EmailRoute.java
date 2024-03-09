package ru.redguy.testtask.route;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.redguy.testtask.domain.Searcher;
import ru.redguy.testtask.domain.Vacancy;
import ru.redguy.testtask.service.SearcherService;
import ru.redguy.testtask.service.VacancyService;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Component
public class EmailRoute extends RouteBuilder {
    @Autowired
    private VacancyService vacancyService;
    @Autowired
    private SearcherService searcherService;

    @Value("${camel.component.mail.host}")
    private String host;

    @Value("${camel.component.mail.username}")
    private String username;

    @Value("${camel.component.mail.password}")
    private String password;

    @Value("${camel.component.mail.from}")
    private String from;

    @Override
    public void configure() throws Exception {
        from("quartz://email?cron=0+38+13+*+*+?")
                .process(exchange -> {
                    Instant now = Instant.now();
                    String date = new SimpleDateFormat("dd.MM.yyyy").format(now.toEpochMilli());

                    Iterable<Searcher> searchers = searcherService.getAll();
                    List<Message> messages = new ArrayList<>();
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
                            messages.add(new Message(searcher.getEmail(), text));
                        }
                    }
                    exchange.getIn().setBody(messages);
                })
                .split(body())
                .setHeader("To", simple("${body.email}"))
                .setBody(simple("${body.text}"))
                .setHeader("CamelCharsetName", constant("UTF-8"))
                .setHeader("CamelMailSubject", constant("Новая вакансия!"))
                .to(String.format("smtps://%s?from=%s&password=%s&username=%s", host, from, password, username));
    }

    public static class Message {
        private final String email;
        private final String text;

        public Message(String email, String text) {
            this.email = email;
            this.text = text;
        }

        public String getEmail() {
            return email;
        }

        public String getText() {
            return text;
        }
    }
}