Feature: ProductエンティティとProductTableModelの相互変換

# このFeatureは、CategoryとCategoryTableModelの相互変換メソッドの単体テストを目的とする
# - パッケージ: jp.co.fullness.bdd.infrastructures.mybatis.products
# - クラス名: ProductAdapterMyBatis
# テスト対象:
# - Convert():ProductをProductTableModelに変換する
# - ConvertList():List<Product>をList<ProductTableModel>に変換する
# - Restore():ProductTableModelからProductを復元する
# - RestoreList():List<ProductTableModel>からList<Product>を復元する

  #
  # - Convert():ProductをProductTableModelに変換する
  #
  Scenario: ProductをProductTableModelへの変換を検証する
    Given Categoryを保持したProductを用意する
    """
    {
      "id": "40bf7198-6795-4a3d-bc88-e59e1fa52436",
      "name": "蛍光ペン(黄)",
      "price": 150,
      "category": {
        "id": "d1b34e43-ba64-497d-860b-0054f3039af5",
        "name": "文房具"
      }
    }
    """
    When ProductTableModelを生成する
    Then ProductTableModelの値を検証する
    """
    {
      "objId": "40bf7198-6795-4a3d-bc88-e59e1fa52436",
      "name": "蛍光ペン(黄)",
      "price": 150,
      "category": {
        "objId": "d1b34e43-ba64-497d-860b-0054f3039af5",
        "name": "文房具"
      }
    }
    """
  #
  # - Restore():ProductTableModelからProductを復元する
  #
  Scenario: ProductTableModelからProductの復元を検証する
    Given ProductTableModelを用意する
    """
    {
      "objId": "40bf7198-6795-4a3d-bc88-e59e1fa52436",
      "name": "蛍光ペン(黄)",
      "price": 150,
      "category": {
        "objId": "d1b34e43-ba64-497d-860b-0054f3039af5",
        "name": "文房具"
      }
    }
    """
    When Productを復元する
    Then Productの値を評価する
    """
    {
      "id": "40bf7198-6795-4a3d-bc88-e59e1fa52436",
      "name": "蛍光ペン(黄)",
      "price": 150,
      "category": {
        "id": "d1b34e43-ba64-497d-860b-0054f3039af5",
        "name": "文房具"
      }
    }
    """
  #
  # - ConvertList():List<Product>をList<ProductTableModel>に変換する
  #
  Scenario: ProductのリストをProductTableModelのリストへの変換を検証する
    Given Productを保持したリストを用意する
    """
    [
	  {
	    "id": "aad387ae-d993-4a5b-9d8c-40d6b748a570",
	    "name": "水性ボールペン(赤)",
	    "price": 150,
	    "category":{
	      "id":"d1b34e43-ba64-497d-860b-0054f3039af5",
		  "name":"文房具"
	    }
	  },
	  {
	    "id": "f2b4222b-d584-4517-a297-53b98c8dbc71",
	    "name": "水性ボールペン(黒)",
	    "price": 150,
	    "category":{
	      "id":"d1b34e43-ba64-497d-860b-0054f3039af5",
	      "name":"文房具"
	    }
	  },
	  {
	    "id": "e7d1bef5-80f0-40e8-b4ff-a9f65c10ed41",
	    "name": "水性ボールペン(青)",
	    "price": 150,
	    "category":{
	      "id":"d1b34e43-ba64-497d-860b-0054f3039af5",
	      "name":"文房具"
	    }
	  }
    ]
    """
    When ProductのリストをProductTableModelのリストに変換する
    Then リスト内のProductTableModelのプロパティを評価する
    """
    [
	  {
	    "objId": "aad387ae-d993-4a5b-9d8c-40d6b748a570",
	    "name": "水性ボールペン(赤)",
	    "price": 150,
	    "category":{
	      "objId":"d1b34e43-ba64-497d-860b-0054f3039af5",
		  "name":"文房具"
	    }
	  },
	  {
	    "objId": "f2b4222b-d584-4517-a297-53b98c8dbc71",
	    "name": "水性ボールペン(黒)",
	    "price": 150,
	    "category":{
	      "objId":"d1b34e43-ba64-497d-860b-0054f3039af5",
	      "name":"文房具"
	    }
	  },
	  {
	    "objId": "e7d1bef5-80f0-40e8-b4ff-a9f65c10ed41",
	    "name": "水性ボールペン(青)",
	    "price": 150,
	    "category":{
	      "objId":"d1b34e43-ba64-497d-860b-0054f3039af5",
	      "name":"文房具"
	    }
	  }
    ]
    """
  #
  # - RestoreList():List<ProductTableModel>からList<Product>を復元する
  #
  Scenario: ProductTableModelのリストからProductのリストの復元を検証する
    Given ProductTableModelを保持したリストを用意する
    """
    [
	  {
	    "objId": "aad387ae-d993-4a5b-9d8c-40d6b748a570",
	    "name": "水性ボールペン(赤)",
	    "price": 150,
	    "category":{
	      "objId":"d1b34e43-ba64-497d-860b-0054f3039af5",
		  "name":"文房具"
	    }
	  },
	  {
	    "objId": "f2b4222b-d584-4517-a297-53b98c8dbc71",
	    "name": "水性ボールペン(黒)",
	    "price": 150,
	    "category":{
	      "objId":"d1b34e43-ba64-497d-860b-0054f3039af5",
	      "name":"文房具"
	    }
	  },
	  {
	    "objId": "e7d1bef5-80f0-40e8-b4ff-a9f65c10ed41",
	    "name": "水性ボールペン(青)",
	    "price": 150,
	    "category":{
	      "objId":"d1b34e43-ba64-497d-860b-0054f3039af5",
	      "name":"文房具"
	    }
	  }
    ]
    """
    When ProductTableModelのリストからProductのリストを復元する
    Then リスト内のドメインモデルProductの値を評価する
    """
    [
	  {
	    "id": "aad387ae-d993-4a5b-9d8c-40d6b748a570",
	    "name": "水性ボールペン(赤)",
	    "price": 150,
	    "category":{
	      "id":"d1b34e43-ba64-497d-860b-0054f3039af5",
		  "name":"文房具"
	    }
	  },
	  {
	    "id": "f2b4222b-d584-4517-a297-53b98c8dbc71",
	    "name": "水性ボールペン(黒)",
	    "price": 150,
	    "category":{
	      "id":"d1b34e43-ba64-497d-860b-0054f3039af5",
	      "name":"文房具"
	    }
	  },
	  {
	    "id": "e7d1bef5-80f0-40e8-b4ff-a9f65c10ed41",
	    "name": "水性ボールペン(青)",
	    "price": 150,
	    "category":{
	      "id":"d1b34e43-ba64-497d-860b-0054f3039af5",
	      "name":"文房具"
	    }
	  }
    ]
    """