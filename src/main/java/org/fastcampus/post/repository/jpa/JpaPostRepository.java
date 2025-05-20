package org.fastcampus.post.repository.jpa;

import org.fastcampus.post.repository.entity.post.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface JpaPostRepository extends JpaRepository<PostEntity, Long> {

    @Modifying
    @Query(value = "UPDATE PostEntity p "
        + "SET p.content = :#{#postEntity.getContent()}, "
        + "p.state = :#{#postEntity.getState()}, "
        + "p.modDt = now() "
        + "WHERE p.id = :#{#postEntity.id}")
    void updatePost(PostEntity postEntity);

    @Modifying
    @Query(value = "UPDATE PostEntity p "
        + "SET p.likeCount = :#{#postEntity.likeCount}, "
        + "p.modDt = now() "
        + "WHERE p.id = :#{#postEntity.getId()}")
    void updateLikeCount(PostEntity postEntity);
}
