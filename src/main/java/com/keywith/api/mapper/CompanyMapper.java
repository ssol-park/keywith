package com.keywith.api.mapper;

import com.keywith.api.dto.ScrapResultDto;
import com.keywith.api.entity.Company;
import com.keywith.api.entity.Industry;
import com.keywith.api.entity.Market;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CompanyMapper {
    CompanyMapper INSTANCE = Mappers.getMapper(CompanyMapper.class);

    @Mapping(source = "scrapDto.stockCode", target = "stockCode")
    @Mapping(source = "scrapDto.stockName", target = "companyName")
    @Mapping(source = "market.id", target = "marketId")
    @Mapping(source = "industry.id", target = "industryId")
    @Mapping(source = "scrapDto.revenue", target = "revenue")
    @Mapping(source = "scrapDto.netIncome", target = "netIncome")
    @Mapping(source = "scrapDto.website", target = "website")
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Company scrapDtoToCompany(ScrapResultDto scrapDto, Industry industry, Market market);
}
