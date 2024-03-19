## [REST API](http://localhost:8080/doc)

## Концепция:

- Spring Modulith
    - [Spring Modulith: достигли ли мы зрелости модульности](https://habr.com/ru/post/701984/)
    - [Introducing Spring Modulith](https://spring.io/blog/2022/10/21/introducing-spring-modulith)
    - [Spring Modulith - Reference documentation](https://docs.spring.io/spring-modulith/docs/current-SNAPSHOT/reference/html/)

```
  url: jdbc:postgresql://localhost:5432/jira
  username: jira
  password: JiraRush
```

- Есть 2 общие таблицы, на которых не fk
    - _Reference_ - справочник. Связь делаем по _code_ (по id нельзя, тк id привязано к окружению-конкретной базе)
    - _UserBelong_ - привязка юзеров с типом (owner, lead, ...) к объекту (таска, проект, спринт, ...). FK вручную будем
      проверять

## Аналоги

- https://java-source.net/open-source/issue-trackers

## Тестирование

- https://habr.com/ru/articles/259055/

## Список выполненных задач:

- Удалены социальные сети (vk, yandex) (#2)
- Чувствительная информация пересена в переменные окружения (.env добавлен в git-игнор) (#3)
- Изменен запуск тестов и миграцию базы для работы с H2 базой данных (#4)
- Добавлены тесты для ProfileRestController (#5)
- Обновлена имплементация метода com.javarush.jira.bugtracking.attachment.FileUtil для современной работы с файловой системой (#6)
- Добавлен функционал для работы с тегами для задач (список тегов, добавление, удаление) (#7)
- Добавлен подсчет времени нахождения задачи в определенном статусе (#8)
- Добавлен Dockerfile для основного сервиса (#9)
- Добавлен docker-compose, для основного сервиса, nginx и PostgreSQL (#10)
