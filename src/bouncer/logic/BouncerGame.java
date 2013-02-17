package bouncer.logic;





import java.util.Random;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;

public class BouncerGame extends ArcadeGame {

    Random randomGenerator = new Random();
    Random randomGenerator2 = new Random();
	public static String TAG = BouncerGame.class.getCanonicalName();
	private Context mContext;
	
	// For text
	private Paint mTextPaint = new Paint();

	// For Bitmaps
	private Paint mBitmapPaint = new Paint();
	
	// Game name
	public static final String NAME = "SpaceBlaster";
	
	// Refresh rate (ms)
	private static final long UPDATE_DELAY = 20; 

	
	int playerX;
	int playerY;
	
	int ballX;
	int ballY;
	/**
	 * Constructor
	 * 
	 * @param context
	 */
	public BouncerGame(Context context) {
		super(context);
		mContext = context;
		super.setUpdatePeriod(UPDATE_DELAY);
	}

	public BouncerGame(Context context, AttributeSet attrs) {
		super(context, attrs);
		mContext = context;
		super.setUpdatePeriod(UPDATE_DELAY);
	}
	
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		int tx = (int)event.getX();
		int ty = (int)event.getY();
//		System.out.println("tx=" + tx 
//				+ " ty=" + ty + " ship x=" + x + " y=" + y 
//				+ " ship wh=" + ship.getWidth() + " " + ship.getHeight()
//				+ " edt=" + event.getDownTime() +  " ep=" + event.getPressure());

		
		// Has the ship been touched. if so move it
		
		// else start game if not already started
		
		if ( tx > playerX + player.getWidth())
			playerX+=10;
		else if( tx < playerX)
			playerX-=10;
			
			
		
