package jp.co.fullness.bdd.domains.models.categories;
import jp.co.fullness.bdd.domains.models.ValueObject;
import jp.co.fullness.bdd.exceptions.ValidateException;
import lombok.Getter;

import java.util.Objects;
import java.util.UUID;
/**
 * 商品カテゴリIdを表す値オブジェクト
 * @author Fullness,Inc.
 * @version 1.0
 * @since 2024/10/25
 */
@Getter
public class CategoryId implements ValueObject<CategoryId> {
    private String value;   // 保持する値
    /**
     * コンストラクタ
     * @param value 商品カテゴリId
     */
    public CategoryId(String value) {
        setValue(value); // 値の設定をセッターに委譲
    }
    /**
     * valueフィールドのセッター
     * @param value 内部保持する値
     */
    public void setValue(String value) {
        validateCategoryId(value);
        this.value = value;
    }
    /**
     * 商品カテゴリIdの値検証メソッド
     * @param value 商品カテゴリId
     */
    private void validateCategoryId(String value){
        // Nullまたは空文字列の検証
        if (value == null || value.isEmpty()) {
            throw new ValidateException("商品カテゴリIdは必須です。");
        }
        // 長さの検証
        if (value.length() != 36) {
            throw new ValidateException("商品カテゴリIdは36文字である必要があります。");
        }
        // UUID形式であることを検証
        try {
            UUID.fromString(value);
        } catch (IllegalArgumentException e) {
            throw new ValidateException("商品カテゴリIdはUUID形式でなければなりません。");
        }
    }
    @Override
    public int hashCode() {
        return value != null ? value.hashCode() : 0;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        CategoryId that = (CategoryId) obj;
        return Objects.equals(value, that.value);
    }
    @Override
    public String toString() {
        return String.format("CategoryId{\"value=%s\"}",value);
    }
}
