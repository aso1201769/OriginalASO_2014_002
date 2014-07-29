package jp.ac.st.asojuku.original2014002;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class MainActivity extends Activity implements View.OnClickListener{

	SQLiteDatabase sdb = null;
	MySQLiteOpenHelper helper = null;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		
	}

	@Override
	protected void onResume() {
		// TODO 自動生成されたメソッド・スタブ
		super.onResume();
		
		Button btnm = (Button)findViewById(R.id.buttonm);
		btnm.setOnClickListener(this);
		
		Button btnc = (Button)findViewById(R.id.buttonc);
		btnc.setOnClickListener(this);
				
		Button btnt = (Button)findViewById(R.id.buttont);
		btnt.setOnClickListener(this);
				
		if(sdb == null) {
				helper = new MySQLiteOpenHelper(getApplicationContext());
			}
		try{
			sdb = helper.getWritableDatabase();
		}catch(SQLiteException e){
			//異常
			return;
		}
		
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
   
	@Override
	public void onClick(View v){
		Intent intent = null;
		switch(v.getId()){
		
		case R.id.buttont:
			EditText Etv = (EditText)findViewById(R.id.EditText1);
			String inputMsg = Etv.getText().toString();
			
			//
			if(inputMsg!=null && !inputMsg.isEmpty()){
				//
				helper.inserthutakoto(sdb, inputMsg);
			}
			
			//くりあ
			Etv.setText("");
			break;
		case R.id.buttonm:
			
			//
			intent = new Intent(MainActivity.this, MaintenanceActivity.class);
			
			startActivity(intent);
		case R.id.buttonc:
			
			
			String strhutakoto = helper.selectRandomhutakoto(sdb);
			
			//
			intent = new Intent(MainActivity.this, hitokoto.class);
			
			//
			intent.putExtra("hutakoto", strhutakoto);
			
			//
			startActivity(intent);
			break;
			
		}
	}
	/**
	 * A placeholder fragment containing a simple view.
	 */
	

}
