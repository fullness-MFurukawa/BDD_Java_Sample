package jp.co.fullness.bdd.presentations.search;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import java.io.Serializable;

/**
 * 演習-14:商品キーワード検索コントローラとFormオブジェクトを作成する
 * 商品検索Form
 */
@Data
public class KeywordForm implements Serializable {
    @NotEmpty(message = "キーワードを入力してください。")
    private String keyword;
}

