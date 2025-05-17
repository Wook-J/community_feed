package org.fastcampus.post.domain.comment;

import org.fastcampus.common.domain.PositiveIntegerCounter;
import org.fastcampus.post.domain.Post;
import org.fastcampus.post.domain.content.Content;
import org.fastcampus.user.domain.User;

public class Comment {

    private final Long id;
    private final Post post;
    private final User author;
    private final Content content;
    private final PositiveIntegerCounter likeCount;

    public Comment(Long id, Post post, User author, Content content) {
        if(author == null){
            throw new IllegalArgumentException("author is null");
        }

        if(post == null){
            throw new IllegalArgumentException("post is null");
        }

        if(content == null){
            throw new IllegalArgumentException("content is null");
        }

        this.id = id;
        this.post = post;
        this.author = author;
        this.content = content;
        this.likeCount = new PositiveIntegerCounter();
    }

    public void like(User user){
        if(this.author.equals(user)){
            throw new IllegalArgumentException("author is the same");
        }
        likeCount.increase();
    }

    public void unlike(){
        this.likeCount.decrease();
    }

    public void updateComment(User user, String updatedContent){
        if(!this.author.equals(user)){
            throw new IllegalArgumentException("author is not the same");
        }

        this.content.updateContent(updatedContent);
    }

    public int getLikeCount() {
        return likeCount.getCount();
    }

    public String getContent() {
        return content.getContentText();
    }
}
