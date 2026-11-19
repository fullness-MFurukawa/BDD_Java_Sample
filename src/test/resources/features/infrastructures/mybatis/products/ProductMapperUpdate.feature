Feature: 商品CRUD操作インターフェイスの更新
#
# このFeatureは、永続化層から商品をCRUD操作する
# ProductMapperインターフェイスの更新メソッドの単体テストを目的とする
# - パッケージ: jp.co.fullness.bdd.infrastructures.mybatis.products
# - インターフェイス名: ProductMapper
# テスト対象:
# - create():商品を永続化する
# - updateById():指定された商品Idの商品を変更する
# - deleteById():指定された商品Idの商品を削除する
  #
  # - create():商品を永続化する
  #
  @transaction
  Scenario: 新しい商品を永続化できることを検証する
    Given 新しい商品を用意する
	"""
    objId: e71c1ee3-f2e8-4e9d-94cb-8beca3f0acd3
    name: 消しゴム
    price: 120
    category:
      objId: d1b34e43-ba64-497d-860b-0054f3039af5
	"""
    When 新しい商品を永続化する
    Then 商品が永続化されたことを評価する
  #
  # - updateById():指定された商品Idの商品を変更する
  #
  @transaction
  Scenario: 存在する商品を変更し、true(変更成功)を返すことを検証する
    Given 変更商品を用意する
    """
    objId: 40bf7198-6795-4a3d-bc88-e59e1fa52436
    name: 蛍光ペン(黄)
    price: 200
    """
    When 商品の単価を変更する
    Then 商品の変更結果を評価する "true"
  @transaction
  Scenario: 存在しない商品を変更し、false(変更成功)を返すことを検証する
    Given 変更商品を用意する
    """
    objId: 40bf7198-6795-4a3d-bc88-e59e1fa52437
    name: 蛍光ペン(黄)
    price: 200
    """
    When 商品の単価を変更する
    Then 商品の変更結果を評価する "false"
  #
  # - deleteById():指定された商品Idの商品を削除する
  #
  @transaction
  Scenario: 存在する商品Idの商品を削除し、true(変更成功)を返すことを検証する
    Given 削除する商品Idを用意する "40bf7198-6795-4a3d-bc88-e59e1fa52436"
    When 商品を削除する
    Then 商品の削除結果を評価する "true"
  @transaction
  Scenario: 存在しない商品Idの商品を削除し、true(変更成功)を返すことを検証する
    Given 削除する商品Idを用意する "40bf7198-6795-4a3d-bc88-e59e1fa52437"
    When 商品を削除する
    Then 商品の削除結果を評価する "false"