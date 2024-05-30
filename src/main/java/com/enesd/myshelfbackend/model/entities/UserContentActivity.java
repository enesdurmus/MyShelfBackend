package com.enesd.myshelfbackend.model.entities;

import com.enesd.myshelfbackend.enums.ActivityType;
import com.enesd.myshelfbackend.enums.ContentType;
import com.enesd.myshelfbackend.model.abstracts.Auditable;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.domain.Persistable;

import java.util.UUID;

@Entity
@Data
@Table(name = "user_content_activities", indexes = {
        @Index(columnList = "user_id, content_type, content_id", unique = true)
})
public class UserContentActivity extends Auditable implements Persistable<Long> {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "content_id")
    private int contentId;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "content_type")
    private ContentType contentType;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "activity_type")
    private ActivityType activityType;

    @Override
    public boolean isNew() {
        return true;
    }
}
