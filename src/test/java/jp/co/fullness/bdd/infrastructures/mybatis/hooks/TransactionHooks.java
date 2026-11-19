package jp.co.fullness.bdd.infrastructures.mybatis.hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import java.text.MessageFormat;

/**
 * トランザクション制御フッククラス
 * @author Fullness,Inc.
 * @since 2025-02-23
 * @version 1.0.1
 */
public class TransactionHooks {
    // ロガー
    private static final Logger logger =
            LoggerFactory.getLogger(TransactionHooks.class);
    // SpringFrameworkのトランザクションマネージャ
    private PlatformTransactionManager transactionManager;

    /**
     * コンストラクタ
     * @param transactionManager トランザクションマネージャ
     */
    public TransactionHooks(PlatformTransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }

    // トランザクションステータス
    private TransactionStatus transactionStatus;
    /**
     * タグ:@transactionが付与されたシナリオの前処理
     */
    @Before("@transaction")
    public void setupTransaction(Scenario scenario){
        // デフォルトトランザクションを開始してトランザクションステータスを取得する
        transactionStatus =
                transactionManager.getTransaction(new DefaultTransactionDefinition());
        logger.info(()->
                MessageFormat.format("シナリオのトランザクション開始:{0}",
                        scenario.getName()));
    }
    /**
     * タグ:@transactionが付与されたシナリオの後処理
     */
    @After("@transaction")
    public void cleanupTransaction(Scenario scenario){
        // トランザクションをロールバックする
        transactionManager.rollback(transactionStatus);
        logger.info(()->
                MessageFormat.format("シナリオのトランザクションをロールバック:{0}",
                        scenario.getName()));
    }
}
