package org.fastcampus.post.repository.jpa;

import org.fastcampus.post.repository.entity.comment.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface JpaCommentRepository extends JpaRepository<CommentEntity, Long> {

    @Modifying
    @Query(value = "UPDATE CommentEntity c "
        + "SET c.content = :#{#commentEntity.getContent()}, "
        + "c.modDt = now() "
        + "WHERE c.id = :#{#commentEntity.getId()}")
    void updateCommentEntity(CommentEntity commentEntity);

    @Modifying
    @Query(value = "UPDATE CommentEntity c "
        + "SET c.likeCount = c.likeCount + :likeCount, "
        + "c.modDt = now() "
        + "WHERE c.id = :commentId")
    void updateLikeCount(Long commentId, Integer likeCount);
}
