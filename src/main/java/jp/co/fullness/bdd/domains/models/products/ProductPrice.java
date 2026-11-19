package jp.co.fullness.bdd.domains.models.products;

import jp.co.fullness.bdd.domains.models.ValueObject;
import jp.co.fullness.bdd.exceptions.ValidateException;
import lombok.Getter;

import java.util.Objects;

/**
 * 商品単価を表す値オブジェクト
 * @author Fullness,Inc.
 * @version 1.0
 * @since 2024/10/25
 */
@Getter
public class ProductPrice  implements ValueObject<ProductPrice> {
    private Integer value;
    /**
     * コンストラクタ
     * @param value 商品単価
     */
    public ProductPrice(Integer value) {
        setValue(value);
    }
    /**
     * セッター
     * @param value 商品単価
     */
    public void setValue(Integer value) {
        validateProductPrice(value);
        this.value = value;
    }
    /**
     * 商品単価の値検証メソッド
     * @param value 商品単価
     */
    private void validateProductPrice(Integer value){
        // Nullまたは空文字列の検証
        if (value == null) {
            throw new ValidateException("商品単価は必須です。");
        }
        // 単価の有効範囲検証
        if (value < 50 || value > 10000) {
            throw new ValidateException("商品単価の有効範囲は50以上10000以下です。");
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
        ProductPrice that = (ProductPrice) obj;
        return Objects.equals(value, that.value);
    }
    @Override
    public String toString() {
        return String.format("ProductPrice{\"value=%s\"}",value);
    }
}
