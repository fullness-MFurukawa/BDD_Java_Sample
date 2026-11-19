Feature: エンティティ:商品の単体テスト
#
# このFeatureは、エンティティProductの単体テストを目的とする
# - パッケージ: jp.co.fullness.bdd.domains.models.products;
# - クラス名: Product
#
  #
  # インスタンス生成のシナリオ
  #
  Scenario Outline: 新しい商品の生成を検証する
    Given 新しい商品名、単価を用意する "<name>" "<price>"
    When 新しい商品を生成する
    Then 新しい商品Idを持った商品が生成されていることを評価する "<name>" "<price>"
    Examples:
      | name | price |
      | 消しゴム | 150   |
  Scenario Outline: 商品名をnullで新しい商品の生成を検証する
    Given 新しい商品名、単価を用意する "<name>" "<price>"
    When 新しい商品を生成する
    Then ValidateExceptionがスローされる "商品名は必須です。"
    Examples:
      | name | price |
      | null | 150   |
  Scenario Outline: 商品単価をnullで新しい商品の生成を検証する
    Given 新しい商品名、単価を用意する "<name>" "<price>"
    When 新しい商品を生成する
    Then ValidateExceptionがスローされる "商品単価は必須です。"
    Examples:
      | name | price |
      | 消しゴム | null  |
  Scenario Outline: 既存商品の生成を検証する
    Given 既存の商品Id、商品名、単価を用意する "<id>" "<name>" "<price>"
    When 既存の商品を生成する
    Then 商品Id、商品名、単価は等価であることを評価する "<id>" "<name>" "<price>"
    Examples:
      | id                                   | name | price |
      | 0bd71345-c8b8-423b-9d3f-48d17dcb7874 | 消しゴム | 150   |
  Scenario Outline: 商品Idをnullで既存商品の生成を検証する
    Given 既存の商品Id、商品名、単価を用意する "<id>" "<name>" "<price>"
    When 既存の商品を生成する
    Then ValidateExceptionがスローされる "商品Idは必須です。"
    Examples:
      | id   | name | price |
      | null | 消しゴム | 150   |
  #
  # changeName()、changePrice()メソッドのシナリオ
  #
  Rule: changeName()、changePrice()メソッド共有テストデータの作成する
    Background:
      Given 新しい商品名、単価、カテゴリを用意する
        | name | price | category_id                          | category_name |
        | 消しゴム | 130   | d1b34e43-ba64-497d-860b-0054f3039af5 | 文房具           |
  #
  # changeName()メソッドのシナリオ
  #
    Scenario: 新しい商品を生成し、商品名変更を検証する
      When 商品名を変更する "砂消しゴム"
      Then 商品名が変更されていることを検証する "砂消しゴム"
    Scenario: 新しい商品を生成し、商品名をnullで変更を検証する
      When 商品名を変更する "null"
      Then ValidateExceptionがスローされる "商品名は必須です。"
  #
  # changePrice()メソッドのシナリオ
  #
    Scenario: 新しい商品を生成し、商品単価変更を検証する
      When 商品単価を変更する "140"
      Then 商品単価が変更されていることを検証する "140"
    Scenario: 新しい商品を生成し、商品単価をnullで変更を検証する
      When 商品単価を変更する "null"
      Then ValidateExceptionがスローされる "商品単価は必須です。"
  #
  # equals()メソッドのテストシナリオ
  #
    Scenario Outline: Productのequalsメソッド()を検証する
      Given 比較対象の商品を用意する "<id1>" "<name1>" "<price1>" "<id2>" "<name2>" "<price2>"
      When equalsメソッドを実行する
      Then equalsメソッド実行結果を評価する "<result>"
      Examples:
        | id1                                  | name1 | price1 | id2                                  | name2 | price2 | result |
        | d1b34e43-ba64-497d-860b-0054f3039af5 | 消しゴム  | 120    | d1b34e43-ba64-497d-860b-0054f3039af5 | 消しゴム  | 120    | true   |
        | d1b34e43-ba64-497d-860b-0054f3039af5 | 消しゴム  | 120    | d1b34e43-ba64-497d-860b-0054f3039af6 | 鉛筆    | 100    | false  |
  #
  # hashCode()メソッドのシナリオ
  #
    Scenario Outline: ProductのhashCode()メソッドを検証する
      Given ハッシュ値を生成する商品を用意する "<id1>" "<name1>" "<price1>" "<id2>" "<name2>" "<price2>"
      When hashCodeメソッドを実行する
      Then hashCodeメソッド実行結果を評価する '<result>'
      Examples:
        | id1                                  | name1 | price1 | id2                                  | name2 | price2 | result |
        | d1b34e43-ba64-497d-860b-0054f3039af5 | 消しゴム  | 120    | d1b34e43-ba64-497d-860b-0054f3039af5 | 消しゴム  | 120    | true   |
        | d1b34e43-ba64-497d-860b-0054f3039af5 | 消しゴム  | 120    | d1b34e43-ba64-497d-860b-0054f3039af6 | 鉛筆    | 100    | false  |