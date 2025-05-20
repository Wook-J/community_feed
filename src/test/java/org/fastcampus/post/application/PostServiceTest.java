package org.fastcampus.post.application;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.fastcampus.post.application.dto.LikeRequestDto;
import org.fastcampus.post.application.dto.UpdatePostRequestDto;
import org.fastcampus.post.domain.Post;
import org.fastcampus.post.domain.content.PostPublicationState;
import org.junit.jupiter.api.Test;

class PostServiceTest extends PostApplicationTestTemplate {
    
//    extends PostApplicationTestTemplate 상속받으면서 다 가져옴
//    private final UserService userService = FakeObjectFactory.getUserService();
//    private final PostService postService = FakeObjectFactory.getPostService();
//
//    private final User user = userService.createUser(new CreateUserRequestDto("user1", null));
//    private final User otherUser = userService.createUser(new CreateUserRequestDto("user1", null));
//
//    private final CreatePostRequestDto dto = new CreatePostRequestDto(user.getId(), "this is test content", PostPublicationState.PUBLIC);

    @Test
    void givenPostRequestDto_whenCreate_thenReturnPost() {
        // when
        Post savePost = postService.createPost(postRequestDto);

        // then
        Post post = postService.getPost(savePost.getId());
        assertEquals(savePost, post);
    }

    @Test
    void givenCreatePost_whenUpdate_thenReturnUpdatedPost() {
        // given
        Post savePost = postService.createPost(postRequestDto);

        // when
        UpdatePostRequestDto updateDto = new UpdatePostRequestDto(user.getId(), "updated-content", PostPublicationState.PRIVATE);
        Post updatedPost = postService.updatePost(savePost.getId(), updateDto);

        // then
        assertEquals(savePost.getId(), updatedPost.getId());
        assertEquals(savePost.getAuthor(), updatedPost.getAuthor());
        assertEquals(savePost.getContent(), updatedPost.getContent());
    }

    @Test
    void givenCreatedPost_whenLiked_thenReturnPostWithLike(){
        // given
        Post savePost = postService.createPost(postRequestDto);

        // when
        LikeRequestDto likeRequestDto = new LikeRequestDto(savePost.getId(), otherUser.getId());
        postService.likePost(likeRequestDto);

        // then
        assertEquals(1, savePost.getLikeCount());
    }

    @Test
    void givenCreatedPost_whenLikedTwice_thenReturnPostWithLike(){
        // given
        Post savePost = postService.createPost(postRequestDto);

        // when
        LikeRequestDto likeRequestDto = new LikeRequestDto(savePost.getId(), otherUser.getId());
        postService.likePost(likeRequestDto);
        postService.likePost(likeRequestDto);

        // then
        assertEquals(1, savePost.getLikeCount());
    }

    @Test
    void givenCreatedPostLiked_whenUnliked_thenReturnPostWithoutLike(){
        // given
        Post savePost = postService.createPost(postRequestDto);
        LikeRequestDto likeRequestDto = new LikeRequestDto(savePost.getId(), otherUser.getId());
        postService.likePost(likeRequestDto);

        // when
        postService.unlikePost(likeRequestDto);

        // then
        assertEquals(0, savePost.getLikeCount());
    }

    @Test
    void givenCreatedPost_whenUnliked_thenReturnPostWithoutLike(){
        // given
        Post savePost = postService.createPost(postRequestDto);

        // when
        LikeRequestDto likeRequestDto = new LikeRequestDto(savePost.getId(), otherUser.getId());
        postService.unlikePost(likeRequestDto);

        // then
        assertEquals(0, savePost.getLikeCount());
    }
}
