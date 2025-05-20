package org.fastcampus.post.application;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.fastcampus.post.application.dto.LikeRequestDto;
import org.fastcampus.post.application.dto.UpdateCommentRequestDto;
import org.fastcampus.post.domain.comment.Comment;
import org.junit.jupiter.api.Test;

class CommentServiceTest extends PostApplicationTestTemplate{

    @Test
    void givenCreatedCommentRequestDto_whenCreateComment_thenReturnComment(){
        // when
        Comment comment = commentService.createComment(commentRequestDto);

        // then
        String content = comment.getContent();
        assertEquals(commentContentText, content);
    }

    @Test
    void givenCreateComment_whenUpdateComment_thenReturnUpdatedComment(){
        // given
        Comment comment = commentService.createComment(commentRequestDto);

        // when
        UpdateCommentRequestDto updateCommentRequestDto = new UpdateCommentRequestDto(user.getId(), "updated-content");
        Comment updatedComment = commentService.updateComment(comment.getId(), updateCommentRequestDto);

        // then
        assertEquals(comment.getId(), updatedComment.getId());
        assertEquals(comment.getAuthor(), updatedComment.getAuthor());
        assertEquals(comment.getContent(), updatedComment.getContent());
    }

    @Test
    void givenComment_whenLikeComment_thenReturnCommentWithLike(){
        // given
        Comment comment = commentService.createComment(commentRequestDto);

        // when
        LikeRequestDto likeRequestDto = new LikeRequestDto(comment.getId(), otherUser.getId());
        commentService.likeComment(likeRequestDto);

        // then
        assertEquals(1, comment.getLikeCount());
    }

    @Test
    void givenComment_whenUnlikeComment_thenReturnCommentWithoutLike(){
        // given
        Comment comment = commentService.createComment(commentRequestDto);

        // when
        LikeRequestDto likeRequestDto = new LikeRequestDto(comment.getId(), otherUser.getId());
        commentService.likeComment(likeRequestDto);
        commentService.unlikeComment(likeRequestDto);

        // then
        assertEquals(0, comment.getLikeCount());
    }
}

/*
* PostService와 CommentService를 Fake와 test 도구를 이용해서 작성해봄!
* - 아직 스프링 객체나 DB 같은 것들이 없이 순수 테스트프레임 워크인 JUnit5만 이용해서 비즈니스 로직 모두 완성!
* - Layer를 철저히 분리해서 앞으로는 어떤 DB/프레임워크를 사용하건 아무런 상관이 없게되었다!
* - 기능이 변경되어도 Domain과 Service에 test case를 만들어두었기 때문에 안정성이 충분히 생김
* - 소프트웨어 아키텍처로 가는 첫 걸음~~
* - 이제 실제로 SpringFrameWork와 MySQL를 연동해보면서 아키텍처가 원하는 대로 동작하는지 추가로 알아보자!
* */