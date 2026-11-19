package jp.co.fullness.bdd.presentations;
import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import static io.cucumber.core.options.Constants.GLUE_PROPERTY_NAME;

/**
 * プレゼンテーション層のテストスイート
 */
@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("features\\presentations")
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "jp.co.fullness.bdd")
public class PresentationsTests {
}
