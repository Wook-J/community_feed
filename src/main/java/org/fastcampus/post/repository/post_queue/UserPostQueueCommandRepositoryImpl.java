package org.fastcampus.post.repository.post_queue;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.fastcampus.post.repository.entity.post.PostEntity;
import org.fastcampus.post.repository.entity.post.UserPostQueueEntity;
import org.fastcampus.post.repository.jpa.JpaPostRepository;
import org.fastcampus.post.repository.jpa.JpaUserPostQueueRepository;
import org.fastcampus.user.repository.entity.UserEntity;
import org.fastcampus.user.repository.jpa.JpaUserRelationRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
public class UserPostQueueCommandRepositoryImpl implements UserPostQueueCommandRepository {

    private final JpaPostRepository jpaPostRepository;
    private final JpaUserRelationRepository jpaUserRelationRepository;
//    private final JpaUserPostQueueRepository jpaUserPostQueueRepository;
    private final UserQueueRedisRepository redisRepository;

    @Override
    @Transactional
    public void publishPost(PostEntity postEntity) {
        UserEntity userEntity = postEntity.getAuthor();
        List<Long> followerIds = jpaUserRelationRepository.findFollowers(userEntity.getId());

//        List<UserPostQueueEntity> userPostQueueEntityList = followerIds.stream()
//            .map(userId -> new UserPostQueueEntity(userId, postEntity.getId(), userEntity.getId()))
//            .toList();
//        jpaUserPostQueueRepository.saveAll(userPostQueueEntityList);
        redisRepository.publishPostToFollowingUserList(postEntity, followerIds);
    }

    @Override
    @Transactional
    public void saveFollowPost(Long userId, Long targetId) {
        List<PostEntity> postEntityList = jpaPostRepository.findAllPostIdsByAuthorId(targetId);

//        List<UserPostQueueEntity> userPostQueueEntityList = postIdList.stream()
//            .map(postId -> new UserPostQueueEntity(userId, postId, targetId))
//            .toList();
//        jpaUserPostQueueRepository.saveAll(userPostQueueEntityList);
        redisRepository.publishPostListToFollowerUser(postEntityList, userId);
    }

    @Override
    @Transactional
    public void deleteUnfollowPost(Long userId, Long targetId) {
//        jpaUserPostQueueRepository.deleteAllByUserIdAndAuthorId(userId, targetId);
        redisRepository.deleteDeleteFeed(userId, targetId);
    }
}
