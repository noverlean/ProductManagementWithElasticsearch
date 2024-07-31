package com.example.productmanagementwithelasticsearch.util;

import com.example.productmanagementwithelasticsearch.entity.Product;
import com.example.productmanagementwithelasticsearch.repository.ProductRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DataLoaderService {

    @Autowired
    private RestHighLevelClient client;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @PostConstruct
    public void loadData() {
        try {
            if (!indexExists("products")) {
                CreateIndexRequest request = new CreateIndexRequest("products");
                request.source(createMappings());
                CreateIndexResponse createIndexResponse = client.indices().create(request, RequestOptions.DEFAULT);
                System.out.println("Index created: " + createIndexResponse.index());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean indexExists(String indexName) throws IOException {
        GetIndexRequest request = new GetIndexRequest(indexName);
        return client.indices().exists(request, RequestOptions.DEFAULT);
    }

    private Map<String, Object> convertProductToMap(Product product) {
        Map<String, Object> map = new HashMap<>();
        map.put("name", product.getName());
        map.put("description", product.getDescription());
        map.put("skus", product.getSkus());
        return map;
    }

    private Map<String, Object> createMappings() {
        Map<String, Object> properties = new HashMap<>();
        properties.put("name", Map.of("type", "text"));
        properties.put("description", Map.of("type", "text"));
        properties.put("startDate", Map.of("type", "date"));
        properties.put("active", Map.of("type", "boolean"));

        Map<String, Object> skuProperties = new HashMap<>();
        skuProperties.put("name", Map.of("type", "text"));
        skuProperties.put("color", Map.of("type", "keyword"));
        skuProperties.put("availability", Map.of("type", "boolean"));

        properties.put("skus", Map.of("type", "nested", "properties", skuProperties));

        Map<String, Object> mappings = new HashMap<>();
        mappings.put("properties", properties);

        Map<String, Object> mappingsWrapper = new HashMap<>();
        mappingsWrapper.put("mappings", mappings);

        return mappingsWrapper;
    }
}

