package aw.aquariumapplication;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import static aw.aquariumapplication.DrawFishView.bitmap;

import java.util.Timer;
import java.util.TimerTask;
import android.os.Handler;

public class MainActivity extends AppCompatActivity {

    // お絵かき画面
    private DrawFishView drawFishView = null;
    // 水槽画面
    private SwimFishView swimFishView = null;

    // ハンドラ
    private Handler handler = null;
    private final static long MSES = 10;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

        // スタート画面を表示
        setContentView(R.layout.activity_start);

    }

    // スタート画面：スタートボタン押下
    public void startButtonClick(View view) {
        // お絵かき画面を表示
        setContentView(R.layout.activity_drawfish);
        this.drawFishView = (DrawFishView)findViewById(R.id.fishView);
		findViewById(R.id.redButton).setOnClickListener(setRedPen);
        findViewById(R.id.blueButton).setOnClickListener(setBluePen);
        findViewById(R.id.yellowButton).setOnClickListener(setYellowPen);
        findViewById(R.id.blackButton).setOnClickListener(setBlackPen);
        findViewById(R.id.resetButton).setOnClickListener(resetButtonClick);
    }

    // お絵かき画面：OKボタン押下
    public void okButtonClick(View view) {

        setContentView(R.layout.activity_swimfish);
        this.swimFishView = (SwimFishView)findViewById(R.id.swimfishview);
        this.swimFishView.setBitMap(DrawFishView.bitmap);

        // タイマーセット
        Timer timer = new Timer(false);
        this.handler = new Handler();

        // MSESミリ秒おきにタスクを実行
        timer.schedule(new TimerTask() {
            public void run() {
                handler.post(new Runnable() {
                    public void run() {
                        // ビューを再描画
                        swimFishView.invalidate();
                    }
                });
            }
        }, 0, MSES);
    }

    // 水槽画面：もどるボタン押下
    public void backStartButtonClick(View view) {

        setContentView(R.layout.activity_start);
    }

    View.OnClickListener setRedPen = new View.OnClickListener() {
        public void onClick(View view) {
            drawFishView.setPen(Color.RED);
        }
    };

    View.OnClickListener setBluePen = new View.OnClickListener() {
        public void onClick(View view) {
            drawFishView.setPen(Color.BLUE);
        }
    };

    View.OnClickListener setYellowPen = new View.OnClickListener() {
        public void onClick(View view) {
            drawFishView.setPen(Color.YELLOW);
        }
    };

    View.OnClickListener setBlackPen = new View.OnClickListener() {
        public void onClick(View view) {
            drawFishView.setPen(Color.BLACK);
        }
    };

    // リセットボタン押下
    View.OnClickListener resetButtonClick = new View.OnClickListener() {
        public void onClick(View view) {
            drawFishView.reset();
        }
    };
}
