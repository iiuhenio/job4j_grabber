# Агрегатор Java Вакансий

## Описание

Система запускается по расписанию.
Период запуска указывается в настройках - app.properties.

Первый сайт будет [Хабр Карьера](https://career.habr.com/vacancies/java_developer). В нем есть раздел job. <br>
Программа будет считывать все вакансии относящиеся к Java и записывать их в базу.
Доступ к интерфейсу реализован через браузер по адресу [http://localhost:9000/](http://localhost:9000).
При необходимости проект можно дополнить для работы с любым сайтом, <br>
Для этого достаточно реализовать парсер конкретного сайта реализовав интерфейс "Parse"

## Стек технологий

Java 16 <br>
Maven 3.6 <br>
org.jsoup <br>
PostgreSQL 14 <br>
H2 database 2.0 <br>
Liqubase 4.15 <br>
Sl4j + Log4j 5.6.11 <br>
org.quartz-schedule 2.3.2 <br>
Junit 4.12 <br>

## Требование к окружению

JDK 16, Maven 3.8, PostgreSQL 14.

## Запуск приложения

1. Создайте базу данных grabber при помощи консоли PostgreSQL или терминала pgAdmin:<br>
   """CREATE DATABASE grabber"""
2. Скопировать проект из репозитория по ссылке:
   <a href=https://git@github.com:iiuhenio/job4j_grabber.git><b>Агрегатор Java Вакансий</b></a>
3. Перейдите в корень проекта и при помощи Maven соберите проект командой:<br>
   """mvn install -Pproduction -Dmaven.test.skip=true"""
4. После успешной сборки проекта перейдите в каталог собранного проекта <b>target</b> и запустите приложение
   командой:<br>
   """java -jar grabber.jar"""

## Взаимодействие с приложением

1. В файле app.properties доступны следующие настройки:
   > time=10 интервал опроса сайта в секундах
   > port=9000 номер порта на котором доступна веб страница с результатом.
2. Для просмотра результата работы парсера необходима перейти на страницу:
   > localhost:9000

![localhost9000](https://github.com/iiuhenio/job4j_grabber/assets/63558835/141a95c8-f693-4b81-84c0-37bc78492020)<br>


Рисунок 1. Отображение страницы с результатом.

### Контакты

> email: [iuheniomail@yandex.ru](mailto:iuheniomail@yandex.ru) <br>
> tl: [iiuhenio](https://t.me/iiuhenio) <br>
> github.com: [iiuhenio](https://github.com/iiuhenio)
