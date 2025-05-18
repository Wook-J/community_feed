package org.fastcampus.user.application.dto;

import org.fastcampus.user.domain.User;

public record GetUserResponseDto(Long id, String name, String profileImage, Integer followingCount, Integer followerCount) {

    // record는 this로만 생성자를 만들 수 있음!
    public GetUserResponseDto (User user){
        this(user.getId(), user.getName(), user.getProfileImage(), user.followingCount(), user.followerCount());
    }
}
