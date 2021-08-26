package com.example.moduleNote.login.spl.repository.custom;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;
import org.springframework.validation.annotation.Validated;

import com.example.moduleNote.login.spl.entity.MpUserInfo;
import com.example.moduleNote.login.spl.entity.QMpUserInfo;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Validated
@Repository
public class MpUserInfoQueryRepository extends QuerydslRepositorySupport {
	private final JPAQueryFactory queryFactory;

	public MpUserInfoQueryRepository(JPAQueryFactory queryFactory) {
		super(MpUserInfo.class);
		this.queryFactory = queryFactory;
	}

	public MpUserInfo getUserInfoByUserNameAndUserPhone(String userId, int userPhone){
		QMpUserInfo mpUserInfo = QMpUserInfo.mpUserInfo;

		JPAQuery<MpUserInfo> query = queryFactory.selectFrom(mpUserInfo);

		BooleanBuilder booleanBuilder = new BooleanBuilder();
		booleanBuilder.and(mpUserInfo.userId.eq(userId));
		booleanBuilder.and(mpUserInfo.userPhone.eq(userPhone));

		return query.where(booleanBuilder).fetchOne();
	}

}
