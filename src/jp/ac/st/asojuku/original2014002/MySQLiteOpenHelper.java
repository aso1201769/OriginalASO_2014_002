package jp.ac.st.asojuku.original2014002;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 *
 */

/**
 * @author student
 *
 */
public class MySQLiteOpenHelper extends SQLiteOpenHelper {

	/**
	 * @param context 呼び出しコンテクスト
	 * @param name 利用DB名
	 * @param factory カーソルファクトリー
	 * @param version DBバージョン
	 */
	public MySQLiteOpenHelper(Context context) {

		super(context, "20140021201769.sqlite3", null,1);

	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO 自動生成されたメソッド・スタブ
		db.execSQL("CREATE TABLE IF NOT EXISTS" +
					"Hitokoto ( _id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL , phrase TEXT )");

	}

	/* (非 Javadoc)
	 * @see android.database.sqlite.SQLiteOpenHelper#onUpgrade(android.database.sqlite.SQLiteDatabase, int, int)
	 */
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("drop table Hitokoto ");
		onCreate(db);
		// TODO 自動生成されたメソッド・スタブ

	}
	/**
	 * 引数のフレーズをhutakotoテーブルにインサートするプライベートメソッド
	 * @param SQLiteDatabase インサート先DBのインスタンス変数
	 * @param inputMsg インサートするメッセージ
	 */
	public void inserthutakoto(SQLiteDatabase db,String inputMsg){

		String sqlstr = "insert into hutakoto (phrase) values(' " +  inputMsg + " '); ";
		try {
			//トランザクション開始
			db.beginTransaction();
			db.execSQL(sqlstr);
			//やったぜ
			db.setTransactionSuccessful();
		} catch (SQLException e) {
			Log.e("ERROR", e.toString());
		}finally{
			//終わり
			db.endTransaction();
		}
	return;
}


/**
 * 引数のフレーズをhutakotoテーブルにインサートするプライベートメソッド
 * @param SQLiteDatabase インサート先DBのインスタンス変数
 * @param inputMsg インサートするメッセージ
 */
public String selectRandomhutakoto(SQLiteDatabase db){

	String rtString = null;

	String sqlstr = "SELECT _id phpase FROM hutakoto ORDER BY RANDOM();";
		try {
			//
			SQLiteCursor cursor = (SQLiteCursor)db.rawQuery(sqlstr,null);
			if(cursor.getCount()!=0){
				//
				cursor.moveToFirst();
				rtString = cursor.getString(1);
				}
			cursor.close();

		} catch (SQLException e)  {
			Log.e("ERROR", e.toString());
		} finally {
			//
		}
		return rtString;
	}



public void deletehutakoto(SQLiteDatabase db, int id){
	
		String sqlstr = " DELETE FROM Hitokoto where _id = " + id + " ;";
		try {
			//
			db.beginTransaction();
			db.execSQL(sqlstr);
			//
			db.setTransactionSuccessful();
		} catch (SQLException e) {
			Log.e("ERROR", e.toString());
		}finally{
			//
			db.endTransaction();
		}
	}
}




















