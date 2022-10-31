package com.example.Estate_Twin.checklist.web.dto;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.Generated;

/**
 * com.example.Estate_Twin.checklist.web.dto.QCheckListDto is a Querydsl Projection type for CheckListDto
 */
@Generated("com.querydsl.codegen.ProjectionSerializer")
public class QCheckListDto extends ConstructorExpression<CheckListDto> {

    private static final long serialVersionUID = 1142441358L;

    public QCheckListDto(com.querydsl.core.types.Expression<? extends com.example.Estate_Twin.checklist.data.entity.CheckList> checkList) {
        super(CheckListDto.class, new Class<?>[]{com.example.Estate_Twin.checklist.data.entity.CheckList.class}, checkList);
    }

}

