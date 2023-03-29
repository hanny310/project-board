package com.fast.projectboard.repository;

import com.fast.projectboard.domain.Article;
import com.fast.projectboard.domain.QArticle;
import com.querydsl.core.types.dsl.DateTimeExpression;
import com.querydsl.core.types.dsl.StringExpression;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource //SpringDataJPA를 사용해 만든 repository형태로 controller없이 내부적으로 Rest API가 만들어진다
public interface ArticleRepository extends
        JpaRepository<Article, Long>,
        QuerydslPredicateExecutor<Article>, //해당 엔티티 안에 있는 모든 필드에 대한 기본 검색 기능 추가(대소문자 구분x,전체 입력값 검색)
        QuerydslBinderCustomizer<QArticle>{
    @Override
    default void customize(QuerydslBindings bindings, QArticle root){ //customize 메소드를 오버라이드하여 새부 검색 기능 재정의
        bindings.excludeUnlistedProperties(true); //true로 설정하면 list하지 않은 필드는 검색에서 제외
        bindings.including(root.title, root.hashtag, root.createdAt, root.createdBy); //검색에 사용할 필드 추가
        bindings.bind(root.title).first(StringExpression::containsIgnoreCase); //부분검색과 대소문자 구분을 하지 않게 한다.
        bindings.bind(root.hashtag).first(StringExpression::containsIgnoreCase);
        bindings.bind(root.createdAt).first(DateTimeExpression::eq);// eq로 설정하면 검색이 어려우나 우선 설정
        bindings.bind(root.createdBy).first(StringExpression::containsIgnoreCase);

    };
}
