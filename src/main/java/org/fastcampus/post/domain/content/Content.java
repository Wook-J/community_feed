package org.fastcampus.post.domain.content;

import org.fastcampus.post.domain.common.DatetimeInfo;

public abstract class Content {
    protected String contentText;
    protected final DatetimeInfo datetimeInfo;

    // 상속받게 된다면 checkText만 재활용해주면 Content에서 text 검증 가능!
    protected Content(String contentText) {
        checkText(contentText);
        this.contentText = contentText;
        this.datetimeInfo = new DatetimeInfo();
    }

    public void updateContent(String updateContent){
        checkText(updateContent);           // update할 때 구현체마다 길이 따로 체크 가능!
        this.contentText = updateContent;
        this.datetimeInfo.updateEditDatetime();
    }

    /* 이 메서드를 PostContent와 CommentContent 에서 사용
    * SOLID 원칙 : 객체지향 설계에서 유지보수성과 확장성을 높이기 위한 5가지 핵심 원칙
    * - SRP(Single Responsibility Principle) : 클래스는 하나의 책임만 가짐
    *    -> 하나의 기능이 변경될 떄에는 하나의 객체만 변경되어야 한다!
    * - OCP(Open/Closed Principle) : 확장에는 열려 있고, 수정에는 닫혀 있어야 함
    * */
    protected abstract void checkText(String contentText);

    public String getContentText() {
        return contentText;
    }
}
