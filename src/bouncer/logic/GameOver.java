package bouncer.logic;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class GameOver extends Activity {
	
	String result;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);
      
        Intent intent = getIntent();
        result=intent.getStringExtra("result");
       
        TextView et = (TextView) findViewById(R.id.points);
        et.setText(result);
      //  System.out.println("res " + result);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_game_over, menu);
        return true;
    }
    
   
    public void goToTitleScreen(View view){
    	
       Intent intent = new Intent(this,TitleScreen.class);
       intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
 	   startActivity(intent);
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
