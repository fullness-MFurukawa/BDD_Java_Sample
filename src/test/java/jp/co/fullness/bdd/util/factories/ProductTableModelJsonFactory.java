package jp.co.fullness.bdd.util.factories;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jp.co.fullness.bdd.infrastructures.mybatis.products.ProductTableModel;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * JSON形式のデータからProductTableModelを生成するFactory
 * 提供
 */
@Component
public class ProductTableModelJsonFactory  implements EvaluationDataFactory<String, ProductTableModel>{
    /**
     * 単一データを変換した結果を返す
     *
     * @param data 変換対象
     * @return 変換結果
     */
    @Override
    public ProductTableModel convert(String data) throws Exception {
        var objectMapper = new ObjectMapper();
        return objectMapper.readValue(data, ProductTableModel.class);
    }

    /**
     * 複数データをリストに変換した結果を返す
     *
     * @param data 変換対象
     * @return 変換結果
     */
    @Override
    public List<ProductTableModel> convertList(String data) throws Exception {
        var objectMapper = new ObjectMapper();
        return objectMapper.readValue(data,
                new TypeReference<List<ProductTableModel>>() {});
    }
}
