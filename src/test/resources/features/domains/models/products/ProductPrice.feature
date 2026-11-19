Feature: 値オブジェクト:商品単価の単体テスト
#
# このFeatureは、値オブジェクトProductNameの単体テストを目的とする
# - パッケージ: jp.co.fullness.bdd.domains.models.products;
# - クラス名: ProductPrice
#
  #
  # インスタンス生成(コンストラクタ)のシナリオ
  #
  Scenario Outline: 有効な商品単価を保持したインスタンスが生成される
    Given 商品単価を用意する "<price>"
    When ProductPriceを生成する
    Then ProductPriceの値は "<price>" である
    Examples:
      | price |
      | 50    |
      | 10000 |
  Scenario Outline: 無効な商品単価でインスタンス生成するとValidateException例外をスローする
    Given 商品単価を用意する "<price>"
    When ProductPriceを生成する
    Then ValidateExceptionがスローされる "商品単価の有効範囲は50以上10000以下です。"
    Examples:
      | price |
      | 49    |
      | 10001 |
  #
  #	equals()メソッドのシナリオ
  #
  Scenario Outline: ProductPriceのequalsメソッド()を検証する
    Given 商品単価を比較する値を用意する "<value1>" "<value2>"
    When equalsメソッドを実行する
    Then equalsメソッド実行結果を評価する "<result>"
    Examples:
      | value1 | value2 | result |
      | 150    | 150    | true   |
      | 150    | 160    | false  |
  #
  #	hashCode()メソッドのシナリオ
  #
  Scenario Outline: CategoryPriceのhashCode()メソッドを検証する
    Given 商品単価のハッシュ値を生成する値を用意する "<value1>" "<value2>"
    When hashCodeメソッドを実行する
    Then hashCodeメソッド実行結果を評価する "<result>"
    Examples:
      | value1	| value2	| result |
      | 150		| 150		| true   |
      | 150		| 160		| false  |