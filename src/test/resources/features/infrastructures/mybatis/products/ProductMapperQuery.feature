Feature: 商品CRUD操作インターフェイスの問合せ
#
# このFeatureは、永続化層から商品をCRUD操作する
# ProductMapperインターフェイスの問合せメソッドの単体テストを目的とする
# - パッケージ: jp.co.fullness.bdd.infrastructures.mybatis.products
# - インターフェイス名: ProductMapper
# テスト対象:
# - findById(): 指定されたIdの商品を取得する
# - findByNameContains(): 指定された商品キーワードに該当する商品を取得する
# - exists():指定された商品名の存在有無を取得する
  #
  # - findById(): 指定されたIdの商品を取得する
  #
  Scenario: 存在する商品Idで商品が取得できることを検証する
    Given 取得対象の商品Idを用意する "50ec6f65-1203-4b71-9d21-630462740d54"
    When 商品Idで商品を取得する
    Then 商品Idで取得した結果を評価する
    """
      objId: 50ec6f65-1203-4b71-9d21-630462740d54
      name: 油性ボールペン(青)
      price: 130
    """
  Scenario: 存在しない商品Idで商品を検索するとnullであることを検証する
    Given 取得対象の商品Idを用意する "50ec6f65-1203-4b71-9d21-630462740d57"
    When 商品Idで商品を取得する
    Then 商品Idで取得した結果がnullであることを評価する
  #
  # - findByNameContains(): 指定された商品キーワードに該当する商品を取得する
  #
  Scenario: 存在する商品のキーワードで商品を取得できることを検証する
    Given キーワードを用意する "色鉛筆"
    When キーワードで商品を取得する
    Then キーワードで取得した結果を評価する
    """
    - objId: cce6eadc-38a5-41fe-950b-48281df253b5
      name: 色鉛筆(12色)
      price: 900
      category:
        objId: d1b34e43-ba64-497d-860b-0054f3039af5
        name: 文房具
    - objId: bf2a41a6-d662-4999-a580-1fd562f51422
      name: 色鉛筆(24色)
      price: 1800
      category:
        objId: d1b34e43-ba64-497d-860b-0054f3039af5
        name: 文房具
    - objId: e71c1ee3-f2e8-4e9d-94cb-8beca3f0acd2
      name: 色鉛筆(36色)
      price: 3200
      category:
        objId: d1b34e43-ba64-497d-860b-0054f3039af5
        name: 文房具
    """
  Scenario: 存在しない商品のキーワードで商品を検索するとnullであることを検証する
    Given キーワードを用意する "筆ペン"
    When キーワードで商品を取得する
    Then キーワードで取得した結果が空であることを評価する
  #
  # - exists():指定された商品名の存在有無を取得する
  #
  Scenario: 指定された商品名が存在することを検証する
    Given 存在を調べる商品名を用意する "蛍光ペン(黄)"
    When 存在を調べる
    Then 結果が返されることを評価する "true"
  Scenario: 指定された商品名が存在しないことを検証する
    Given 存在を調べる商品名を用意する "蛍光ペン(黒)"
    When 存在を調べる
    Then 結果が返されることを評価する "false"