Feature: CRUD操作でスローされる内部例外

# このFeatureは、CRUD操作でスローされるInternalExceptionの単体テストを目的とする
# - パッケージ: jp.co.fullness.bdd.exceptions
# - クラス名: InternalException

  # テスト対象
  # - CategoryRepository
  #   - findById(): 指定されたIdの商品カテゴリを取得する
  #   - findAll(): すべての商品カテゴリを取得する
  Scenario: 指定されたIdの商品カテゴリを取得する:InternalExceptionがスローされることを検証する
    When CategoryRepositoryのfindAllメソッドを実行する
    Then InternalExceptionがスローされたことを評価する
  Scenario: すべての商品カテゴリを取得する:InternalExceptionがスローされることを検証する
    When CategoryRepositoryのfindIdメソッドを実行する
    Then InternalExceptionがスローされたことを評価する

  # テスト対象
  # -ProductRepository
  #   - findById(): 指定されたIdの商品を取得する
  #   - findByNameContains(): 指定された商品キーワードに該当する商品を取得する
  #   - exists():指定された商品名の存在有無を取得する
  #   - create():商品を永続化する
  #   - updateById():指定された商品Idの商品を変更する
  #   - deleteById():指定された商品Idの商品を削除する
  Scenario: 指定されたIdの商品を取得する:InternalExceptionがスローされることを検証する
    When ProductRepositoryのfindByIdメソッドを実行する
    Then InternalExceptionがスローされたことを評価する
  Scenario: 指定された商品キーワードに該当する商品を取得する:InternalExceptionがスローされることを検証する
    When ProductRepositoryのfindByNameContainsメソッドを実行する
    Then InternalExceptionがスローされたことを評価する
  Scenario: 指定された商品名の存在有無を取得する:InternalExceptionがスローされることを検証する
    When ProductRepositoryのexistsメソッドを実行する
    Then InternalExceptionがスローされたことを評価する
  Scenario: 商品を永続化する:InternalExceptionがスローされることを検証する
    When ProductRepositoryのcreateメソッドを実行する
    Then InternalExceptionがスローされたことを評価する
  Scenario: 指定された商品Idの商品を変更する:InternalExceptionがスローされることを検証する
    When ProductRepositoryのupdateByIdメソッドを実行する
    Then InternalExceptionがスローされたことを評価する
  Scenario: 指定された商品Idの商品を削除する:InternalExceptionがスローされることを検証する
    When ProductRepositoryのdeleteByIdメソッドを実行する
    Then InternalExceptionがスローされたことを評価する