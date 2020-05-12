package mohapps.myproject.activity;

import android.os.Bundle;

import com.haohaohu.cachemanage.CacheUtil;
import com.haohaohu.cachemanage.CacheUtilConfig;
import com.haohaohu.cachemanage.strategy.KeyStoreEncryptStrategy;

import androidx.appcompat.app.AppCompatActivity;

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CacheUtil.init(CacheUtilConfig.builder(this)
                .setIEncryptStrategy(new KeyStoreEncryptStrategy(this, "cacheUtil"))
                .allowMemoryCache(true)
                .allowEncrypt(false)
                .build());
    }
    @Override
    protected void onResume(){
        super.onResume();
    }


}
