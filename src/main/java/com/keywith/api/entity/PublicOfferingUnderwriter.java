package com.keywith.api.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Table("public_offering_underwriter")
public class PublicOfferingUnderwriter {
    @Id
    private Long id;
    private Long publicOfferingId;
    private Long underwriterId;
    private LocalDateTime updatedAt;
    private LocalDateTime createdAt;
}
