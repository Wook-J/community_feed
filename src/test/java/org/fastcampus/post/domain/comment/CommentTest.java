package org.fastcampus.post.domain.comment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.fastcampus.post.domain.Post;
import org.fastcampus.post.domain.content.CommentContent;
import org.fastcampus.post.domain.content.PostContent;
import org.fastcampus.user.domain.User;
import org.fastcampus.user.domain.UserInfo;
import org.junit.jupiter.api.Test;

class CommentTest {

    private final UserInfo info = new UserInfo("name", "url");
    private final User user = new User(1L, info);
    private final User otherUser = new User(2L, info);

    private final Post post = new Post(1L, user, new PostContent("content"));
    private final Comment comment = new Comment(1L, post, user, new CommentContent("comment"));

    @Test
    void givenCommentCreated_whenLike_thenLikeCountShouldBeOne(){
        // when
        comment.like(otherUser);

        // then
        assertEquals(1, comment.getLikeCount());
    }

    @Test
    void givenCommentCreated_whenLikeBySelf_thenThrowException(){
        // when, then
        assertThrows(IllegalArgumentException.class, () -> comment.like(user));
    }

    @Test
    void givenCommentCreatedAndLike_whenUnlike_thenLikeCountShouldBeZero(){
        // given
        comment.like(otherUser);

        // when
        comment.unlike();

        // then
        assertEquals(0, comment.getLikeCount());
    }

    @Test
    void givenCommentCtreated_whenUnlike_thenLikeCountShouldBeZero(){
        // when
        comment.unlike();

        // then
        assertEquals(0, comment.getLikeCount());
    }

    @Test
    void givenComment_whenUpdateComment_thenShouldBeUpdated(){
        // given
        String newCommentContent = "new content";

        // when
        comment.updateComment(user, newCommentContent);

        // then
        assertEquals(newCommentContent, comment.getContent());
    }

    @Test
    void givenComment_whenUpdateCommentOver100_thenThrowException(){
        // given
        String newCommentContent = "a".repeat(101);

        // when, then
        assertThrows(IllegalArgumentException.class, () -> comment.updateComment(user, newCommentContent));
    }
}
