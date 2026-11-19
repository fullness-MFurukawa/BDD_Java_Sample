package jp.co.fullness.bdd.exceptions;

import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

import static io.cucumber.core.options.Constants.GLUE_PROPERTY_NAME;

// JUnit Platform Suite APIを使用してテストスイートを定義する
// 複数のテストクラスを1つのスイートとして実行できる
@Suite
// JUnit Platformにどのテストエンジンを使用するかを指定する
// Cucumberエンジンを使用することを指定
@IncludeEngines("cucumber")
// テストリソース(Cucumberのfeatureファイル)の場所を指定
// features\\domains\\modelsディレクトリにあるfeatureファイルを選択
@SelectClasspathResource("features\\exceptions")
// 特定のテストエンジンに対する構成パラメータを設定
// CucumberのGLUEプロパティを設定し、ステップ定義クラスが存在するパッケージを指定
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "jp.co.fullness.bdd")
public class ExceptionsTests {
}
