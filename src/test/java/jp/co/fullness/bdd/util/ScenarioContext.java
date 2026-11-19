package jp.co.fullness.bdd.util;

import io.cucumber.spring.ScenarioScope;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * シナリオ実行中のオブジェクトを一時保存するユーティリティクラス
 * @author Fullness,Inc.
 * @since 2025-02-22
 * @version 1.0.1
 */
@Component
@ScenarioScope
public class ScenarioContext {
    // オブジェクトを保存するMap
    private final Map<String, Object> data = new HashMap<>();

    /**
     * オブジェクトをMapに登録する
     * @param key キー
     * @param value　オブジェクト
     */
    public void set(String key, Object value) {
        data.put(key, value);
    }

    /**
     * Mapからオブジェクトを取得「する
     * @param key キー
     * @return オブジェクト
     */
    public  Object get(String key) {
        return data.get(key);
    }

    /**
     * オブジェクトの有無を調べる
     * @param key キー
     * @return true:オブジェクトあり、false:オブジェクト無し
     */
    public boolean contains(String key) {
        return data.containsKey(key);
    }
}
