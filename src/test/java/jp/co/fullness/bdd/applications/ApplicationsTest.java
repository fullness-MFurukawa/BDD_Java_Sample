package jp.co.fullness.bdd.applications;

import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

import static io.cucumber.core.options.Constants.GLUE_PROPERTY_NAME;

/**
 * アプリケーション層のテストスイート
 */
@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("features\\applications\\impls")
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "jp.co.fullness.bdd")
public class ApplicationsTest {
}
