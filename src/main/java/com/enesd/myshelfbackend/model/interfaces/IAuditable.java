package com.enesd.myshelfbackend.model.interfaces;

import java.time.Instant;

public interface IAuditable {
    public void setCreatedAt(Instant instant);

    public Instant getCreatedAt();

    public void setUpdatedAt(Instant instant);

    public Instant getUpdatedAt();
}
