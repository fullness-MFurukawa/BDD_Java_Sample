package jp.co.fullness.bdd.presentations.register;

import lombok.Data;
import java.io.Serializable;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

/**
 * 商品登録Form
 * @author Fullness,Inc.
 * @since 2025-02-23
 * @version 1.0.1
 */
@Data
public class RegisterForm implements Serializable {
    @NotEmpty(message = "商品名は必須です。")
    private String name;
    @NotNull(message = "単価は必須です。")
    @Min(message = "単価は50以上です。",value = 50)
    @Max(message = "単価は1000以下です。",value = 10000)
    private Integer price;
    @NotEmpty(message = "カテゴリは必須です。")
    private String categoryId;
}