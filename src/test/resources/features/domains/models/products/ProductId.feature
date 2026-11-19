Feature: 値オブジェクト:商品Idの単体テスト
#
# このFeatureは、値オブジェクトProductIdの単体テストを目的とする
# - パッケージ: jp.co.fullness.bdd.domains.models.products;
# - クラス名: ProductId
#

#
# インスタンス生成(コンストラクタ)のテストシナリオ
#
  Scenario: 有効なUUIDを保持したインスタンスが生成される
    Given 商品Idを用意する "0bd71345-c8b8-423b-9d3f-48d17dcb7874"
    When 商品Idを生成する
    Then 商品Idの値は "0bd71345-c8b8-423b-9d3f-48d17dcb7874" である
  #
  # 演習-02で追加
  #
  Scenario: 空文字を用いてインスタンス生成するとValidateException例外をスローする
    Given 商品Idを用意する ""
    When 商品Idを生成する
    Then ValidateExceptionがスローされる "商品Idは必須です。"
  Scenario: nullを用いてインスタンス生成するとValidateException例外をスローする
    Given 商品Idを用意する "null"
    When 商品Idを生成する
    Then ValidateExceptionがスローされる "商品Idは必須です。"
  Scenario: 36文字でない文字列でインスタンス生成するとValidateException例外をスローする
    Given 商品Idを用意する "0bd71345-c8b8-423b-9d3f-48d17dcb787"
    When 商品Idを生成する
    Then ValidateExceptionがスローされる "商品Idは36文字である必要があります。"
  Scenario: UUID形式でない文字列でインスタンス生成するとValidateException例外をスローする
    Given 商品Idを用意する "abcd234-efgh-5678-ijkl-9012xzyabcssz"
    When 商品Idを生成する
    Then ValidateExceptionがスローされる "商品IdはUUID形式でなければなりません。"

  #
  #	equals()メソッドのシナリオ
  #
  Scenario Outline: 商品Idのequals()メソッドを検証する
    Given 商品Idを比較する値を用意する "<value1>" "<value2>"
    When equalsメソッドを実行する
    Then equalsメソッド実行結果を評価する "<result>"
    Examples:
      | value1                               | value2                               | result |
      | 0bd71345-c8b8-423b-9d3f-48d17dcb7874 | 0bd71345-c8b8-423b-9d3f-48d17dcb7874 | true   |
      | 0bd71345-c8b8-423b-9d3f-48d17dcb7874 | 0bd71345-c8b8-423b-9d3f-48d17dcb7875 | false  |
  #
  #	hashCode()メソッドのシナリオ
  #
  Scenario Outline: 商品IdのhashCode()メソッドを検証する
    Given 商品Idのハッシュ値を生成する値を用意する "<value1>" "<value2>"
    When hashCodeメソッドを実行する
    Then hashCodeメソッド実行結果を評価する "<result>"
    Examples:
      | value1                               | value2                               | result |
      | 0bd71345-c8b8-423b-9d3f-48d17dcb7874 | 0bd71345-c8b8-423b-9d3f-48d17dcb7874 | true   |
      | 0bd71345-c8b8-423b-9d3f-48d17dcb7874 | 0bd71345-c8b8-423b-9d3f-48d17dcb7875 | false  |
