package jp.co.fullness.bdd.domains.models.categories;

import jp.co.fullness.bdd.domains.models.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;
import java.util.UUID;

/**
 * 商品カテゴリを表すエンティティクラス
 * @author Fullness,Inc.
 * @version 1.0
 * @since 2024/10/25
 */
@Getter
@NoArgsConstructor
public class Category implements Entity<Category> {
    private CategoryId      categoryId;     // 商品カテゴリId
    private CategoryName    categoryName;   // 商品カテゴリ名
    /**
     * コンストラクタ
     * 商品カテゴリを復元する際に使用する
     * @param categoryId 商品カテゴリId
     * @param categoryName 商品カテゴリ名
     */
    public Category(String categoryId, String categoryName) {
        this.categoryId = new CategoryId(categoryId);
        this.categoryName = new CategoryName(categoryName);
    }
    /**
     * コンストラクタ
     * 新しい商品カテゴリを生成する際に使用する
     * @param categoryName 商品カテゴリ名
     */
    public Category(String categoryName) {
        this.categoryName = new CategoryName(categoryName);
        // 新しいUUIDを生成してCategoryIdに設定する
        UUID uuId = UUID.randomUUID();
        this.categoryId = new CategoryId(uuId.toString());
    }

    /**
     * 商品カテゴリ名を変更する
     * @param categoryName 商品カテゴリ名
     */
    public void changeCategoryName(String categoryName) {
        this.categoryName = new CategoryName(categoryName);
    }

    /**
     * 等価性検証
     * @param obj 検証対象
     * @return 検証結果
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Category other = (Category) obj;
        return Objects.equals(categoryId, other.categoryId);
    }

    /**
     * 等価性検証
     * @return 等価性検証結果
     */
    @Override
    public int hashCode() {
        return categoryId != null ? categoryId.hashCode() : 0;
    }

    /**
     * インスタンスの複製
     * @return 複製結果
     * @throws CloneNotSupportedException 複製禁止例外
     */
    @Override
    public Object clone() throws CloneNotSupportedException {
        Category cloned = (Category) super.clone();
        cloned.categoryId = new CategoryId(this.categoryId.getValue());
        cloned.categoryName = new CategoryName(this.categoryName.getValue());
        return cloned;
    }

    @Override
    public String toString() {
        return "Category{" +
                "categoryId=" + categoryId.toString() +
                ", categoryName=" + categoryName.toString() +
                '}';
    }
}
