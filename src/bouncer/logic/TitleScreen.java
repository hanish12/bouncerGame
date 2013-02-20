package bouncer.logic;

import bouncer.database.ResultsList;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.text.Editable;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Toast;

public class TitleScreen extends Activity {
	private static String PREFS_NAME="bouncer_game";
	private static String SPEED_NAME="speedBall";
	private static String TAG=TitleScreen.class.getCanonicalName();
	private static String USER_NAME="userName";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_title_screen);
        
        setTypedNameEditText();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_title_screen, menu);
        return true;
    }
    
    public void	setTypedNameEditText(){
    	String playerName=LoadPreferences(USER_NAME);
    	if( !(playerName.contains("empty")) ){
    		 EditText et = (EditText)findViewById(R.id.typedName);
    		 et.setText(playerName);
    	}
    		
    }

    
    public void startGame(View view){
    	
    	 EditText et = (EditText)findViewById(R.id.typedName);
    	 String nameS=et.getText().toString();
    	 
    		Context context = getApplicationContext();
        	int duration = Toast.LENGTH_LONG;
        	
            String text = "you must type your name";
            Toast toast = Toast.makeText(context, text, duration);
    	 if(nameS.isEmpty())
    		 toast.show();
    	 else{
    	 
         SavePreferences(USER_NAME, nameS);
    	
    	  SeekBar sb1=(SeekBar) findViewById(R.id.setBallSpeed);
    	  int value = sb1.getProgress();
    	  SavePreferences(SPEED_NAME, value+"");
    	  
    	  Intent intent = new Intent(this,Bouncer.class);
    	  startActivity(intent);
    	 }
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
    
 public void goToResultsList(View view){
    	
    	Intent intent = new Intent(this,ResultsList.class);
 	   	startActivity(intent);
    	
    	
    }
 
 @SuppressLint("WorldReadableFiles")
private String LoadPreferences(String key){
   	   
        String defaultString = "empty";  
        String location ="";
        SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_WORLD_READABLE);
        location =  sharedPreferences.getString( key, defaultString );
        System.out.println("loadRestore key = " + location);
        	
        return location;
   
       }
    
  
    
    
}
