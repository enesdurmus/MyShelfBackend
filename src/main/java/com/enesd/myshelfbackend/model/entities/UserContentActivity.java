package com.enesd.myshelfbackend.model.entities;

import com.enesd.myshelfbackend.enums.ActivityType;
import com.enesd.myshelfbackend.enums.ContentType;
import com.enesd.myshelfbackend.model.abstracts.Auditable;
import com.enesd.myshelfbackend.model.compositeKeys.UserContentActivityId;
import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Data
@IdClass(UserContentActivityId.class)
@Table(name = "user_content_activities")
public class UserContentActivity extends Auditable {
    @Id
    @Column(name = "user_id")
    private UUID userId;

    @Id
    @Column(name = "content_id")
    private int contentId;

    @Id
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "content_type")
    private ContentType contentType;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "activity_type")
    private ActivityType activityType;
}
