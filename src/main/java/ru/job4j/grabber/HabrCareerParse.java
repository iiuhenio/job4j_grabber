package ru.job4j.grabber;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import ru.job4j.grabber.utils.DateTimeParser;
import ru.job4j.grabber.utils.HabrCareerDateTimeParser;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class HabrCareerParse implements Parse {
    /**
     * 1. У нас есть две константы.
     * Первая это ссылка на сайт в целом.
     * Вторая указывает на страницу с вакансиями непосредственно
     */
    private static final String SOURCE_LINK = "https://career.habr.com";

    private static final String PAGE_LINK = String.format("%s/vacancies/java_developer?page=", SOURCE_LINK);

    private static final int PAGE_NUMBER = 5;
    /*
    Парсер даты сделаем полем и принимать в конструкторе
     */
    private final DateTimeParser dateTimeParser;

    public HabrCareerParse(DateTimeParser dateTimeParser) {
        this.dateTimeParser = dateTimeParser;
    }
    /*
    Реализуем вывод описания вакансии
     */
    private String retrieveDescription(String link) {
        Connection connection = Jsoup.connect(link);
        try {
            Document document = connection.get();
            Elements rows = document.select(".style-ugc");
            return rows.text();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public List<Post> list(String link) {
        List<Post> list = new ArrayList<>();
        /*
        Добавляем итерирование по страницам.
         */
        for (int i = 1; i <= PAGE_NUMBER; i++) {
            Connection connection = Jsoup.connect(link + i);
            /*
             * 2. Сначала мы получаем страницу, чтобы с ней можно было работать:
             */
            Document document = null;
            try {
                document = connection.get();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            /*
             * 4. На основе анализа прописываем парсинг
             * 1) Сначала мы получаем все вакансии страницы.
             */
            Elements rows = document.select(".vacancy-card__inner");

            rows.forEach(row -> list.add(getPost(row)));
        }
        return list;
    }

    /**
     * Вынесем парсинг Post в отдельный метод
     *
     *  Проходимся по каждой вакансии и извлекаем нужные для нас данные.
     * Сначала получаем элементы содержащие название и ссылку. Стоит обратить внимание,
     * что дочерние элементы можно получать через индекс - метод child(0)
     * или же через селектор - select(".vacancy-card__title").
     */
    private Post getPost(Element element) {
        Element titleElement = element.select(".vacancy-card__title").first();
        Element linkElement = titleElement.child(0);
        Element dateElement = element.select(".vacancy-card__date").first();
        String vacancyName = titleElement.text();
        String link = String.format("%s%s", SOURCE_LINK, linkElement.attr("href"));
        String description = retrieveDescription(link);
        LocalDateTime dateTime = dateTimeParser.parse(dateElement.child(0).attr("dateTime"));
        return new Post(vacancyName, link, description, dateTime);
    }
}
