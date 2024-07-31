*** Не работает миграция данных из бд в индекс elasticksearch. Данные добавить через командную строку. Ctrl+R -> (вводим)cmd -> enter -> (вставляем команду ниже) ***

команда: curl -X POST "http://localhost:9200/products/_doc/1" -H "Content-Type: application/json" -d "{\"name\":\"Sample Product\",\"description\":\"This is a sample product.\",\"startDate\":\"2024-07-31T12:00:00Z\",\"active\":true,\"skus\":[{\"name\":\"SKU-C1\",\"color\":\"Red\",\"availability\":true},{\"name\":\"SKU-C2\",\"color\":\"Blue\",\"availability\":false}]}"

Для запуска неодходимо клонировать проект, запустить docker-compose up -d, после создания окружения запустить проект из IntelliJ. в строке браузера можно искать данные по названию продукта, описанию продукта, названию ску и цвету ску.
