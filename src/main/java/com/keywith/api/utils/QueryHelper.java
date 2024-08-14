package com.keywith.api.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.Map;

@Slf4j
@Component
public class QueryHelper {

    private final DatabaseClient databaseClient;

    public QueryHelper(DatabaseClient databaseClient) {
        this.databaseClient = databaseClient;
    }

    public <T> Mono<Void> executeUpsert(String sql, T entity) {
        Map<String, Object> params = ObjectMapperUtil.convertEntityToMap(entity);

        DatabaseClient.GenericExecuteSpec spec = databaseClient.sql(sql);

        for (Map.Entry<String, Object> entry : params.entrySet()) {
            if (entry.getValue() != null)
                spec = spec.bind(entry.getKey(), entry.getValue());
        }

        return spec.fetch().rowsUpdated().then();
    }
}
