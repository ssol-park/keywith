package com.keywith.api.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table("industry")
public class Industry {
    @Id
    private Long id;
    private String name;
    private LocalDateTime updatedAt;
    private LocalDateTime createdAt;

    public Industry(String name) {
        this.name = name;
    }
}
