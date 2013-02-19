package bouncer.logic;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;


public class Menu extends Activity {
	
	private static String PREFS_NAME="bouncer_game";
	private static String ON_OFF_KEY="on_off";
	private static String TAG;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        TAG=Menu.class.getCanonicalName();
    }

   /* @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_menu, menu);
        return true;
    }*/
    
    
    
    public void turnSoundOffOn(View view){
    	  // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();
        
        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.on:
                if (checked){
                	SavePreferences(ON_OFF_KEY,"on");
                	}
                    
                break;
            case R.id.off:
                if (checked)
                	SavePreferences(ON_OFF_KEY,"off");
                break;
        }
    	
    }
    
    
   public void backToGame(View view){
	   Intent intent = new Intent(this, Bouncer.class);
	   startActivity(intent);
	  
   }
    
    
    private void SavePreferences(String key, String value){
 	   
        // String defaultString = "empty";
         //String loc ="";
         SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_WORLD_READABLE);
         SharedPreferences.Editor editor = sharedPreferences.edit();
        
         editor.putString(key , value);
         Log.d(TAG,"saved " + value);
         
         editor.commit();
     
        }
}
