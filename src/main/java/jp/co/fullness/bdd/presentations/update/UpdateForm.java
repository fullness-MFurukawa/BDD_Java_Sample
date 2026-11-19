package jp.co.fullness.bdd.presentations.update;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.io.Serializable;
/**
 * 演習-16:商品変更コントローラとFormオブジェクトを作成する
 * 商品変更Form
 */
@Data
public class UpdateForm implements Serializable {
    private String id;
    @NotEmpty(message = "商品名は必須です。")
    private String name;

    @NotNull(message = "単価は必須です。")
    @Min(message = "単価は50以上です。",value = 50)
    @Max(message = "単価は1000以下です。",value = 10000)
    private Integer price;
}
