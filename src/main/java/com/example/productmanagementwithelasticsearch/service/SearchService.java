package com.example.productmanagementwithelasticsearch.service;



import org.apache.lucene.search.join.ScoreMode;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class SearchService {

    @Autowired
    private RestHighLevelClient client;

    public List<Map<String, Object>> search(String keyword) throws IOException {
        SearchRequest searchRequest = new SearchRequest("products");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

        searchSourceBuilder.query(QueryBuilders.boolQuery()
                .should(QueryBuilders.matchQuery("name", keyword))
                .should(QueryBuilders.matchQuery("description", keyword))
                .should(QueryBuilders.nestedQuery("skus",
                        QueryBuilders.boolQuery()
                                .should(QueryBuilders.matchQuery("skus.name", keyword))
                                .should(QueryBuilders.matchQuery("skus.color", keyword)),
                        ScoreMode.Avg))
        );

        searchRequest.source(searchSourceBuilder);
        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);

        // Преобразуем результаты поиска в список
        List<Map<String, Object>> searchResults = new ArrayList<>();
        for (SearchHit hit : searchResponse.getHits().getHits()) {
            searchResults.add(hit.getSourceAsMap());
        }

        return searchResults;
    }
}
