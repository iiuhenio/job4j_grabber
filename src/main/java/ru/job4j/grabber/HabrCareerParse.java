package ru.job4j.grabber;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class HabrCareerParse {

    /**
     * 1. У нас есть две константы.
     * Первая это ссылка на сайт в целом.
     * Вторая указывает на страницу с вакансиями непосредственно
     */
    private static final String SOURCE_LINK = "https://career.habr.com";

    private static final String PAGE_LINK = String.format("%s/vacancies/java_developer", SOURCE_LINK);

    public static void main(String[] args) throws IOException {
        /*
         * 2. Сначала мы получаем страницу, чтобы с ней можно было работать:
         */
        Connection connection = Jsoup.connect(PAGE_LINK);
        Document document = connection.get();
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
             * 3) Наконец получаем данные непосредственно.
             * text() возвращает все содержимое элемента в виде текста, т.е. весь текст что находится
             * вне тегов HTML. Ссылка находится в виде атрибута, поэтому ее значение надо получить
             * как значение атрибута. Для этого служит метод attr()
             */
            String vacancyName = titleElement.text();
            String link = String.format("%s%s", SOURCE_LINK, linkElement.attr("href"));
            String date = dateElement.text();
            System.out.printf("%s %s %s%n", date, vacancyName, link);
        });
    }
}
