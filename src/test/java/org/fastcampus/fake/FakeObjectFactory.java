package org.fastcampus.fake;

import org.fastcampus.post.application.CommentService;
import org.fastcampus.post.application.PostService;
import org.fastcampus.post.application.interfaces.CommentRepository;
import org.fastcampus.post.application.interfaces.LikeRepository;
import org.fastcampus.post.application.interfaces.PostRepository;
import org.fastcampus.post.repository.FakeCommentRepository;
import org.fastcampus.post.repository.FakeLikeRepository;
import org.fastcampus.post.repository.FakePostRepository;
import org.fastcampus.user.application.UserRelationService;
import org.fastcampus.user.application.UserService;
import org.fastcampus.user.application.interfaces.UserRelationRepository;
import org.fastcampus.user.application.interfaces.UserRepository;
import org.fastcampus.user.repository.FakeUserRelationRepository;
import org.fastcampus.user.repository.FakeUserRepository;

/*
* 매번 테스트를 만들 때마다 값들을 주입받고 생성하고 만드려면 테스트가 복잡해지기도 하고,
* 만약에 서비스에서 들어가는 인자가 변경되었을 때 모두다 바꿔야 하는 문제가 발생..!
*
* FakeObjectFactory를 Singleton 으로 만들어서 처리하는 방법임!
* */
public class FakeObjectFactory {

    private static final UserRepository fakeUserRepository = new FakeUserRepository();
    private static final UserRelationRepository fakeUserRelationRepository = new FakeUserRelationRepository();
    private static final PostRepository fakePostRepository = new FakePostRepository();
    private static final CommentRepository fakeCommentRepository = new FakeCommentRepository();
    private static final LikeRepository fakeLikeRepository = new FakeLikeRepository();

    private static final UserService userService = new UserService(fakeUserRepository);
    private static final UserRelationService userRelationService = new UserRelationService(userService, fakeUserRelationRepository);
    private static final PostService postService = new PostService(userService,fakePostRepository, fakeLikeRepository);
    private static final CommentService commentService = new CommentService(userService, postService, fakeCommentRepository, fakeLikeRepository);

    private FakeObjectFactory() {}

    public static UserService getUserService() {
        return userService;
    }

    public static UserRelationService getUserRelationService() {
        return userRelationService;
    }

    public static PostService getPostService() {
        return postService;
    }

    public static CommentService getCommentService() {
        return commentService;
    }

}
