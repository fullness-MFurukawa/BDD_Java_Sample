package jp.co.fullness.bdd.infrastructures.mybatis.categories;

import org.apache.ibatis.annotations.Mapper;
import java.util.List;

/**
 * カテゴリテーブルをアクセスするMapperインターフェイス
 * @author Fullness,Inc.
 * @since 2025-02-23
 * @version 1.0.1
 */
@Mapper
public interface CategoryMapper {
    /**
     * すべての商品カテゴリを取得する
     * @return 取得した商品カテゴリのリスト
     */
    List<CategoryTableModel> findAll();
    /**
     * 指定されたカテゴリIdの商品カテゴリを取得する
     * @param categoryId 取得対象を表すカテゴリId
     * @return 取得した商品カテゴリ
     */
    CategoryTableModel findById(String categoryId);
}
