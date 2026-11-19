Feature: 値オブジェクト:商品カテゴリ名の単体テスト
#
# このFeatureは、商品カテゴリ名を表す値オブジェクトの単体テストを目的とする
# - パッケージ: jp.co.fullness.bdd.domains.models.categories;
# - クラス名: CategoryName
#
  #
  # インスタンス生成(コンストラクタ)のテストシナリオ
  #
  Scenario: 30文字以内のカテゴリ名を保持したインスタンスが生成される
    Given 商品カテゴリ名を用意する "文房具"
    When 商品カテゴリ名を生成する
    Then 商品カテゴリ名の値は "文房具" である
  Scenario: 空文字を用いてインスタンス生成するとValidateException例外をスローする
    Given 商品カテゴリ名を用意する ""
    When 商品カテゴリ名を生成する
    Then ValidateExceptionがスローされる "商品カテゴリ名は必須です。"
  Scenario: nullを用いてインスタンス生成するとValidateException例外をスローする
    Given 商品カテゴリ名を用意する "null"
    When 商品カテゴリ名を生成する
    Then ValidateExceptionがスローされる "商品カテゴリ名は必須です。"
  Scenario: 30文字より大きい文字列でインスタンス生成するとValidateException例外をスローする
    Given 商品カテゴリ名を用意する "d1b34e43-ba64-497d-860b-0054f3039af5"
    When 商品カテゴリ名を生成する
    Then ValidateExceptionがスローされる "商品カテゴリ名は30文字以内である必要があります。"
  #
  # equals()メソッドのテストシナリオ
  #
  Scenario Outline: 商品カテゴリ名のequals()メソッドを検証する
    Given 商品カテゴリ名を比較する値を用意する "<name1>" "<name2>"
    When equalsメソッドを実行する
    Then equalsメソッド実行結果を評価する "<result>"
    Examples:
      | name1 | name2 | result |
      | 文房具   | 文房具   | true   |
      | 文房具   | 雑貨    |  false  |

  #
  # hashCode()メソッドのテストシナリオ
  #
  Scenario Outline: 商品カテゴリ名のhashCode()メソッドを検証する
    Given 商品カテゴリ名のハッシュ値を生成する値を用意する "<name1>" "<name2>"
    When hashCodeメソッドを実行する
    Then hashCodeメソッド実行結果を評価する "<result>"
    Examples:
      |name1|name2|result|
      | 文房具 | 文房具 | true |
      | 文房具    | 雑化 | false |