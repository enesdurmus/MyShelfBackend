package com.enesd.myshelfbackend.strategies.config;

import com.enesd.myshelfbackend.enums.ContentType;
import com.enesd.myshelfbackend.strategies.interfaces.IContentRetrievalStrategy;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

@Configuration
@AllArgsConstructor
public class ContentRetrievalStrategyConfig {
    private final List<IContentRetrievalStrategy> contentRetrievalStrategies;

    @Bean
    public Map<ContentType, IContentRetrievalStrategy> contentRetrievalStrategies() {
        Map<ContentType, IContentRetrievalStrategy> strategyEnumMap = new EnumMap<>(ContentType.class);
        contentRetrievalStrategies.forEach(contentRetrievalStrategy -> strategyEnumMap.put(contentRetrievalStrategy.getContentType(), contentRetrievalStrategy));
        return strategyEnumMap;
    }
}
