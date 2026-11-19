package jp.co.fullness.bdd.domains.models.products;

import jp.co.fullness.bdd.domains.models.Entity;
import jp.co.fullness.bdd.domains.models.categories.Category;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;
import java.util.UUID;

/**
 * 商品を表すエンティティクラス
 * @author Fullness,Inc.
 * @version 1.0
 * @since 2024/10/25
 */
@Getter
@NoArgsConstructor
public class Product  implements Entity<Product> {
    private ProductId       productId;      // 商品Id
    private ProductName     productName;    // 商品名
    private ProductPrice    productPrice;   // 商品単価
    private Category        category;       // 商品カテゴリ
    /**
     * コンストラクタ
     * @param productName   商品名
     * @param productPrice  商品単価
     */
    public Product(String productName, Integer productPrice) {
        // 新しいUUIDを生成してProductIdに設定する
        UUID uuId = UUID.randomUUID();
        this.productId = new ProductId(uuId.toString());
        this.productName = new ProductName(productName);
        this.productPrice = new ProductPrice(productPrice);
    }
    /**
     * コンストラクタ
     * @param productId     商品Id
     * @param productName   商品名
     * @param productPrice  商品単価
     */
    public Product(String productId, String productName, Integer productPrice) {
        this.productId = new ProductId(productId);
        this.productName = new ProductName(productName);
        this.productPrice = new ProductPrice(productPrice);
    }
    /**
     * 商品名を変更する
     * @param productName 商品名
     */
    public void changeProductName(String productName) {
        this.productName = new ProductName(productName);
    }
    /**
     * 商品単価を変更する
     * @param productPrice 商品単価
     */
    public void changeProductPrice(Integer productPrice) {
        this.productPrice = new ProductPrice(productPrice);
    }
    /**
     * 商品カテゴリを変更する
     * @param category 商品カテゴリ
     */
    public void changeCategory(Category category) {
        this.category = category;
    }
    @Override
    public int hashCode() {
        return productId != null ? productId.hashCode() : 0;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Product other = (Product) obj;
        return Objects.equals(productId, other.productId);
    }
    @Override
    public Object clone() throws CloneNotSupportedException {
        Product cloned = (Product) super.clone();
        cloned.productId = new ProductId(this.productId.getValue());
        cloned.productName = new ProductName(this.productName.getValue());
        cloned.productPrice = new ProductPrice(this.productPrice.getValue());
        if (this.category != null){
            cloned.category = new
                    Category(
                    this.category.getCategoryId().getValue(),
                    this.category.getCategoryName().getValue());
        }
        return cloned;
    }
    @Override
    public String toString() {
        return String.format(
                "Product{ProductId=%s,ProductName=%s,ProductPrice=%s,Category=%s}",
                productId,productName,productPrice,category);
    }
}
