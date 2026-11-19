package jp.co.fullness.bdd.domains.models.products;
import jp.co.fullness.bdd.domains.models.ValueObject;
import jp.co.fullness.bdd.exceptions.ValidateException;
import lombok.Getter;
import java.util.Objects;
import java.util.UUID;
/**
 * 商品Idを表す値オブジェクト
 * @author Fullness,Inc.
 * @version 1.0
 * @since 2024/10/25
 */
@Getter
public class ProductId implements ValueObject<ProductId> {
    private String value;
    /**
     * コンストラクタ
     * @param value 商品Id
     */
    public ProductId(String value) {
        setValue(value);
    }
    /**
     * セッター
     * @param value 商品Id
     */
    public void setValue(String value) {
        validateProductId(value);
        this.value = value;
    }
    /**
     * 商品Idの値検証メソッド
     * @param value 商品Id
     */
    private void validateProductId(String value){
        // Nullまたは空文字列の検証
        if (value == null || value.isEmpty()) {
            throw new ValidateException("商品Idは必須です。");
        }
        // 長さの検証
        if (value.length() != 36) {
            throw new ValidateException("商品Idは36文字である必要があります。");
        }
        // UUID形式であることを検証
        try{
            UUID.fromString(value);
        }catch(IllegalArgumentException e){
            throw new ValidateException("商品IdはUUID形式でなければなりません。");
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
        ProductId that = (ProductId) obj;
        return Objects.equals(value, that.value);
    }
    @Override
    public String toString() {
        return String.format("ProductId{\"value=%s\"}",value);
    }
}
