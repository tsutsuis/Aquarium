package aw.aquariumapplication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.graphics.Paint;

import java.util.ArrayList;

/**
 * Created by misato on 2015/08/18.
 */
public class DrawFishView extends View {
    private Paint paint;
    private Path path;
    public static Bitmap bitmap = null;

    public DrawFishView(Context context, AttributeSet attrs) {
        super(context,attrs);
        this.path = new Path();
        this.paint = new Paint();

        this.paint.setColor(Color.BLACK);
        this.paint.setStyle(Paint.Style.STROKE);
        this.paint.setAntiAlias(true);
        this.paint.setStrokeWidth(10);

        // キャッシュを取得する設定にする
        setDrawingCacheEnabled(true);
    }

    @SuppressLint("DrawAllocation")
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        /* 魚型のキャンバス作成 */

//        // パスの開始地点（横・縦）
//        this.path.moveTo(150, 200);
//        // しっぽの部分（横・縦）
//        this.path.lineTo(250, 300);
//        this.path.lineTo(150, 400);
//        this.path.lineTo(150, 200);
//        // 胴体の部分（左・上・右・下）
//        this.path.addOval(new RectF(250, 200, 550, 400), Path.Direction.CW);
//
//        canvas.clipPath(this.path);
        canvas.drawPath(this.path, this.paint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        switch (event.getAction()) {
            // タッチしたとき
            case MotionEvent.ACTION_DOWN:
                this.path.moveTo(x, y);
                break;
            // タッチしたまま動かしたとき
            case MotionEvent.ACTION_MOVE:
                this.path.lineTo(x, y);
                break;
            // 指を離したとき
            case MotionEvent.ACTION_UP:
                this.path.lineTo(x, y);
                break;
        }

        // キャッシュからbitmapを作成
        bitmap = Bitmap.createBitmap(getDrawingCache());

        // 再描画（onDraw()メソッドが実行される）
        invalidate();
        return true;
    }
}
