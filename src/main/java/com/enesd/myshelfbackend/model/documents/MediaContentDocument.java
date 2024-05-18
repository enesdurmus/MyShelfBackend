package com.enesd.myshelfbackend.model.documents;

import com.enesd.myshelfbackend.model.abstracts.MediaContent;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.time.Instant;

@Document(indexName = "media_contents")
public class MediaContentDocument extends MediaContent {

    @Override
    @Field(type = FieldType.Date, pattern = "uuuu-MM-dd'T'HH:mm:ss.SSS")
    public Instant getReleaseDate() {
        return super.getReleaseDate();
    }
}
