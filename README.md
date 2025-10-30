## 🌐 Языки / Languages

- 🇷🇺 [Русский](README.ru.md)

# 📦 Product Management with Elasticsearch — Technical Test Project

This project is a technical test that simulates a basic product management system with search capabilities powered by Elasticsearch. It allows storing product data and searching by product name, description, SKU name, and SKU color.

---

## ⚙️ Features

- Add products with multiple SKUs
- Search products by:
  - Product name
  - Product description
  - SKU name
  - SKU color
- Store product data in Elasticsearch
- RESTful API for product operations

---

## 🛠️ Technologies

- Java 17+
- Spring Boot
- Elasticsearch
- Docker & Docker Compose
- IntelliJ IDEA (recommended for development)

---

## 🚀 Quick Start

1. Clone the repository:
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
