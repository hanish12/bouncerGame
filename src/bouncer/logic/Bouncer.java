package bouncer.logic;



import android.R.menu;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;

public class Bouncer extends Activity {
	private View view;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         
        LayoutInflater factory = LayoutInflater.from(this);
        
        // Set game layout
        view = factory.inflate(R.layout.activity_main, null);
        setContentView(view);
        
       // this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        // Enable view key events
		view.setFocusable(true);
		view.setFocusableInTouchMode(true);
        
	}
	
	@Override
	public void onBackPressed() {
	  //  if (this.lastBackPressTime < System.currentTimeMillis() - 4000) {
	   //     Toast.makeText(this, R.string.backButtonWarning, 4000).show();
	  //      this.lastBackPressTime = System.currentTimeMillis();
	  //  } else {
	  //      super.onBackPressed();
	  //  }
		showMenu();
	}
	
	private void showMenu(){
		
		Intent intent = new Intent(this, Menu.class);
		startActivity(intent);
	}

    
    @Override
    protected void onStop() {
    	super.onStop();
    	((ArcadeGame)view).halt();
    }
    
    @Override
    protected void onPause() {
    	super.onPause();
    	onStop();
    }
    
    @Override
    protected void onRestart() {
    	super.onRestart();
    	((ArcadeGame)view).resume();
    }
}