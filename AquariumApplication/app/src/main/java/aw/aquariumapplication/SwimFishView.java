package aw.aquariumapplication;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;

/**
 * Created by misato on 2015/09/24.
 */
public class SwimFishView extends View {

    // 魚の画像
    private Bitmap fish = null;
    private Bitmap initFish = null;

    // 魚の座標
    private int fishX = 0;
    private int fishY = 0;

    // 魚の移動量
    private int fishVX = 0;
    private int fishVY = 0;

    // 左右反転用
    private Matrix matrix = null;

    /**
     * コンストラクタ
     *
     * @param context
     */
    public SwimFishView(Context context, AttributeSet attrs) {
        super(context,attrs);

        Resources res = this.getContext().getResources();
    }

    // 書いた魚セッター
    public void setBitMap(Bitmap fish) {
        // 画面サイズによって魚を縮小
        this.fish = fish;
    }

    @Override
    protected void onDraw(Canvas canvas) {

        super.onDraw(canvas);

        // 初回のみ実行
        if (this.initFish == null) {
            initialize(canvas);
        }

        // 魚を泳がせる
        playSwim(canvas);
    }

    /**
     * 初期化処理
     *
     * @param canvas
     */
    private void initialize(Canvas canvas) {

        // 画面サイズによって魚の移動量を算出
        this.fishVX = canvas.getWidth() / 100;
        this.fishVY = canvas.getHeight() / 200;

        // 画面サイズによって魚を縮小
        int fishSizeX = canvas.getWidth() / 3;
        int fishSizeY = canvas.getHeight() / 4;
        this.fish = Bitmap.createScaledBitmap(fish, fishSizeX, fishSizeY, false);

        // 初期状態の魚を退避（左右反転時に使用）
        this.initFish = this.fish;
    }

    /**
     * 魚を泳がせる
     *
     * @param canvas
     */
    private void playSwim(Canvas canvas) {

        // 魚を移動させる
        this.fishX += this.fishVX;
        this.fishY += this.fishVY;

        // 魚が画面から消えたら
        if (0 > this.fishX || canvas.getWidth() < this.fishX + this.fish.getWidth()) {

            // 左右折り返す
            this.fishVX = this.fishVX * -1;

            Resources res = this.getContext().getResources();
            this.fish = initFish;
            this.matrix = new Matrix();
            if (0 > this.fishVX) {
                // 左右反転させる
                this.matrix.preScale(-1, 1);
            }
            this.fish = Bitmap.createBitmap(this.fish, 0, 0, this.fish.getWidth(), this.fish.getHeight(), this.matrix, false);
        }
        if (0 > this.fishY || canvas.getHeight() < this.fishY + this.fish.getHeight()) {
            // 上下折り返す
            this.fishVY = this.fishVY * -1;
        }

        // 魚を表示
        canvas.drawBitmap(this.fish, this.fishX, this.fishY, null);
    }
}
