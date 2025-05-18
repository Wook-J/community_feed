package org.fastcampus.post.repository.entity.like;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Embeddable
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class LikeIdEntity {
    private Long targetId;      // post나 comment의 id
    private Long userId;        // 좋아요를 누르는 user의 id
    private String targetType;  // like의 target이 post인지 comment 인지
}
