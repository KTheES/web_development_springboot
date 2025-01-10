package me.kimeunsoo.springbootdeveloper.dto;

import lombok.Getter;
import me.kimeunsoo.springbootdeveloper.domain.Article;

@Getter
public class ArticleListViewResponse {
    private final long id;
    private final String title;
    private final String content;

    public ArticleListViewResponse(Article article){
        this.id = article.getId();
        this.title = article.getTitle();
        this.content = article.getContent();
    }
    /*
        다 작성한분들 controller 패키지에
        BlogViewController.java 파일 만들겠습니다.
     */


}
