package jp.ac.st.asojuku.original2014002;

import android.app.Activity;
import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;



public class MaintenanceActivity extends Activity implements View.OnClickListener,AdapterView.OnItemClickListener{


	//
	SQLiteDatabase sdb = null;
	//
	MySQLiteOpenHelper helper = null;


	//
	int selectedID = -1;

	int lastPosition = -1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO 自動生成されたメソッド・スタブ
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mentenansu);
	}

	@Override
	protected void onResume() {
		// TODO 自動生成されたメソッド・スタブ
		super.onResume();

		Button btndelete = (Button)findViewById(R.id.btnDelete);
		Button btnmainte_back = (Button)findViewById(R.id.btnMainte_back);
		ListView lsthitokoto = (ListView)findViewById(R.id.Lvhitokoto);

		//
		btndelete.setOnClickListener(this);
		btnmainte_back.setOnClickListener(this);

		lsthitokoto.setOnItemClickListener(this);

		this.setDBValuetoList(lsthitokoto);
	}

	private void setDBValuetoList(ListView lsthitokoto) {
		// TODO 自動生成されたメソッド・スタブ
		SQLiteCursor cursor = null;
		 if(sdb == null){
			 helper = new MySQLiteOpenHelper(getApplicationContext());
		 }
		 try{
			 sdb = helper.getWritableDatabase();
		 }catch(SQLiteException e){
			 Log.e("ERROR",e.toString());
		 }
		cursor = this.helper.selectHitokotoList(sdb);
		
		int db_layout = android.R.layout.simple_list_item_activated_1;
	    
		String[]from = {"phrase"};
		
		int[] to = new int[]{android.R.id.text1};
		
		SimpleCursorAdapter adapter =new SimpleCursorAdapter(this,db_layout,cursor,from,to,0);

		lsthitokoto.setAdapter(adapter);
	}

	@Override
	public void onClick(View v) {
		// TODO 自動生成されたメソッド・スタブ
		switch(v.getId()) {
			case R.id.btnDelete:
		
		//
		if(this.selectedID != -1){
				this.deleteFromHitokoto(this.selectedID);
				ListView lsthitokoto = (ListView)findViewById(R.id.Lvhitokoto);
				//
				this.setDBValuetoList(lsthitokoto);
				
				this.selectedID = -1;
				this.lastPosition = -1;
				
		} else{
			
			Toast.makeText(MaintenanceActivity.this,"削除する行を選んでください", Toast.LENGTH_SHORT).show();
			
		}
		break;
			case R.id.btnMainte_back:
				//
				finish();
			break;
		}
	}

	

	private void deleteFromHitokoto(int id) {
		// TODO 自動生成されたメソッド・スタブ
		if(sdb == null){
			helper = new MySQLiteOpenHelper(getApplicationContext());
		
		}try{
			sdb = helper.getWritableDatabase();
		}catch(SQLiteException e){
			
			Log.e("EROOR", e.toString());
			
		}this.helper.deletehitokoto(sdb, id);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		// TODO 自動生成されたメソッド・スタブ
		
		//
		if(this.selectedID!=-1){
			parent.getChildAt(this.lastPosition).setBackgroundColor(0);
			}
		//
		view.setBackgroundColor(android.graphics.Color.LTGRAY);
		
		//
		SQLiteCursor cursor = (SQLiteCursor)parent.getItemAtPosition(position);
		
		this.selectedID = cursor.getInt(cursor.getColumnIndex("_id"));
		
		this.lastPosition = position;
		
		
	}

}
