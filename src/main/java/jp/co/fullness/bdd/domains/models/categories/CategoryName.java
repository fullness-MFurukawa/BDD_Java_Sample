package jp.co.fullness.bdd.domains.models.categories;

import jp.co.fullness.bdd.domains.models.ValueObject;
import jp.co.fullness.bdd.exceptions.ValidateException;
import lombok.Getter;

import java.util.Objects;

/**
 * 商品カテゴリ名を表す値オブジェクト
 * @author Fullness,Inc.
 * @version 1.0
 * @since 2024/10/25
 */
@Getter
public class CategoryName implements ValueObject<CategoryName> {
    private String value;   //　保持する値
    /**
     * コンストラクタ
     * @param value 商品カテゴリ名
     */
    public CategoryName(String value) {
        setValue(value);
    }

    /**
     * valueフィールドのセッター
     * @param value 内部保持する値
     */
    public void setValue(String value) {
        validateCategoryName(value);
        this.value = value;
    }

    /**
     * 商品カテゴリ名の値検証メソッド
     * @param value 商品カテゴリ名
     */
    private void validateCategoryName(String value){
        // Nullまたは空文字列の検証
        if (value == null || value.isEmpty()) {
            throw new ValidateException("商品カテゴリ名は必須です。");
        }
        // 長さの検証
        if (value.length() > 30) {
            throw new ValidateException("商品カテゴリ名は30文字以内である必要があります。");
        }
    }

    @Override
    public int hashCode() {
        return value != null ? value.hashCode() : 0;
    }
    @Override
    public boolean equals(Object obj) {
        // 自身との比較
        if (this == obj) return true;
        // nullまたは型が異なる場合はfalseを返す
        if (!(obj instanceof CategoryName that)) return false;
        // 型が同じ場合はフィールドの値を比較
        return Objects.equals(value, that.value);
    }
    @Override
    public String toString() {
        return String.format("CategoryName{\"value=%s\"}",value);
    }
}
