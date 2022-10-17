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
        for (int page = 1; page <= 5; page++) {
            Connection connection = Jsoup.connect(PAGE_LINK + page);
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
            /*
             * 2) Проходимся по каждой вакансии и извлекаем нужные для нас данные.
             * Сначала получаем элементы содержащие название и ссылку. Стоит обратить внимание,
             * что дочерние элементы можно получать через индекс - метод child(0)
             * или же через селектор - select(".vacancy-card__title").
             */
            rows.forEach(row -> {
                Element titleElement = row.select(".vacancy-card__title").first();
                Element linkElement = titleElement.child(0);
                Element dateElement = row.select(".vacancy-card__date").first();
                /*
                 * Меняем формат даты:
                 */
                HabrCareerDateTimeParser hcdtp = new HabrCareerDateTimeParser();
                LocalDateTime datetT = hcdtp.parse(
                        row.select(".vacancy-card__date")
                                .first()
                                .child(0)
                                .attr("datetime"));
                /*
                 * 3) Наконец получаем данные непосредственно.
                 * text() возвращает все содержимое элемента в виде текста, т.е. весь текст что находится
                 * вне тегов HTML. Ссылка находится в виде атрибута, поэтому ее значение надо получить
                 * как значение атрибута. Для этого служит метод attr()
                 */
                String vacancyName = titleElement.text();
                String linkVacancy = String.format("%s%s", SOURCE_LINK, linkElement.attr("href"));
                String date = dateElement.text();
                String description;
                description = retrieveDescription(linkVacancy);
                Post post = new Post(vacancyName, linkVacancy, description, datetT);
                list.add(post);
            });
        }
        return list;
    }
}
