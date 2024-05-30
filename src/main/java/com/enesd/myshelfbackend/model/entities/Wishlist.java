package com.enesd.myshelfbackend.model.entities;

import com.enesd.myshelfbackend.enums.ContentType;
import com.enesd.myshelfbackend.model.abstracts.Auditable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Persistable;

@Entity
@Data
@Table(name = "wishlists", indexes = {
        @Index(columnList = "user_id, content_type, content_id", unique = true)
})
@AllArgsConstructor
@NoArgsConstructor
public class Wishlist extends Auditable implements Persistable<Long> {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "content_id")
    private Integer contentId;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "content_type")
    private ContentType contentType;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public boolean isNew() {
        return true;
    }
}
