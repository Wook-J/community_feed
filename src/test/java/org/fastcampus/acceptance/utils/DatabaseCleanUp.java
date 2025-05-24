package org.fastcampus.acceptance.utils;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Table;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Profile("test")
@Component
@Slf4j
public class DatabaseCleanUp implements InitializingBean {

    @PersistenceContext
    private EntityManager entityManager;
    private List<String> tableNames;
    private List<String> notGeneratedTableNames;

    @Override
    public void afterPropertiesSet() throws Exception {
        tableNames = entityManager.getMetamodel().getEntities()
            .stream()
            .filter(entity -> entity.getJavaType().getAnnotation(Entity.class) != null)
            .map(entity -> entity.getJavaType().getAnnotation(Table.class).name())
            .toList();

        notGeneratedTableNames = List.of("community_user_relation", "community_like");
    }

    @Transactional
    public void execute() {
        entityManager.flush();  // 영속성 컨텍스트를 먼저 DB에 반영
        entityManager.createNativeQuery("SET REFERENTIAL_INTEGRITY FALSE").executeUpdate(); // 외래키 제약을 일시적으로 끔
        for(String tableName : tableNames) {
            entityManager.createNativeQuery("TRUNCATE TABLE " + tableName).executeUpdate(); // 모든 테이블 TRUNCATE (데이터 전부 삭제)
            if(!notGeneratedTableNames.contains(tableName)) {   // ID 컬럼이 자동 증가하는 경우, 이를 1부터 재시작
                entityManager.createNativeQuery("ALTER TABLE " + tableName + " ALTER COLUMN ID RESTART WITH 1").executeUpdate();
            }
        }
        entityManager.createNativeQuery("SET REFERENTIAL_INTEGRITY TRUE").executeUpdate();
    }
}
