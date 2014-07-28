package jp.ac.st.asojuku.original2014002;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class hitokoto extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO 自動生成されたメソッド・スタブ
		super.onCreate(savedInstanceState);
		setContentView(R.layout.hutakoto);
	}

	@Override
	protected void onResume() {
		// TODO 自動生成されたメソッド・スタブ
		super.onResume();
		
		Intent intent = this.getIntent();
		
		String strhutakoto = intent.getStringExtra("hutakoto");
		
		
		
		TextView txvhutakoto = (TextView)findViewById(R.id.txvhutakoto);
		txvhutakoto.setText(strhutakoto);
		
		
	}

}
