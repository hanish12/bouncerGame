package bouncer.database;


import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;
import bouncer.logic.R;

public class ResultsListAdapter extends CursorAdapter {
	
	static String RESULTS_COLUMN_NAME = "Name";
	static String RESULTS_COLUMN_POINTS = "Points";

	@SuppressWarnings("deprecation")
	public ResultsListAdapter(Context context, Cursor c) {
		super(context, c);
		
	}
	

	@Override//to populate the view with data
	public void bindView(View view, Context arg1, Cursor cursor) {
		TextView nameText = (TextView) view.findViewById(R.id.nameRecord);
		nameText.setText(cursor.getString(cursor.getColumnIndex(RESULTS_COLUMN_NAME)));
		
		TextView latText = (TextView) view.findViewById(R.id.pointsRecord);
		latText.setText(cursor.getString(cursor.getColumnIndex(RESULTS_COLUMN_POINTS)));
	}

	@Override//to create the view
	public View newView(Context arg0, Cursor arg1, ViewGroup parent) {
		LayoutInflater inflater =  LayoutInflater.from(parent.getContext());
		View view = inflater.inflate(R.layout.activity_results_list, parent, false);
		
		return view;
	}
	

}
