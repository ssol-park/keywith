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
@Table("public_offering_underwriter")
public class PublicOfferingUnderwriter {
    @Id
    private long id;
    private long publicOfferingId;
    private long underwriterId;
    private LocalDateTime updatedAt;
    private LocalDateTime createdAt;

    public PublicOfferingUnderwriter(long offeringId, long underwriterId) {
        this.publicOfferingId = offeringId;
        this.underwriterId = underwriterId;
    }
}
