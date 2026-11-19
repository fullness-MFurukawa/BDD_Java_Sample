Feature: 商品カテゴリCRUD操作インターフェイス
#
# このFeatureは、永続化層から商品カテゴリをCRUD操作する
# CategoryMapperインターフェイスの単体テストを目的とする
# - パッケージ: jp.co.fullness.bdd.infrastructures.mybatis.categories
# - インターフェイス名: CategoryMapper
# テスト対象:
# - findById(): 指定されたIdのカテゴリを取得する
# - findAll(): すべてのカテゴリを取得する
  #
  # 指定されたIdの商品カテゴリを取得する
  #
  Scenario: 存在する商品カテゴリIdで商品カテゴリを取得できることを検証する
    Given 取得対象の商品カテゴリIdを用意する "d1b34e43-ba64-497d-860b-0054f3039af5"
    When 商品カテゴリIdで商品カテゴリを取得する
    Then 取得した商品カテゴリを評価する
    """
    objId: d1b34e43-ba64-497d-860b-0054f3039af5
    name: 文房具
    """
  Scenario: 存在しない商品カテゴリIdで商品カテゴリを取得するとnullが返されることを検証する
    Given 取得対象の商品カテゴリIdを用意する "d1b34e43-ba64-497d-860b-0054f3039af6"
    When 商品カテゴリIdで商品カテゴリを取得する
    Then 取得した商品カテゴリがnullであることを評価する
  #
  # すべてのカテゴリを取得する
  #
  Scenario: すべての商品カテゴリを取得できることを検証する
    Given 取得する商品カテゴリを用意する
    """
    - objId: "d1b34e43-ba64-497d-860b-0054f3039af5"
      name: "文房具"
    - objId: "485d80c6-d2ff-49dc-90f3-42793e7473ff"
      name: "雑貨"
    - objId: "49aaa280-c3cf-4c73-9158-4143eefd6148"
      name: "PC周辺機器"
    """
    When すべての商品カテゴリを取得する
    Then すべての商品カテゴリが取得されたことを評価する