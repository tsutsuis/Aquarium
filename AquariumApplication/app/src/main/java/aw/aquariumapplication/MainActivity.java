package aw.aquariumapplication;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    SwimFishView swimFishView = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        this.swimFishView = new SwimFishView(this);
		setContentView(R.layout.activity_main);
	}

	// ボタン押下
	public void buttonClick(View view) {
        this.swimFishView.setBitMap(DrawFishView.bitmap);
        setContentView(this.swimFishView);
	}
}
