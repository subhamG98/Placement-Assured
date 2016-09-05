package subhamguptaminor.androidbelieve.drawerwithswipetabs;

import android.app.Service;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

public class ChatHeadService extends Service {

    private WindowManager windowManager;
    private ImageView chatHead;
    WindowManager.LayoutParams params;
    Integer i=0;
    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("ddg", "reached oncreate");
        windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);

//Toast.makeText(getBaseContext(),"1 pass",Toast.LENGTH_LONG).show();
        chatHead = new ImageView(this);
        chatHead.setImageResource(R.drawable.msg);
       // Toast.makeText(this,"2 pass",Toast.LENGTH_LONG).show();
        params= new WindowManager.LayoutParams(
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.TYPE_PHONE,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                PixelFormat.TRANSLUCENT);

        params.gravity = Gravity.TOP | Gravity.LEFT;
        params.x = 0;
        params.y = 140;
     //   Toast.makeText(this,"3 pass",Toast.LENGTH_LONG).show();
        //this code is for dragging the chat head
        chatHead.setOnTouchListener(new View.OnTouchListener() {
            private int initialX;
            private int initialY;
            private float initialTouchX;
            private float initialTouchY;

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        initialX = params.x;
                        initialY = params.y;
                        initialTouchX = event.getRawX();
                        initialTouchY = event.getRawY();
                        return true;
                    case MotionEvent.ACTION_UP:
                       // Toast.makeText(getBaseContext(),"on click",Toast.LENGTH_SHORT).show();

                       if(i==0) {
                           i=1;
                           Intent chatscreen = new Intent(getBaseContext(), chatscreen.class);
                           chatscreen.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                           startActivity(chatscreen);

                       }
                        else
                       {
                           i=0;


                           }
                        return true;



                    case MotionEvent.ACTION_MOVE:
                        params.x = initialX
                                + (int) (event.getRawX() - initialTouchX);
                        params.y = initialY
                                + (int) (event.getRawY() - initialTouchY);
                        windowManager.updateViewLayout(chatHead, params);
                        if (params.y > 1000) {
                            stopService(new Intent(getApplication(), ChatHeadService.class));

                        }

                        return true;

                }
                return false;
            }
        });
        windowManager.addView(chatHead, params);
    }




    @Override
    public void onDestroy() {
        super.onDestroy();
        if (chatHead != null)
            windowManager.removeView(chatHead);


    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO Auto-generated method stub
        return null;
    }
}