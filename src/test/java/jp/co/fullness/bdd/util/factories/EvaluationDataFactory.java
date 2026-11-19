package jp.co.fullness.bdd.util.factories;

import java.util.List;

/**
 * テスト評価データ生成Factoryインターフェイス
 * @author Fullness,Inc.
 * @since 2025-02-23
 * @version 1.0.1
 */
public interface EvaluationDataFactory<IN,OUT>{
    /**
     * 単一データを変換した結果を返す
     * @param data 変換対象
     * @return 変換結果
     */
    OUT convert(IN data) throws Exception;

    /**
     * 複数データをリストに変換した結果を返す
     * @param data 変換対象
     * @return 変換結果
     */
    List<OUT> convertList(IN data) throws Exception;
}
