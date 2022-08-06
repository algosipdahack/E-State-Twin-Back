package com.example.Estate_Twin.util.converter;

import com.example.Estate_Twin.estate.domain.TransactionType;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
@Slf4j
public class TransactionTypeConverter implements AttributeConverter<TransactionType, String> {
    @Override
    public String convertToDatabaseColumn(TransactionType transactionType) {
        if(transactionType == null)  return null;
        return transactionType.getType();
    }

    @Override
    public TransactionType convertToEntityAttribute(String dbData) {
        if(dbData == null) {
            return null;
        }
        try {
            return TransactionType.of(dbData);
        } catch (IllegalArgumentException e) {
            log.error("failure to convert cause unexpected code [{}]",dbData,e);
            throw  e;
        }
    }
}
