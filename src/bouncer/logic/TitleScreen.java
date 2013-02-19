package bouncer.logic;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.SeekBar;

public class TitleScreen extends Activity {
	private static String PREFS_NAME="bouncer_game";
	private static String SPEED_NAME="speedBall";
	private static String TAG=TitleScreen.class.getCanonicalName();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_title_screen);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_title_screen, menu);
        return true;
    }
    
    public void startGame(View view){
    	  SeekBar sb1=(SeekBar) findViewById(R.id.setBallSpeed);
    	  int value = sb1.getProgress();
    	  SavePreferences(SPEED_NAME, value+"");
    	  
    	  Intent intent = new Intent(this,Bouncer.class);
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
    
    public void exit(View view){
    	Intent intent = new Intent(this,TitleScreen.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
  	   	startActivity(intent);
    	backgroundApp();
    }
    	
    
    
    public void backgroundApp(){
        
    	this.moveTaskToBack(true);
    	
    }
    
  
    
    
}
