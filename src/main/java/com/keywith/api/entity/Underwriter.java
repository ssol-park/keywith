package com.keywith.api.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Table("underwriter")
public class Underwriter {
    @Id
    private long id;
    private String name;
    private LocalDateTime updatedAt;
    private LocalDateTime createdAt;
}
