package jp.co.fullness.bdd.applications.hooks;

import io.cucumber.java.After;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

/**
 * Productテーブルの復元フッククラス
 * @author Fullness,Inc.
 * @since 2025-02-23
 * @version 1.0.1
 */
public class ProductRestoreHooks {
    // データ復元用JdbcTemplate
    private final JdbcTemplate jdbcTemplate;

    /**
     * コンストラクタ
     * @param jdbcTemplate データ復元用JdbcTemplate
     */
    public ProductRestoreHooks(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * productテーブルのデータ復元
     */
    @After("@Restore")
    @Transactional
    public void  teardown() {
        jdbcTemplate.execute("select setval ('product_seq', 1, false);");
        jdbcTemplate.execute("TRUNCATE TABLE public.product;");
        jdbcTemplate.execute("TRUNCATE TABLE public.product RESTART IDENTITY;");
        jdbcTemplate.update("DELETE FROM product;");
        jdbcTemplate.update("INSERT INTO public.product(id, obj_id, name, price, category_id) VALUES (nextval('product_seq') , '0bd71345-c8b8-423b-9d3f-48d17dcb7874' , '水性ボールペン(黒)' , 150 , 1);");
        jdbcTemplate.update("INSERT INTO public.product(id, obj_id, name, price, category_id) VALUES (nextval('product_seq') , 'aad387ae-d993-4a5b-9d8c-40d6b748a570' , '水性ボールペン(赤)' , 150 , 1);");
        jdbcTemplate.update("INSERT INTO public.product(id, obj_id, name, price, category_id) VALUES (nextval('product_seq') , 'e7d1bef5-80f0-40e8-b4ff-a9f65c10ed41' , '水性ボールペン(青)' , 150 , 1);");
        jdbcTemplate.update("INSERT INTO public.product(id, obj_id, name, price, category_id) VALUES (nextval('product_seq') , 'f2b4222b-d584-4517-a297-53b98c8dbc71' , '油性ボールペン(黒)' , 130 , 1);");
        jdbcTemplate.update("INSERT INTO public.product(id, obj_id, name, price, category_id) VALUES (nextval('product_seq') , 'fdb75d1f-0f90-453c-8d4e-a8db01647be3' , '油性ボールペン(赤)' , 130 , 1);");
        jdbcTemplate.update("INSERT INTO public.product(id, obj_id, name, price, category_id) VALUES (nextval('product_seq') , '50ec6f65-1203-4b71-9d21-630462740d54' , '油性ボールペン(青)' , 130 , 1);");
        jdbcTemplate.update("INSERT INTO public.product(id, obj_id, name, price, category_id) VALUES (nextval('product_seq') , '40bf7198-6795-4a3d-bc88-e59e1fa52436' , '蛍光ペン(黄)' , 180 , 1);");
        jdbcTemplate.update("INSERT INTO public.product(id, obj_id, name, price, category_id) VALUES (nextval('product_seq') , '7612d4bd-32b9-4d8e-bb40-a8238f4f4104' , '蛍光ペン(赤)' , 180 , 1);");
        jdbcTemplate.update("INSERT INTO public.product(id, obj_id, name, price, category_id) VALUES (nextval('product_seq') , '51f249c6-d223-4ba1-9e13-8750f0a341eb' , '蛍光ペン(青)' , 180 , 1);");
        jdbcTemplate.update("INSERT INTO public.product(id, obj_id, name, price, category_id) VALUES (nextval('product_seq') , 'bec62907-d657-4034-bbfb-651e3f7534f6' , '蛍光ペン(緑)' , 180 , 1);");
        jdbcTemplate.update("INSERT INTO public.product(id, obj_id, name, price, category_id) VALUES (nextval('product_seq') , 'cce6eadc-38a5-41fe-950b-48281df253b5' , '色鉛筆(12色)' , 900 , 1);");
        jdbcTemplate.update("INSERT INTO public.product(id, obj_id, name, price, category_id) VALUES (nextval('product_seq') , 'bf2a41a6-d662-4999-a580-1fd562f51422' , '色鉛筆(24色)' , 1800 , 1);");
        jdbcTemplate.update("INSERT INTO public.product(id, obj_id, name, price, category_id) VALUES (nextval('product_seq') , 'e71c1ee3-f2e8-4e9d-94cb-8beca3f0acd2' , '色鉛筆(36色)' , 3200 , 1);");
    }
}
