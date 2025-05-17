package org.fastcampus.post.application;

import org.fastcampus.post.application.interfaces.PostRepository;
import org.fastcampus.post.domain.Post;
import org.fastcampus.user.application.UserService;

public class PostService {

    private final UserService userService;
    private final PostRepository postRepository;

    public PostService(UserService userService, PostRepository postRepository) {
        this.userService = userService;
        this.postRepository = postRepository;
    }

    public Post getPost(Long id) {
        return postRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("No post found with id: " + id));
    }
}
