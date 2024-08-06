package com.keywith.api.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Getter
@ToString
@NoArgsConstructor
@Table("market")
public class Market {
    @Id
    private long id;
    private String name;
    private LocalDateTime updatedAt;
    private LocalDateTime createdAt;

    public Market(String name) {
        this.name = name;
    }
}
