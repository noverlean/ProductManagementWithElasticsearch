## 🌐 Языки / Languages

- 🇷🇺 [Русский](README.ru.md)

# 📦 Product Management with Elasticsearch — Тестовое задание

Этот проект представляет собой тестовое задание, имитирующее простую систему управления продуктами с возможностью поиска на базе Elasticsearch. Он позволяет сохранять данные о продуктах и выполнять поиск по названию продукта, описанию, названию SKU и цвету SKU.

---

## ⚙️ Функциональность

- Добавление продуктов с несколькими SKU
- Поиск продуктов по:
  - Названию продукта
  - Описанию продукта
  - Названию SKU
  - Цвету SKU
- Хранение данных о продуктах в Elasticsearch
- REST API для операций с продуктами

---

## 🛠️ Технологии

- Java 17+  
- Spring Boot  
- Elasticsearch  
- Docker и Docker Compose  
- IntelliJ IDEA (рекомендуется для разработки)

---

## 🚀 Быстрый старт

1. Клонируйте репозиторий:
   ```bash
   git clone https://github.com/noverlean/ProductManagementWithElasticsearch.git
   cd ProductManagementWithElasticsearch
   ```
2. Запустите окружение:
  ```bash
  docker-compose up -d
  ```

3. Откройте проект в IntelliJ и запустите Spring Boot приложение.
4. В браузере используйте строку поиска для запроса продуктов по названию, описанию, названию SKU или цвету SKU.

##⚠️ Ручное добавление данных
Автоматическая миграция данных из базы данных в индекс Elasticsearch в данный момент не работает. Чтобы вручную добавить пример данных, используйте следующую команду в терминале:
```bash
curl -X POST "http://localhost:9200/products/_doc/1" \
-H "Content-Type: application/json" \
-d '{
  "name": "Sample Product",
  "description": "This is a sample product.",
  "startDate": "2024-07-31T12:00:00Z",
  "active": true,
  "skus": [
    {
      "name": "SKU-C1",
      "color": "Red",
      "availability": true
    },
    {
      "name": "SKU-C2",
      "color": "Blue",
      "availability": false
    }
  ]
}'
```

