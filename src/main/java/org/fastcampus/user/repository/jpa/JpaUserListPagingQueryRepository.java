package org.fastcampus.user.repository.jpa;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.fastcampus.user.application.dto.GetUserListResponseDto;
import org.fastcampus.user.repository.entity.QUserEntity;
import org.fastcampus.user.repository.entity.QUserRelationEntity;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class JpaUserListPagingQueryRepository {

    // QueryDsl를 사용하기 위해서!
    private final JPAQueryFactory jpaQueryFactory;

    // Q 객체를 불러와야 하는데 User관계에 따른 User 정보를 가져오기 위해서!
    private static final QUserEntity user = QUserEntity.userEntity;
    private static final QUserRelationEntity relation = QUserRelationEntity.userRelationEntity;

    public List<GetUserListResponseDto> getFollwerList(Long userId, Long lastFollowerId){
        return jpaQueryFactory
            .select(
                Projections.fields(
                    GetUserListResponseDto.class
                )
            )
            .from(relation)
            .join(user).on(relation.followingUserId.eq(user.id))
            .where(
                relation.followerUserId.eq(userId),
                hasLastData(lastFollowerId)
            )
            .orderBy(user.id.desc())
            .limit(20)
            .fetch();
    }

    private BooleanExpression hasLastData(Long lastId){
        if(lastId == null) return null;
        return user.id.lt(lastId);
    }

    /*
    * 마지막으로 조회한 id를 넘겨준다면 조건문에 추가되고 아니라면 조건문에 추가가 안되도록
    * dynamic한 쿼리를 queryDsl를 통해 작성해봄
    * */
}
