package com.enesd.myshelfbackend.seeders;

import com.enesd.myshelfbackend.model.entities.BookEntity;
import com.enesd.myshelfbackend.model.entities.MediaEntity;
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

            public MediaEntity create() {
                MediaEntity mediaEntity = new MediaEntity();
                mediaEntity.setTitle(faker.book().title());
                mediaEntity.setAdult(faker.bool().bool());
                mediaEntity.setReleaseDate(faker.date().birthday().toInstant());
                mediaEntity.setRevenue((long) faker.number().numberBetween(100, 100000000));
                mediaEntity.setAverageRating((float) faker.number().randomDouble(10, 10, 1000));
                mediaEntity.setRuntime(faker.number().randomDigit());
                return mediaEntity;
            }
        }

        MediaContentCreator mediaContentCreator = new MediaContentCreator(faker);
        for (int i = 0; i < 20; i++) {
            MediaEntity mediaEntity = mediaContentCreator.create();
            mediaContentEntityRepository.save(mediaEntity);
        }
    }

    private void seedSubscriptions() {
        Subscription subscription = new Subscription();
        subscription.setAmount(5);
        subscription.setName("test");
        subscription.setDurationHours(1f);
        subscription.setPrice(BigDecimal.ONE);
        subscriptionRepository.save(subscription);
    }
}
