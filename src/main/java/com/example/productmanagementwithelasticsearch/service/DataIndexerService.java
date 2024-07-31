package com.example.productmanagementwithelasticsearch.service;

import com.example.productmanagementwithelasticsearch.entity.Product;
import com.example.productmanagementwithelasticsearch.repository.ProductRepository;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DataIndexerService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private RestHighLevelClient client;

    @Scheduled(fixedRate = 60000) // Задача будет выполняться каждые 60 секунд
    public void indexData() throws IOException {
        List<Product> products = productRepository.findAll();
        for (Product product : products) {
            indexProduct(product);
        }
    }

    private void indexProduct(Product product) throws IOException {
        IndexRequest request = new IndexRequest("products");
        request.id(product.getId().toString());
        request.source(convertProductToMap(product), XContentType.JSON);
        client.index(request, RequestOptions.DEFAULT);
    }

    private Map<String, Object> convertProductToMap(Product product) {
        Map<String, Object> map = new HashMap<>();
        map.put("name", product.getName());
        map.put("description", product.getDescription());
        map.put("skus", product.getSkus());
        return map;
    }
}
