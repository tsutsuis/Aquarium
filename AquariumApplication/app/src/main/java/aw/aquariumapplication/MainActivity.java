package aw.aquariumapplication;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private DrawFishView drawFishView = null;
    private SwimFishView swimFishView = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

        this.drawFishView = (DrawFishView)findViewById(R.id.fishView);
		findViewById(R.id.redButton).setOnClickListener(setRedPen);
        findViewById(R.id.blueButton).setOnClickListener(setBluePen);
        findViewById(R.id.yellowButton).setOnClickListener(setYellowPen);
        findViewById(R.id.blackButton).setOnClickListener(setBlackPen);
        findViewById(R.id.resetButton).setOnClickListener(resetButtonClick);
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

    View.OnClickListener resetButtonClick = new View.OnClickListener() {
        public void onClick(View view) {
            drawFishView.reset();
        }
    };

	// OKボタン押下
	public void okButtonClick(View view) {
		this.swimFishView = new SwimFishView(this);
        this.swimFishView.setBitMap(DrawFishView.bitmap);
        setContentView(this.swimFishView);
	}
}
