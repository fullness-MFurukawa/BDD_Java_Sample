Feature: 値オブジェクト:商品名の単体テスト
#
# このFeatureは、値オブジェクトProductNameの単体テストを目的とする
# - パッケージ: jp.co.fullness.bdd.domains.models.products;
# - クラス名: ProductName
#
  #
  # インスタンス生成(コンストラクタ)のシナリオ
  #
  Scenario Outline: 有効な商品名を保持したインスタンスが生成される
    Given 商品名を用意する "<name>"
    When ProductNameを生成する
    Then ProductNameの値は "<expectedName>" である
    Examples:
      | name      | expectedName |
      | 水性ボールペン 青 | 水性ボールペン 青    |
  Scenario: 空文字を用いてインスタンス生成するとValidateException例外をスローする
    Given 商品名を用意する ""
    When ProductNameを生成する
    Then ValidateExceptionがスローされる "商品名は必須です。"
  Scenario: nullを用いてインスタンス生成するとValidateException例外をスローする
    Given 商品名を用意する "null"
    When ProductNameを生成する
    Then ValidateExceptionがスローされる "商品名は必須です。"
  Scenario: 30文字より大きい文字列でインスタンス生成するとValidateException例外をスローする
    Given 商品名を用意する "40cffd3bf63645c69a875c87ecb6f0000"
    When ProductNameを生成する
    Then ValidateExceptionがスローされる "商品名は30文字以内である必要があります。"
  #
  #	equals()メソッドのシナリオ
  #
  Scenario Outline: ProductNameのequals()メソッドを検証する
    Given 商品名を比較する値を用意する "<value1>" "<value2>"
    When equalsメソッドを実行する
    Then equalsメソッド実行結果を評価する "<result>"
    Examples:
      | value1    | value2    | result |
      | 水性ボールペン 青 | 水性ボールペン 青 | true   |
      | 水性ボールペン 青 | 油性ボールペン 赤 | false  |
  #
  #	hashCode()メソッドのシナリオ
  #
  Scenario Outline: CategoryNameのhashCode()メソッドを検証する
    Given 商品名のハッシュ値を生成する値を用意する "<value1>" "<value2>"
    When hashCodeメソッドを実行する
    Then hashCodeメソッド実行結果を評価する "<result>"
    Examples:
      | value1    | value2    | result |
      | 水性ボールペン 青 | 水性ボールペン 青 | true   |
      | 水性ボールペン 青 | 油性ボールペン 赤 | false  |
