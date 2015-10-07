package aw.aquariumapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.view.View;

/**
 * Created by misato on 2015/09/24.
 */
public class SwimFishView extends View {

    private Bitmap bitmap = null;

    public SwimFishView(Context context) {
        super(context);
    }

    public void setBitMap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // 書いた魚を縮小して表示
        canvas.drawBitmap(Bitmap.createScaledBitmap(this.bitmap, 250, 100, false), 0, 0, null);
    }
}
