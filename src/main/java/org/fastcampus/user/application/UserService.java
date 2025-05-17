package org.fastcampus.user.application;

import java.util.IllformedLocaleException;
import org.fastcampus.user.application.dto.CreateUserRequestDto;
import org.fastcampus.user.application.interfaces.UserRepository;
import org.fastcampus.user.domain.User;
import org.fastcampus.user.domain.UserInfo;

/*
 * 비즈니스 로직을 처리한다기 보다는 다른 객체와 협업을 하는 역할을 주로 함
 * 클린아키텍처 : 유저랑 가깝고, 비즈니스로직에서 먼 컴포넌트를 저수준 컴포넌트라고함
 * 저수준 컴포넌트를 직접 구현한 구현체를 더 높은 도메인이나 어플리케이션 컴포넌트가 의존해서는 안됨!
 * */

// -> 구현체가 없어도 저장을 하는 Service 레이어를 만들 수 있음!
// 이후 dto 객체 만듦
// -> 이 역시 프로젝트마다 클래스 명명할 때 네이밍규칙을 만들면 편함
// 행동, 변경이나 생성할 객체의 이름, 요청이 req인지 resp인지, dto 객체
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(CreateUserRequestDto dto){
        UserInfo info = new UserInfo(dto.name(), dto.profileImageUrl());
        User user = new User(null, info);
        return userRepository.save(user);
    }

    public User getUser(Long id){
        return userRepository.findById(id).orElseThrow(IllformedLocaleException::new);
    }
}

