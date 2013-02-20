package bouncer.database;







import bouncer.logic.R;
import bouncer.logic.R.layout;
import bouncer.logic.R.menu;
import android.os.Bundle;
import android.app.Activity;
import android.database.Cursor;
import android.util.Log;
import android.view.Menu;
import android.widget.ListView;

public class ResultsList extends Activity {
	
	
	private static String TAG=ResultsList.class.getCanonicalName();
	DatabaseHelper databaseHelper;
	ResultsListAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results_list);
        
        ListView listView = (ListView )findViewById(R.id.results_list);
       
       databaseHelper = new DatabaseHelper(this);
       Cursor cursor = databaseHelper.getAllRecors();
       
       if(cursor.moveToFirst()){
       	
       	do{
       	String name = cursor.getString(1);
       	String points = cursor.getString(2);
       	
       	Log.d(TAG," " + name + " " + points + " " + name);
       	}while(cursor.moveToNext());
       	
       }
       
       adapter = new ResultsListAdapter(this, cursor);
       listView.setAdapter(adapter);
       
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_results_list, menu);
        return true;
    }
}
