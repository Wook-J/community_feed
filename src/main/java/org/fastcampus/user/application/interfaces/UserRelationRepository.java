package org.fastcampus.user.application.interfaces;

import org.fastcampus.user.domain.User;

public interface UserRelationRepository {

    boolean isAlreadyFollow(User user, User targetUser);
    void save(User user, User targetUser);
    void delete(User user, User targetUser);

    /*
    * 변경이 있는 객체 전체를 던지는 것을 선호...!
    * user의 count도 같이 업데이트 되어야 하기 때문
    * 트랜잭션 단위로 변경이 일어나야하는 것들은 메소드 하나로 묶어두면 유지보수가 쉬워짐
    * User 전체를 parameter로 하여 domain 데이터로 넘겨주면 User 내부가 변하더라도 interface는 변화가 없음!
    * */
}
