package com.example.carlos.acercade;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.File;
import java.io.FileOutputStream;

public class MainActivity extends AppCompatActivity {
    static int CODE_FOR_RESULT=981;
    public TextView git,uca,gmail,wa,name,esp;
    public ImageButton share;
    public ImageView idForSaveView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        git = findViewById(R.id.git);
        wa = findViewById(R.id.wa);
        uca = findViewById(R.id.uca);
        gmail = findViewById(R.id.gmail);
        share = findViewById(R.id.Share);
        name = findViewById(R.id.name);
        esp = findViewById(R.id.esp);
        idForSaveView = findViewById(R.id.saveView);

    }

    public void OnclickShare(View view){
        Bitmap bitmap = getBitmapFromView(idForSaveView);
        try{
            File file = new File(this.getExternalCacheDir(),"avatar.png");
            FileOutputStream f0ut = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100,f0ut);
            f0ut.flush();
            f0ut.close();
            file.setReadable(true,false);
            final Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(file))
                    .putExtra(Intent.EXTRA_TEXT,name.getText().toString()+"\n"+esp.getText().toString()+"\n GitHub: "+git.getText().toString()+"\n WhatsApp: "+wa.getText().toString()+"\n Institucional: "+uca.getText().toString()+"\n Gmail: "+gmail.getText().toString());
            intent.setType("image/png");
            startActivityForResult(Intent.createChooser(intent, "Share image via"),CODE_FOR_RESULT);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private Bitmap getBitmapFromView(View view){
        Bitmap returnedBitmap = Bitmap.createBitmap(view.getWidth(),view.getHeight(),Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(returnedBitmap);
        Drawable bgDrawnable = view.getBackground();
        if(bgDrawnable != null){
            bgDrawnable.draw(canvas);
        }else{
            canvas.drawColor(Color.WHITE);
        }
        view.draw(canvas);
        return returnedBitmap;
    }
}
