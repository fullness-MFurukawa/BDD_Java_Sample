Feature: 商品カテゴリCRUD操作リポジトリ

# このFeatureは、永続化層から商品カテゴリをCRUD操作するRepositoryインターフェイス実装の単体テストを目的とする
# - パッケージ: jp.co.fullness.bdd.infrastructures.mybatis.categories
# - クラス名: CategoryRepositoryMyBatis
# 依存関係:
# - CategoryMapper
# - CategoryAdapterMyBatis
# テスト対象:
# - findById(): 指定されたIdの商品カテゴリを取得する
# - findAll(): すべての商品カテゴリを取得する

  #
  # - findById(): 指定されたIdの商品カテゴリを取得する
  #
  Scenario: 存在する商品カテゴリIdでカテゴリを取得できることを検証する
    Given 商品カテゴリIdを用意する "485d80c6-d2ff-49dc-90f3-42793e7473ff"
    When 用意した商品カテゴリIdでカテゴリを永続化層から取得する
    Then 取得した商品カテゴリが予測値と等価であることを評価する
      | id                                   | name |
      | 485d80c6-d2ff-49dc-90f3-42793e7473ff | 雑貨   |
  #
  # - findAll(): すべての商品カテゴリを取得する
  #
  Scenario: すべての商品カテゴリが取得できることを検証する
    Given 取得される商品カテゴリを用意する
    """
	[
		{
			"id": "d1b34e43-ba64-497d-860b-0054f3039af5",
			"name": "文房具"
		},
		{
			"id": "485d80c6-d2ff-49dc-90f3-42793e7473ff",
			"name": "雑貨"
		},
		{
			"id": "49aaa280-c3cf-4c73-9158-4143eefd6148",
			"name": "PC周辺機器"
		}
	]
	"""
    When すべての商品カテゴリを永続化層から取得する
    Then 用意された商品カテゴリが取得できたことを評価する