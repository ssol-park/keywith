package com.keywith.api.mapper;

import com.keywith.api.dto.ScrapResultDto;
import com.keywith.api.entity.PublicOffering;
import com.keywith.api.entity.PublicOfferingUnderwriter;
import com.keywith.api.entity.Underwriter;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PublicOfferingMapper {
    PublicOfferingMapper INSTANCE = Mappers.getMapper(PublicOfferingMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "scrapDto.stockCode", target = "stockCode")
    @Mapping(source = "scrapDto.offeringStartDate", target = "offeringStartDate")
    @Mapping(source = "scrapDto.offeringEndDate", target = "offeringEndDate")
    @Mapping(source = "scrapDto.confirmedPrice", target = "confirmedPrice")
    @Mapping(source = "scrapDto.desiredPriceMin", target = "desiredPriceMin")
    @Mapping(source = "scrapDto.desiredPriceMax", target = "desiredPriceMax")
    @Mapping(source = "scrapDto.competitionRate", target = "competitionRate")
    @Mapping(source = "scrapDto.listingDate", target = "listingDate")
    @Mapping(source = "scrapDto.paymentDate", target = "paymentDate")
    @Mapping(source = "scrapDto.refundDate", target = "refundDate")
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    PublicOffering scrapDtoToPublicOffering(ScrapResultDto scrapDto);

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "publicOffering.id", target = "publicOfferingId")
    @Mapping(source = "underwriter.id", target = "underwriterId")
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    PublicOfferingUnderwriter mapToPublicOfferingUnderwriter(PublicOffering publicOffering, Underwriter underwriter);
}
