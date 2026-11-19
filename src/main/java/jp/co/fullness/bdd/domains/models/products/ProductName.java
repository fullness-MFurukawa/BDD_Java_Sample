package jp.co.fullness.bdd.domains.models.products;

import jp.co.fullness.bdd.domains.models.ValueObject;
import jp.co.fullness.bdd.exceptions.ValidateException;
import lombok.Getter;

import java.util.Objects;

/**
 * 商品名を表す値オブジェクト
 * @author Fullness,Inc.
 * @version 1.0
 * @since 2024/10/25
 */
@Getter
public class ProductName implements ValueObject<ProductName> {
    private String value;
    /**
     * コンストラクタ
     * @param value 商品名
     */
    public ProductName(String value) {
        setValue(value);
    }
    /**
     * セッター
     * @param value 商品名
     */
    public void setValue(String value) {
        validateProductName(value);
        this.value = value;
    }
    /**
     * 商品名の値検証メソッド
     * @param value 商品名
     */
    private void validateProductName(String value){
        // Nullまたは空文字列の検証
        if (value == null || value.isEmpty()) {
            throw new ValidateException("商品名は必須です。");
        }
        // 長さの検証
        if (value.length() > 30) {
            throw new ValidateException("商品名は30文字以内である必要があります。");
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
        ProductName that = (ProductName) obj;
        return Objects.equals(value, that.value);
    }
    @Override
    public String toString() {
        return String.format("ProductName{\"value=%s\"}",value);
    }
}
