package org.fastcampus.user.repository;

import java.util.HashSet;
import java.util.Set;
import org.fastcampus.user.application.interfaces.UserRelationRepository;
import org.fastcampus.user.domain.User;

public class FakeUserRelationRepository implements UserRelationRepository {
    private final Set<Relation> store = new HashSet<>();

    @Override
    public boolean isAlreadyFollow(User user, User targetUser) {
        return store.contains(new Relation(user.getId(), targetUser.getId()));
    }

    @Override
    public void save(User user, User targetUser) {
        store.add(new Relation(user.getId(), targetUser.getId()));
    }

    @Override
    public void delete(User user, User targetUser) {
        store.remove(new Relation(user.getId(), targetUser.getId()));
    }
}

record Relation (Long userId, Long targetUserId) {}

/*
* 기한이 촉박한 경우에는 사치일 수 있다!
* -> 그렇다면 가짜 객체를 만들어서 얻는 이점은!?
*
* - 외부적인 것들(다양한 DB 여러개 사용, Kafka나 외부 API 호출 등)이 결정이 안됐을 때,
*   어떻게 구현체를 만들고 테스트를 만들었다면 기존 테스트는 사라질 가능성이 높다!
* - 테스트 객체를 만들기위한 DB(ex H2) 설정환경 구성할 때 폼이 더 들 수 있음
* - 가짜 객체 유연하게 변경이 가능하고 빠르게 만들 수 있음
* - 할 수 없는 것과 할 수 있는데 도입하지 않는 것은 차이가 있음!!
* */