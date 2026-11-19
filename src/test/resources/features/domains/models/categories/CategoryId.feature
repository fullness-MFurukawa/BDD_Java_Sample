Feature: 値オブジェクト:商品カテゴリIdの単体テスト
#
# このFeatureは、商品カテゴリIdを表す値オブジェクトの
# 単体テストを目的とする
#

  #
  # インスタンス生成(コンストラクタ)のテストシナリオ
  #
  Scenario: 有効なUUIDを保持したインスタンスが生成される
    Given 商品カテゴリId "d1b34e43-ba64-497d-860b-0054f3039af5" を用意する
    When 商品カテゴリIdを生成する
    Then 商品カテゴリIdの値は "d1b34e43-ba64-497d-860b-0054f3039af5" である

  Scenario: 空文字を用いてインスタンス生成するとValidateException例外をスローする
    Given 商品カテゴリId "" を用意する
    When 商品カテゴリIdを生成する
    Then ValidateExceptionがスローされる "商品カテゴリIdは必須です。"

  Scenario: nullを用いてインスタンス生成するとValidateException例外をスローする
    Given 商品カテゴリId "null" を用意する
    When 商品カテゴリIdを生成する
    Then ValidateExceptionがスローされる "商品カテゴリIdは必須です。"

  Scenario: 32文字でない文字列でインスタンス生成するとValidateException例外をスローする
    Given 商品カテゴリId "d1b34e43-ba64-497d-860b-0054f3039af" を用意する
    When 商品カテゴリIdを生成する
    Then ValidateExceptionがスローされる "商品カテゴリIdは36文字である必要があります。"

  #
  # equals()メソッド共通ステップのテストシナリオ
  #
  Scenario Outline: 商品カテゴリIdのequals()メソッドを検証する
    Given 商品カテゴリIdを比較する値を用意する "<value1>" "<value2>"
    When equalsメソッドを実行する
    Then equalsメソッド実行結果を評価する "<result>"
    Examples:
      | value1                               | value2                               | result |
      | d1b34e43-ba64-497d-860b-0054f3039af5 | d1b34e43-ba64-497d-860b-0054f3039af5 | true   |
      | d1b34e43-ba64-497d-860b-0054f3039af5 | d1b34e43-ba64-497d-860b-0054f3039af4 | false  |

  #
  #	hashCode()メソッド共通ステップのシナリオ
  #
  Scenario Outline: 商品カテゴリIdのhashCode()メソッドを検証する
    Given 商品カテゴリIdのハッシュ値を生成する値を用意する "<value1>" "<value2>"
    When hashCodeメソッドを実行する
    Then hashCodeメソッド実行結果を評価する "<result>"
    Examples:
      | value1                               | value2	                            | result |
      | d1b34e43-ba64-497d-860b-0054f3039af5 | d1b34e43-ba64-497d-860b-0054f3039af5 | true   |
      | d1b34e43-ba64-497d-860b-0054f3039af5 | d1b34e43-ba64-497d-860b-0054f3039af4 | false  |