		return true;
	}
	
	
	
	
	/**
	 * Main Draw Sub
	 */
	@Override
	protected void onDraw(Canvas canvas) 
	{
		super.dispatchDraw(canvas);
		paint(canvas);
	}
	
	boolean ingame = true;
	public void paint(Canvas g) {
		if (ingame)
			playGame(g);
	//	else
		//	showIntroScreen(g);
	}
	
	Bitmap[] platforms;
	//x,y,direction
	int[] initPoint;
	int[] direction;
	
	public void playGame(Canvas c) {
		
		drawPlayerPlatform(c);
		c.drawBitmap(platforms[0], initPoint[0], initPoint[1], mBitmapPaint);
		drawPlatforms(c);
	    movePlatforms();
	    drawBall(c);
	    moveBall();
	}
	
	public static int nrOfPlatforms = 7;
	public void initPlatforms(){
		int width = getWidth();
		
		platforms=new Bitmap[nrOfPlatforms];
		initPoint=new int[nrOfPlatforms*2];
		direction=new int[nrOfPlatforms];
		//5 platforms
		for(int i = 0; i < nrOfPlatforms ; i++){
		platforms[i] = getImage(R.drawable.platformbluelarger);
		}
		
		for(int i = 0; i < nrOfPlatforms*2 ; i+=2){
		
		//initPoint[i]=0;
		initPoint[i] = width/2 + i*platforms[0].getWidth()/3;
		//initPoint[i+1]=0;
		initPoint[i+1] = 10 + i * platforms[0].getHeight();
	
		
		}
		for(int i = 0; i < nrOfPlatforms ; i++){
			direction[i]=i+1;
		}
		
		
		
		
	}
	
	public void drawBall(Canvas c){
		c.drawBitmap(ball, ballX, ballY, mBitmapPaint);
	}
	

	
	int ballSpeed = 15;
	int ballDirection = -1;
	
	int ballSpeedX = 5;
	int ballDirectionX = 0;
	public void moveBall(){
		//collision detection
		int width = getWidth();
		//1.with platforms
		for(int i = 0; i < nrOfPlatforms*2; i+=2){
			
			
			if( (ballX > initPoint[i]) && (ballX < initPoint[i] + platforms[0].getWidth())
				&& (ballY <= initPoint[i+1] + platforms[0].getHeight())){
				
			  	ballDirection=1;
			}
			
					
		}
		
		for(int i = 0; i < nrOfPlatforms*2; i+=2){
			
			
			if( (ballX > initPoint[i]) && (ballX < initPoint[i] + platforms[0].getWidth())
				&& (ballY < initPoint[i+1] )){
				
				Log.d(TAG, ballY + " < " + initPoint[i+1]);
				//if(initPoint[i]-ballY == 1){
			  	   // Log.d(TAG, "in == 1");
					ballDirection=-1;
				//}
			}
			
					
		}
		
		
		
		//2.with player
		  if( (ballX > playerX) && (ballX < playerX + player.getWidth())
				  && (ballY >= playerY - player.getHeight())){
			  ballCollisionWithPlayer();
			  ballDirection=-1;
		  }
		//3.with up-wall
		  if(ballY <= 0 + ball.getHeight())
			  ballDirection=1;
		  
		  //4.with left and right wall
		  if(ballX <= 0)
			  ballDirectionX=1;
		  if(ballX >= width-ball.getWidth() )
			  ballDirectionX=-1;
		  
		  int height=getHeight();
		  //4.with down-wall
			  if(ballY > height)
				  gameOver();
		
		
			ballY+=ballSpeed*ballDirection;
			ballX+=ballSpeedX*ballDirectionX;
		
	}
	
	int divideFragments = 3;
	int[] xydivFr;

	public void ballCollisionWithPlayer(){
	
		
		
		//int platformWidth = playerX + player.getWidth();
		int interval = player.getWidth()/divideFragments;
		
		int tempPlayerX=playerX;
		for(int i = 0; i < divideFragments*2; i+=2){
			xydivFr[i] = tempPlayerX;
			xydivFr[i+1] = tempPlayerX+interval;
			tempPlayerX = tempPlayerX+interval;
		}
		Log.d(TAG,""+ xydivFr);
		
		//conditions depends on where ball touch player platform
		if( (ballX > xydivFr[0]) && (ballX < xydivFr[1]) )
			ballDirectionX=-1;
		else if( (ballX > xydivFr[2]) && (ballX < xydivFr[3]) )
			ballDirectionX=0;
		else if( (ballX > xydivFr[4]) && (ballX < xydivFr[5]) )
			ballDirectionX=1;
				
		
		
	}
	
	
	
	
	
	
	
	public void drawPlatforms(Canvas c){
		for(int i = 0; i < nrOfPlatforms ; i++){
	    int index=i*2;
		c.drawBitmap(platforms[i], initPoint[index], initPoint[index+1], mBitmapPaint);
		}
	}

	//int direction = 1;
	public void movePlatforms(){
		
		int width = getWidth();
		/*int i = initPoint[0];
		initPoint[0] = i + direction[0];
		
		if(initPoint[0] >= width - platforms[0].getWidth())
			direction[0] = -1;
		if(initPoint[0] <= 0)
			direction[0] = 1;*/
		
		for(int index = 0; index < nrOfPlatforms ; index++){
			int iMul= index * 2;
			int i = initPoint[iMul];
			initPoint[iMul] = i + direction[index];
			
			if(initPoint[iMul] >= width - platforms[index].getWidth())
				direction[index] = -1*direction[index];
			if(initPoint[iMul] <= 0)
				direction[index] = 1*-(direction[index]);
		
		}
		
	}
	
	@Override
	protected void updatePhysics() {
		// TODO Auto-generated method stub
		
	}


	Bitmap player;
	Bitmap ball;
	@Override
	protected void initialize() {
		Log.d(TAG,"initalize");
		int n;
	
			
		
		// Screen size
		int width = getWidth();
		int height = getHeight();
		
		initPlatforms();
		
		mTextPaint.setARGB(255, 255, 255, 255);
		
		player = getImage(R.drawable.player_platform);
		playerX = width/2;
		playerY = height - player.getHeight();
		
		ball = getImage(R.drawable.ball);
		ballX = playerX + player.getWidth()/2;
		ballY = playerY - ball.getHeight();
		xydivFr=new int[divideFragments*2];
	}
	
	
	public void drawPlayerPlatform(Canvas c){
		int height = getHeight();
		c.drawBitmap(player, playerX, playerY, mBitmapPaint );
	}

	@Override
	protected boolean gameOver() {
		//here should be something logic, now just restart
		ballDirection=-1;
		return false;
	}

	@Override
	protected long getScore() {
		// TODO Auto-generated method stub
		return 0;
	}

}
