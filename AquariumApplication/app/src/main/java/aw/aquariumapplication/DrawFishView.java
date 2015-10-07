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

    public static Bitmap bitmap;
    private Canvas canvas;
    private Paint paint;
    private Path path;
    private int width;
    private int height;

    public DrawFishView(Context context, AttributeSet attrs) {
        super(context,attrs);

        this.path = new Path();
        this.paint = new Paint();

        /* ペンの初期設定 */
        //線で描く
        this.paint.setStyle(Paint.Style.STROKE);
        //線の太さを指定
        this.paint.setStrokeWidth(10);
        //色を設定
        this.paint.setColor(Color.BLACK);
        //端点を丸く
        this.paint.setStrokeCap(Paint.Cap.ROUND);
        //つなぎ目を丸く
        this.paint.setStrokeJoin(Paint.Join.ROUND);
        //線を滑らかに書く
        this.paint.setAntiAlias(true);
    }

    //ペンの色をセット
    public void setPen(int color) {
        this.paint.setColor(color);
    }

    // お絵かきリセット
    public void reset() {
        this.path.reset();
        this.bitmap = null;
        onSizeChanged(this.width, this.height, 0, 0);
        invalidate();
    }

    @SuppressLint("DrawAllocation")
    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawBitmap(this.bitmap, 0, 0, null);
        canvas.drawPath(this.path, this.paint);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        this.width = w;
        this.height = h;
        super.onSizeChanged(w, h, oldw, oldh);
        this.bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        this.canvas = new Canvas(this.bitmap);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        switch (event.getAction()) {
            // タッチしたとき
            case MotionEvent.ACTION_DOWN:
                this.path.reset();
                this.path.moveTo(x, y);
                invalidate();
                break;
            // タッチしたまま動かしたとき
            case MotionEvent.ACTION_MOVE:
                this.path.lineTo(x, y);
                invalidate();
                break;
            // 指を離したとき
            case MotionEvent.ACTION_UP:
                this.path.lineTo(x, y);
                // オフスクリーン・キャンバス（を通して、それに紐付くビットマップ）にパスを描画
                this.canvas.drawPath(this.path, this.paint);
                /* リセットすることで、invalidate を呼び出し後の onDraw では drawPath では何も描画されず、
                 全てオフスクリーンキャンバスのビットマップから描画される */
                this.path.reset();
                invalidate();
                break;
        }

        return true;
    }
}
