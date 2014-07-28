package jp.ac.st.asojuku.original2014002;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;


public class MaintenanceActivity extends Activity implements View.OnClickListener,AdapterView.ItemClickListener


	//
	SQLiteDatabase sdb = null;
	//
	MYSQLiteOpenHelper helper = null;
	
	
	//
	int selectedID = -1;
	
	int lastPosition = -1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO 自動生成されたメソッド・スタブ
		super.onCreate(savedInstanceState);
		setContentView(R.layout.MaintenanceActivity);
	}

	@Override
	protected void onResume() {
		// TODO 自動生成されたメソッド・スタブ
		super.onResume();
		
		Button btnDelete = (Button)findViewById(R.id,btnDelete);
		
	}
	
}
