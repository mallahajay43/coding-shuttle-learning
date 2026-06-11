package com.mallahajay43.coding_shuttle_learning.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class PostResponse {
    private String title;
    private String content;
    private UUID creator;

    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    private String CreatedBy;
    private String ModifiedBy;
}
