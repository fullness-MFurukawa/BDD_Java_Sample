Feature: エンティティ:商品カテゴリの単体テスト
#
# このFeatureは、エンティティCategoryの単体テストを目的とする
# - パッケージ: jp.co.fullness.bdd.domains.models.categories;
# - クラス名: Category
#
  #
  # インスタンス生成のシナリオ
  #
  Scenario: 新しい商品カテゴリの生成を検証する
    Given 新しい商品カテゴリを用意する "食品"
    When 新しい商品カテゴリを生成する
    Then 新しい商品カテゴリIdを持った商品カテゴリが生成されていることを評価する "食品"
  Scenario: 商品カテゴリ名をnullで新しい商品カテゴリの生成を検証する
    Given 新しい商品カテゴリを用意する "null"
    When 新しい商品カテゴリを生成する
    Then ValidateExceptionがスローされる "商品カテゴリ名は必須です。"
  Scenario: 既存商品カテゴリの生成を検証する
    Given 既存商品カテゴリを用意する
      | id                                   | name   |
      | d1b34e43-ba64-497d-860b-0054f3039af5 | 文房具  |
    When 既存の商品カテゴリを生成する
    Then 商品カテゴリIdと商品カテゴリ名は等価であることを評価する
      | id                                   | name   |
      | d1b34e43-ba64-497d-860b-0054f3039af5 | 文房具  |
  Scenario: 商品カテゴリIdをnullで既存商品カテゴリの生成を検証する
    Given 既存商品カテゴリを用意する
      | id	    | name	|
      | null	| 文房具	|
    When 既存の商品カテゴリを生成する
    Then ValidateExceptionがスローされる "商品カテゴリIdは必須です。"
  Scenario: 商品カテゴリ名をnullで既存商品カテゴリの生成を検証する
    Given 既存商品カテゴリを用意する
      | id								      | name	|
      | d1b34e43-ba64-497d-860b-0054f3039af5  | null	|
    When 既存の商品カテゴリを生成する
    Then ValidateExceptionがスローされる "商品カテゴリ名は必須です。"
  #
  # ChangeName()メソッドのシナリオ
  #
  Scenario: 新しい商品カテゴリの生成し、商品カテゴリ名変更を検証する
    Given 新しい商品カテゴリを用意する "食品"
    When 商品カテゴリ名を変更する "台所用品"
    Then 商品カテゴリ名が変更されていることを検証する "台所用品"
  Scenario: 新しい商品カテゴリの生成し、商品カテゴリ名をnullで変更する
    Given 新しい商品カテゴリを用意する "食品"
    When 商品カテゴリ名を変更する "null"
    Then ValidateExceptionがスローされる "商品カテゴリ名は必須です。"
  Scenario: 既存商品カテゴリの生成し、商品カテゴリ名変更を検証する
    Given 既存商品カテゴリを用意する
      | id                                   | name   |
      | d1b34e43-ba64-497d-860b-0054f3039af5 | 文房具  |
    When 商品カテゴリ名を変更する "台所用品"
    Then 商品カテゴリ名が変更されていることを検証する "台所用品"
  Scenario: 既存商品カテゴリの生成し、商品カテゴリ名をnullに変更する
    Given 既存商品カテゴリを用意する
      | id                                   | name   |
      | d1b34e43-ba64-497d-860b-0054f3039af5 | 文房具  |
    When 商品カテゴリ名を変更する "null"
    Then ValidateExceptionがスローされる "商品カテゴリ名は必須です。"
  #
  # equals()メソッドのシナリオ
  #
    Scenario Outline: 商品カテゴリのequals()メソッドを検証する
    Given 比較対象の商品カテゴリを用意する "<id1>" "<name1>" "<id2>" "<name2>"
    When equalsメソッドを実行する
    Then equalsメソッド実行結果を評価する "<result>"
    Examples:
      | id1                                  | name1 | id2                                  | name2 | result |
      | d1b34e43-ba64-497d-860b-0054f3039af5 | 文房具   | d1b34e43-ba64-497d-860b-0054f3039af5 | 文房具   | true   |
      | d1b34e43-ba64-497d-860b-0054f3039af6 | 文房具   | d1b34e43-ba64-497d-860b-0054f3039af5 | 文房具   | false  |
  #
  # hashCode()メソッドのシナリオ
  #
  Scenario Outline: 商品カテゴリののhashCode()メソッドを検証する
    Given 比較対象の商品カテゴリを用意する "<id1>" "<name1>" "<id2>" "<name2>"
    When hashCodeメソッドを実行する
    Then hashCodeメソッド実行結果を評価する "<result>"
    Examples:
      | id1                                  | name1 | id2                                  | name2 | result |
      | d1b34e43-ba64-497d-860b-0054f3039af5 | 文房具   | d1b34e43-ba64-497d-860b-0054f3039af5 | 文房具   | true   |
      | d1b34e43-ba64-497d-860b-0054f3039af6 | 文房具   | d1b34e43-ba64-497d-860b-0054f3039af5 | 文房具   | false  |
  #
  # clone()メソッドのシナリオ
  #
  Scenario: 新しい商品カテゴリの複製が生成できることを検証する
    Given 新しい商品カテゴリを用意する "食品"
    When 新しい商品カテゴリの複製を生成する
    Then 複製されたことを検証する
  Scenario: 既存商品カテゴリの複製が生成できることを検証する
    Given 既存商品カテゴリを用意する
      | id                                   | name   |
      | d1b34e43-ba64-497d-860b-0054f3039af5 | 文房具  |
    When 既存商品カテゴリの複製を生成する
    Then 複製されたことを検証する