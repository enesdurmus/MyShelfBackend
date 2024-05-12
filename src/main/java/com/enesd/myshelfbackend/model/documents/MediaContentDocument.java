package com.enesd.myshelfbackend.model.documents;

import com.enesd.myshelfbackend.model.abstracts.MediaContent;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "media_contents")
public class MediaContentDocument extends MediaContent {
}
