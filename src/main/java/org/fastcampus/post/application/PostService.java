package org.fastcampus.post.application;

import org.fastcampus.post.application.dto.CreatePostRequestDto;
import org.fastcampus.post.application.dto.LikeRequestDto;
import org.fastcampus.post.application.interfaces.LikeRepository;
import org.fastcampus.post.application.interfaces.PostRepository;
import org.fastcampus.post.domain.Post;
import org.fastcampus.user.application.UserService;
import org.fastcampus.user.domain.User;

public class PostService {

    private final UserService userService;

    private final PostRepository postRepository;
    private final LikeRepository likeRepository;

    public PostService(UserService userService, PostRepository postRepository, LikeRepository likeRepository) {
        this.userService = userService;
        this.postRepository = postRepository;
        this.likeRepository = likeRepository;
    }

    public Post getPost(Long id) {
        return postRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("No post found with id: " + id));
    }

    public Post createPost(CreatePostRequestDto dto){
        User author = userService.getUser(dto.userId());
        Post post = Post.createPost(null, author, dto.content(), dto.state());
        return postRepository.save(post);
        /*
        * 정적 메서드 사용해서 생성자와 유사하게 생성!
        * - 어떤 생성자를 나타내는지 1번 더 메서드 명으로 알려줌! -> 유지보수가 상대적으로 쉬워짐!
        * - 테스트케이스 작성할 때에도 명확하게 이름을 나타내줄 수 있음
        * */
        // 현재 도메인에 dto를 의존시키지 않음
    }

    public Post updatePost(Long id, CreatePostRequestDto dto){
        Post post = getPost(id);
        User user = userService.getUser(dto.userId());

        post.updatePost(user, dto.content(), dto.state());
        return postRepository.save(post);
    }

    public void likePost(LikeRequestDto dto) {
        Post post = getPost(dto.targetId());
        User user = userService.getUser(dto.userId());

        if(likeRepository.checkLike(post, user)){
            return;
        }

        post.like(user);
        likeRepository.like(post, user);
    }

    public void unLikePost(LikeRequestDto dto) {
        Post post = getPost(dto.targetId());
        User user = userService.getUser(dto.userId());

        if(likeRepository.checkLike(post, user)){
            post.unlike();
            likeRepository.unlike(post, user);
        }
    }

}
