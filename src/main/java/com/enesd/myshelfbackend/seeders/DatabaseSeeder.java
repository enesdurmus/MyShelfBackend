package com.enesd.myshelfbackend.seeders;

import com.enesd.myshelfbackend.enums.SubscriptionType;
import com.enesd.myshelfbackend.model.entities.BookEntity;
import com.enesd.myshelfbackend.model.entities.MediaContentEntity;
import com.enesd.myshelfbackend.model.entities.Subscription;
import com.enesd.myshelfbackend.repository.jpa.BookEntityRepository;
import com.enesd.myshelfbackend.repository.jpa.MediaContentEntityRepository;
import com.enesd.myshelfbackend.repository.jpa.SubscriptionRepository;
import com.github.javafaker.Faker;
import lombok.AllArgsConstructor;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;

@Service
@AllArgsConstructor
public class DatabaseSeeder {

    private BookEntityRepository bookEntityRepository;
    private MediaContentEntityRepository mediaContentEntityRepository;
    private SubscriptionRepository subscriptionRepository;

    @EventListener
    public void seed(ContextRefreshedEvent event) {
        seedBookTable();
        seedMediaContentTable();
        seedSubscriptions();
    }

    private void seedBookTable() {
        Faker faker = new Faker();
        class BookCreator {
            private final Faker faker;

            public BookCreator(Faker faker) {
                this.faker = faker;
            }

            public BookEntity create() {
                BookEntity bookEntity = new BookEntity();
                bookEntity.setTitle(faker.book().title());
                bookEntity.setAverageRating((float) faker.number().randomDouble(2, 1, 100));
                bookEntity.setCountryCode(faker.country().countryCode2());
                bookEntity.setDescription(faker.gameOfThrones().character());
                bookEntity.setPageNumber(faker.number().randomDigit());
                return bookEntity;
            }
        }

        BookCreator bookCreator = new BookCreator(faker);
        for (int i = 0; i < 20; i++) {
            BookEntity book = bookCreator.create();
            bookEntityRepository.save(book);
        }
    }

    private void seedMediaContentTable() {
        Faker faker = new Faker();
        class MediaContentCreator {
            private final Faker faker;

            public MediaContentCreator(Faker faker) {
                this.faker = faker;
            }

            public MediaContentEntity create() {
                MediaContentEntity mediaContentEntity = new MediaContentEntity();
                mediaContentEntity.setTitle(faker.book().title());
                mediaContentEntity.setAdult(faker.bool().bool());
                mediaContentEntity.setReleaseDate(faker.date().birthday().toInstant());
                mediaContentEntity.setRevenue((long) faker.number().numberBetween(100, 100000000));
                mediaContentEntity.setAverageRating((float) faker.number().randomDouble(10, 10, 1000));
                mediaContentEntity.setRuntime(faker.number().randomDigit());
                return mediaContentEntity;
            }
        }

        MediaContentCreator mediaContentCreator = new MediaContentCreator(faker);
        for (int i = 0; i < 20; i++) {
            MediaContentEntity mediaContentEntity = mediaContentCreator.create();
            mediaContentEntityRepository.save(mediaContentEntity);
        }
    }

    private void seedSubscriptions() {
        Subscription subscription = new Subscription();
        subscription.setAmount(5);
        subscription.setSubscriptionType(SubscriptionType.RATE_LIMIT);
        subscription.setName("test");
        subscription.setDurationHours(1f);
        subscription.setPrice(BigDecimal.ONE);
        subscriptionRepository.save(subscription);
    }
}
