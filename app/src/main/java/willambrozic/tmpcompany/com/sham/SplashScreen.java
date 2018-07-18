package willambrozic.tmpcompany.com.sham;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.felipecsl.gifimageview.library.GifImageView;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;

//this class is the splash screen for the app that is not present in the app presented in class, it was removed because I could not figure out how to add it with the intro at startup
public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        new Handler().postDelayed(new Runnable() {
            public void run() {
                SplashScreen.this.startActivity(new Intent(SplashScreen.this,sham.class));
                SplashScreen.this.finish();
            }
        }, 2000);
    }
}
