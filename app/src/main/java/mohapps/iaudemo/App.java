package mohapps.iaudemo;

import android.app.Application;

import com.haohaohu.cachemanage.CacheUtil;
import com.haohaohu.cachemanage.CacheUtilConfig;
import com.haohaohu.cachemanage.strategy.KeyStoreEncryptStrategy;



public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        CacheUtil.init(CacheUtilConfig.builder(this)
                .setIEncryptStrategy(new KeyStoreEncryptStrategy(this, "cacheUtil"))
                .allowMemoryCache(true)
                .allowEncrypt(false)
                .build());
    }

}