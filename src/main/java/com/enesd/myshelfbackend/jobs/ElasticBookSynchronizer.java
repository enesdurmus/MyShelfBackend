package com.enesd.myshelfbackend.jobs;

import com.enesd.myshelfbackend.model.documents.BookDocument;
import com.enesd.myshelfbackend.model.entities.BookEntity;
import com.enesd.myshelfbackend.repository.elasticsearch.BookDocumentRepository;
import com.enesd.myshelfbackend.repository.jpa.BookEntityRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class ElasticBookSynchronizer {

    private static final Logger logger = LoggerFactory.getLogger(ElasticBookSynchronizer.class);
    private final BookDocumentRepository bookDocumentRepository;
    private final BookEntityRepository bookEntityRepository;
    private final ModelMapper modelMapper;

    @Scheduled(cron = "0 * * ? * *")
    @SchedulerLock(name = "ElasticBookSynchronizer.synchronizeBooks", lockAtLeastFor = "PT15S", lockAtMostFor = "PT30S")
    public void synchronizeBooks() {
        List<Integer> elasticBookIds = bookDocumentRepository.findAllIds();

//        int offset = 0;
//        List<Long> missingIds;
//        do {
//            missingIds = repository.findMissingIds(idList, offset, pageSize);
//            if (!missingIds.isEmpty()) {
//                // Elasticsearch'e eklemek için gerekli işlemleri yapın
//                // Örneğin: Elasticsearch'e ekleme işlemi için kullanılacak metod çağrısı
//                // elasticsearchService.addToIndex(missingIds);
//                offset += pageSize;
//            }
//        } while (!missingIds.isEmpty());
//
//        List<BookEntity> bookEntities = elasticBookIds.size() == 0 ? bookEntityRepository.findAll() : bookEntityRepository.findByIdNotIn(elasticBookIds);
//
//        List<BookDocument> bookDocuments = bookEntities
//                .stream()
//                .map(user -> modelMapper.map(user, BookDocument.class)).toList();
//
//        bookDocumentRepository.saveAll(bookDocuments);
    }
}
