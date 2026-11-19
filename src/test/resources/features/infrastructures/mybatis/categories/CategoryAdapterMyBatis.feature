Feature: CategoryエンティティとCategoryTableModelの相互変換
# このFeatureは、CategoryとCategoryTableModelの相互変換メソッドの単体テストを目的とする
# - パッケージ: jp.co.fullness.bdd.infrastructures.mybatis.categories
# - クラス名: CategoryAdapterMyBatis
# テスト対象:
# - Convert():CategoryをCategoryTableModelに変換する
# - ConvertList():List<Category>をList<CategoryTableModel>に変換する
# - Restore():CategoryTableModelからCategoryを復元する
# - RestoreList():List<CategoryTableModel>からList<Category>を復元する
  #
  # - Convert():CategoryをCategoryTableModelに変換する
  #
  Scenario: CategoryからCategoryTableModelへの変換を検証する
    Given Categoryエンティティを用意する
    """
    {
	  "id": "d1b34e43-ba64-497d-860b-0054f3039af5",
      "name": "文房具"
    }
    """
    When CategoryエンティティをCategoryTableModelに変換する
    Then CategoryTableModelの値を検証する
    """
    {
	  "objId": "d1b34e43-ba64-497d-860b-0054f3039af5",
      "name": "文房具"
    }
    """
  #
  # - Restore():CategoryTableModelからCategoryを復元する
  #
  Scenario: CategoryTableModelからCategoryへの復元を検証する
    Given CategoryTableModelを用意する
    """
    {
	  "objId": "d1b34e43-ba64-497d-860b-0054f3039af5",
      "name": "文房具"
    }
    """
    When CategoryTableModelからCategoryを復元する
    Then Categoryの値を検証する
    """
    {
	  "id": "d1b34e43-ba64-497d-860b-0054f3039af5",
      "name": "文房具"
    }
    """
  #
  # - ConvertList():List<Product>をList<CategoryTableModel>に変換する
  #
  Scenario: CategoryのリストからCategoryDBModelのリストへの変換を検証する
    Given Categoryのリストを用意する
	"""
	[
		{
			"id": "d1b34e43-ba64-497d-860b-0054f3039af5",
			"name": "文房具"
		},
		{
			"id": "485d80c6-d2ff-49dc-90f3-42793e7473ff",
			"name": "雑貨"
		},
		{
			"id": "49aaa280-c3cf-4c73-9158-4143eefd6148",
			"name": "PC周辺機器"
		}
	]
	"""
    When CategoryのリストをCategoryModelのリストに変換する
    Then リスト内のCategoryTableModelのプロパティを検証する
    """
    [
	  {
	  	  "objId": "d1b34e43-ba64-497d-860b-0054f3039af5",
		  "name": "文房具"
	  },
	  {
		  "objId": "485d80c6-d2ff-49dc-90f3-42793e7473ff",
		  "name": "雑貨"
	  },
	  {
		  "objId": "49aaa280-c3cf-4c73-9158-4143eefd6148",
		  "name": "PC周辺機器"
	  }
    ]
    """
  #
  # - RestoreList:List<CategoryTableModel>からList<Category>を復元する
  #
  Scenario: CategoryTableModelのリストからCategoryリストへの復元を検証する
    Given CategoryTableModelのリストを用意する
    """
    [
	  {
	  	  "objId": "d1b34e43-ba64-497d-860b-0054f3039af5",
		  "name": "文房具"
	  },
	  {
		  "objId": "485d80c6-d2ff-49dc-90f3-42793e7473ff",
		  "name": "雑貨"
	  },
	  {
		  "objId": "49aaa280-c3cf-4c73-9158-4143eefd6148",
		  "name": "PC周辺機器"
	  }
    ]
    """
    When CategoryTableModelのリストからCategoryのリストを復元する
    Then リスト内のCategoryの値を検証する
    """
	[
		{
			"id": "d1b34e43-ba64-497d-860b-0054f3039af5",
			"name": "文房具"
		},
		{
			"id": "485d80c6-d2ff-49dc-90f3-42793e7473ff",
			"name": "雑貨"
		},
		{
			"id": "49aaa280-c3cf-4c73-9158-4143eefd6148",
			"name": "PC周辺機器"
		}
	]
	"""