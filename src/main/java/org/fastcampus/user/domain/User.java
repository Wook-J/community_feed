package org.fastcampus.user.domain;

import java.util.Objects;
import org.fastcampus.common.domain.PositiveIntegerCounter;

public class User {

    private final Long id;
    private final UserInfo info;
    private final PositiveIntegerCounter followingCount;
    private final PositiveIntegerCounter followerCounter;

    public User(Long id, UserInfo userInfo) {   // 단축키 alt + Insert
        if(userInfo == null){
            throw new IllegalArgumentException("userInfo is null");
        }
        this.id = id;
        this.info = userInfo;
        this.followingCount = new PositiveIntegerCounter();
        this.followerCounter = new PositiveIntegerCounter();
    }

    public void follow(User targetUser){
        if(this.equals(targetUser)){
            throw new IllegalArgumentException();
        }

        followingCount.increase();              // 내가 팔로잉하고 있는 숫자 증가
        targetUser.increaseFollowerCount();      // 팔로잉한 user의 팔로우 수 증가
//      targetUser.followerCounter.increase();  // 디미터의 법칙 위배
    }

    public void unfollow(User targetUser){
        if(this.equals(targetUser)){
            throw new IllegalArgumentException();
        }

        followingCount.decrease();              // 내가 팔로잉하고 있는 숫자 감소
        targetUser.decreaseFollowerCount();      // 팔로잉한 user의 팔로우 수 감소
//      targetUser.followerCounter.decrease();  // 디미터의 법칙 위배
    }

    // 객체지향 생활체조에서 말하는 디미터의 법칙(Law of Demeter)
    // 한 객체는 자신이 직접 가지고 있는 객체에만 메시지를 보내야 한다.
    // 즉, 자신의 직접적인 친구에게만 말해야지, 친구의 친구에게까지 접근하지 마라는 뜻임
    private void increaseFollowerCount(){
        followerCounter.increase();
    }

    private void decreaseFollowerCount(){
        followerCounter.decrease();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return id == user.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    public Long getId() {
        return id;
    }

    public int followerCount(){
        return followerCounter.getCount();
    }

    public int followingCount(){
        return followingCount.getCount();
    }

    public UserInfo getInfo() {
        return info;
    }
}
