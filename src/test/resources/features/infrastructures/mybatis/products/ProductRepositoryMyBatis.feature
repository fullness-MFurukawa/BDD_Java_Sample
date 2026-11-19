Feature: 商品CRUD操作リポジトリ

# このFeatureは、永続化層から商品をCRUD操作するRepositoryインターフェイス実装の単体テストを目的とする
# - パッケージ: jp.co.fullness.bdd.infrastructures.mybatis.products
# - クラス名: ProductRepositoryMyBatis
# テスト対象:
# - findById(): 指定されたIdの商品を取得する
# - findByNameContains(): 指定された商品キーワードに該当する商品を取得する
# - exists():指定された商品名の存在有無を取得する
# - create():商品を永続化する
# - updateById():指定された商品Idの商品を変更する
# - deleteById():指定された商品Idの商品を削除する

  #
  # - findById(): 指定された商品Idの商品を取得する
  #
  Scenario: 存在する商品Idで商品を取得できることを検証する
    Given 取得する商品Idを用意する "50ec6f65-1203-4b71-9d21-630462740d54"
    When 商品Idで永続化層から商品を取得する
    Then 取得した商品を評価する
    """
      id: 50ec6f65-1203-4b71-9d21-630462740d54
      name: 油性ボールペン(青)
      price: 130
    """
  Scenario: 存在しない商品Idではnullを返すことを検証する
    Given 取得する商品Idを用意する "50ec6f65-1203-4b71-9d21-630462740d55"
    When 商品Idで永続化層から商品を取得する
    Then 取得した商品がnullであることを評価する
  #
  # - findByNameContains(): 指定された商品キーワードに該当する商品を取得する
  #
  Scenario: 存在するキーワードで商品を取得できることを検証する
    Given 検索キーワードを用意する "色鉛筆"
    When キーワードで永続化層から商品を取得する
    Then キーワードで取得した商品を評価する
    """
    - id: cce6eadc-38a5-41fe-950b-48281df253b5
      name: 色鉛筆(12色)
      price: 900
      category:
        id: d1b34e43-ba64-497d-860b-0054f3039af5
        name: 文房具
    - id: bf2a41a6-d662-4999-a580-1fd562f51422
      name: 色鉛筆(24色)
      price: 1800
      category:
        id: d1b34e43-ba64-497d-860b-0054f3039af5
        name: 文房具
    - id: e71c1ee3-f2e8-4e9d-94cb-8beca3f0acd2
      name: 色鉛筆(36色)
      price: 3200
      category:
        id: d1b34e43-ba64-497d-860b-0054f3039af5
        name: 文房具
    """
  Scenario: 存在しないキーワードではnullを返すことを検証する
    Given 検索キーワードを用意する "筆ペン"
    When キーワードで永続化層から商品を取得する
    Then キーワードで取得した結果がnullであることを評価する
  #
  # - exists():指定された商品名の存在有無を取得する
  #
  Scenario: 指定された商品名が存在することを検証する
    Given 存在確認する商品名を用意する "蛍光ペン(黄)"
    When 商品名で永続化層の商品有無を調べる
    Then 存在を評価する "true"
  Scenario: 指定された商品名が存在しないことを検証する
    Given 存在確認する商品名を用意する "蛍光ペン(黒)"
    When 商品名で永続化層の商品有無を調べる
    Then 存在を評価する "false"
  #
  # - create():Productエンティティを永続化する
  #
  @transaction
  Scenario: 新しい商品を永続化できることを検証する
    Given 新しい永続化商品を用意する
	"""
    id: e71c1ee3-f2e8-4e9d-94cb-8beca3f0acd3
    name: 消しゴム
    price: 120
    category:
      id: d1b34e43-ba64-497d-860b-0054f3039af5
      name: 文房具
	"""
    When 新しい商品を永続化層に追加する
    Then 新しい商品が永続化されたことを評価する
  #
  # - updateById():指定された商品IdのProductエンティティを変更する
  #
  @transaction
  Scenario: 存在する商品を変更し、true(変更成功)を返すことを検証する
    Given 変更する商品を用意する
    """
    id: 40bf7198-6795-4a3d-bc88-e59e1fa52436
    name: 蛍光ペン(黄)
    price: 200
    """
    When 永続化層の商品を変更する
    Then 永続化層の商品変更結果を評価する "true"
  @transaction
  Scenario: 存在しない商品を変更し、false(変更失敗)を返すことを検証する
    Given 変更する商品を用意する
    """
    id: 40bf7198-6795-4a3d-bc88-e59e1fa52437
    name: 蛍光ペン(黄)
    price: 200
    """
    When 永続化層の商品を変更する
    Then 永続化層の商品変更結果を評価する "false"
  #
  # - deleteById():指定された商品IdのProductエンティティを削除する
  #
  @transaction
  Scenario: 存在する商品Idの商品を削除し、true(削除成功)を返すことを検証する
    Given 削除対象の商品Idを用意する "40bf7198-6795-4a3d-bc88-e59e1fa52436"
    When 永続化層から商品を削除する
    Then 永続化層の商品削除結果を評価する "true"
  @transaction
  Scenario: 存在しない商品Idの商品を削除し、false(削除失敗)を返すことを検証する
    Given 削除対象の商品Idを用意する "40bf7198-6795-4a3d-bc88-e59e1fa52437"
    When 永続化層から商品を削除する
    Then 永続化層の商品削除結果を評価する "false"