### Пользовательский интерфейс Iterable
Реализован пользовательский интерфейс Iterable
Интерфейс включает пользовательский Iterator и метод forEach() для перебора элементов коллекции
Метод forEach в качестве параметра принимает функциональный интерфейс Consumer

Пользовательский интерфейс Iterator остоит из следующих методов:
- hasNext()
- next()
- forEach()

Метод forEach в качестве параметра принимает функциональный интерфейс Consumer

### Custom Implementation Array List

В классе CustomArrayList добавлена имплементация интерфейса Iterable
Поля класса CustomArrayList:
- size
- Object[]
- currentIndex (для Iterator)

При инициализации создаётся массив с default capacity (константа, по умолчанию 10)
Есть конструктор с capacity;

В классе реализованы следующие методы:
- добавление в конец списка
- добавление в середину списка (пользователь передаёт индекс)
- удаление по индексу
- удаление по значению
- получение значения по индексу
- получение первого индекса по значению
- уменьшение размера списка до количества элементов

В классе App демонстрируется функционал