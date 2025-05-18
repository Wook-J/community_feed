package org.fastcampus.user.repository.jpa;

import java.util.List;
import org.fastcampus.user.application.dto.GetUserListResponseDto;
import org.fastcampus.user.repository.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface JpaUserListQueryRepository extends JpaRepository<UserEntity, Long> {

    // 나를 팔로잉하는 유저의 이름과 프로필 이미지를 불러오기
    @Query(value = "SELECT new org.fastcampus.user.application.dto.GetUserListResponseDto(u.name, u.profileImage) "
        + "FROM UserRelationEntity ur "
        + "INNER JOIN UserEntity u ON ur.followerUserId = u.id "
        + "WHERE ur.followingUserId = :userId")
    List<GetUserListResponseDto> getFollowingUserList(Long userId);

    // 내가 팔로우 하는 유저의 이름고 프로필 이미지를 불러오기
    @Query(value = "SELECT new org.fastcampus.user.application.dto.GetUserListResponseDto(u.name, u.profileImage) "
        + "FROM UserRelationEntity ur "
        + "INNER JOIN UserEntity u ON ur.followingUserId = u.id "
        + "WHERE ur.followerUserId = :userId")
    List<GetUserListResponseDto> getFollowerUserList(Long userId);
}
