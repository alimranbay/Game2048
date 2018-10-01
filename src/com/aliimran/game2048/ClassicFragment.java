package com.aliimran.game2048;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.gesture.Gesture;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.Display;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

public class ClassicFragment extends Fragment{
	//In the end, you should check if everything is OK and compatible by commenting the line below
	public static LinearLayout[][] boxes;
	private GestureDetector mGestureDetector;
	private static int[][] numbers = new int[4][4];
	private int width, height;
	
	public static LinearLayout[][] getBoxes() {
		return boxes;
	}
	public static void setBoxes(LinearLayout[][] boxes) {
		ClassicFragment.boxes = boxes;
	}
	@SuppressLint("NewApi") @SuppressWarnings("deprecation")
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
		final View rootView = inflater.inflate(R.layout.fragment_classic, container, false);
		LinearLayout theBox = (LinearLayout) rootView.findViewById(R.id.thebox);	
		//LinearLayout[][] 
		boxes = new LinearLayout[4][4];
		
		for(int i=0;i<4;i++){
			for(int j=0;j<4;j++){
				numbers[i][j] = 0;
			}
		}
		
		//Get the screen size in pixels.
		Context context = getActivity();
		WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		Display display = wm.getDefaultDisplay();
		Point size = new Point();
		
		if(android.os.Build.VERSION.SDK_INT>=13){
			display.getSize(size);
			width = size.x;
			height = size.y;
		}
		else{
			width = display.getWidth();
			height = display.getHeight();
		}

		//set the box height to match the width
		final float scale = getActivity().getResources().getDisplayMetrics().density;
		int pixels, pixels2;
		
		android.view.ViewGroup.LayoutParams params = theBox.getLayoutParams();
		//http://stackoverflow.com/questions/6656540/android-convert-px-to-dp-video-aspect-ratio
		//http://stackoverflow.com/questions/4605527/converting-pixels-to-dp
		params.height = width-convertDpToPixel(30, context);
		theBox.setLayoutParams(params);
		
		//Add boxes in theBox
		for(int i =0; i < 4; i++){
			LinearLayout subBox = new LinearLayout(getActivity());
			//subBox.setLayoutParams(new LinearLayout.LayoutParams(width-60, (width-85)/4));
			if(i == 0){
				LayoutParams params2 = new LinearLayout.LayoutParams(width-convertDpToPixel(30, context)
						, (width-convertDpToPixel(40, context))/4);
				params2.setMargins(0, convertDpToPixel(5, context), 0, 0);
				subBox.setLayoutParams(params2);
			}else{
				subBox.setLayoutParams(new LinearLayout.LayoutParams(width-convertDpToPixel(30, context)
						, (width-convertDpToPixel(40, context))/4));
			}
			for(int j =0; j < 4; j++){
				LinearLayout box = box(context, getResources(), width);
				
				boxes[i][j] = box;
				
				subBox.addView(box);
			}
			
			theBox.addView(subBox);
		}
		
		placeRandomNumber(getTag(), getResources(), boxes, getActivity(), numbers);
		placeRandomNumber(getTag(), getResources(), boxes, getActivity(), numbers);
		
		mGestureDetector = new GestureDetector(getActivity(), new GestureDetector.SimpleOnGestureListener(){
			
			@Override
            public boolean onDown(MotionEvent e) {
                return true;
            }
			
			//If the user flings, then the blocks should be moved to the direction of the fling, and if needed, 
			//the blocks should be combinated.
			@Override
			public boolean onFling(MotionEvent e1, MotionEvent e2,
					float velocityX, float velocityY) {
				//http://stackoverflow.com/questions/937313/android-basic-gesture-detection
			    final int SWIPE_MIN_DISTANCE = 120;
			    final int SWIPE_MAX_OFF_PATH = 250;
			    final int SWIPE_THRESHOLD_VELOCITY = 200;
			    boolean place = false;
			    
				if(e2.getX() - e1.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY){
					Log.i("Classic", "Fling right");
					for(int y = 0; y < 4; y++){
						int nul = -1;
						boolean stop = false;
						for(int x = 3; x >= 0; x--){
							if(numbers[y][x] == 0 && stop == false){
								nul = x; stop = true;
							}
						}
						for(int x = 3; x >= 0; x--){
							if(x > 0 && numbers[y][x-1] == numbers[y][x] && nul < 3 && x < 4 && numbers[y][x] > 0){
								numbers[y][x] *=2;
								numbers[y][x-1] = 0;
								x++; nul= x-1;
								rewrite(getActivity(), getResources(), width);
								place = true;
							}
							else if(x < 3 && numbers[y][x] > 0 && x < nul && nul >= 0 && y < nul){
								numbers[y][nul] = numbers[y][x];
								numbers[y][x] = 0;
								nul--; x = 4;
								rewrite(getActivity(), getResources(), width);
								place = true;
							}
						}
					}
				}
				else if(e1.getX() - e2.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY){
					Log.i("Classic", "Fling left");
					for(int y = 0; y < 4; y++){
						int nul = 4;
						boolean stop = false;
						for(int x = 0; x < 4; x++){
							if(numbers[y][x] == 0 && stop == false){
								nul = x; stop = true;
							}
						}
						for(int x = 0; x < 4; x++){
							if(x >= 0 && x < 3 && numbers[y][x] > 0 && numbers[y][x+1] == numbers[y][x] && nul > 0){
								numbers[y][x+1] = 0;
								numbers[y][x] *= 2;
								nul = x+1; x--;
								rewrite(getActivity(), getResources(), width);
								place = true;
							}
							else if(x > 0 && numbers[y][x] > 0 && x < 4 && y < 4 && nul < 4 && y > nul){
								numbers[y][nul] = numbers[y][x];
								numbers[y][x] = 0;
								nul++; x = -1;
								rewrite(getActivity(), getResources(), width);
								place = true;
							}
						}
					}
				}
				else if(e1.getY() - e2.getY() > SWIPE_MIN_DISTANCE && Math.abs(velocityY) > SWIPE_THRESHOLD_VELOCITY){
					Log.i("Classic", "Fling up");
					for(int x = 0; x < 4; x++){
						int nul = 4;
						boolean stop = false;
						for(int y = 0; y < 4; y++){
							if(numbers[y][x] == 0 && stop == false){
								nul = y; stop = true;
							}
						}
						for(int y = 0; y < 4; y++){
							if(y < 3 && nul > 0 &&numbers[y+1][x] == numbers[y][x] && numbers[y][x] > 0 && y >= 0){
								numbers[y+1][x] = 0;
								numbers[y][x] *= 2;
								nul = y+1; y--;
								rewrite(getActivity(), getResources(), width);
								place = true;
							}
							else if(nul < 4 && y > 0 && numbers[y][x] > 0 && y > nul){
								numbers[nul][x] = numbers[y][x];
								numbers[y][x] = 0;
								nul++; y = -1;
								rewrite(getActivity(), getResources(), width);
								place = true;
							}
						}
					}
				}
				else if(e2.getY() - e1.getY() > SWIPE_MIN_DISTANCE && Math.abs(velocityY) > SWIPE_THRESHOLD_VELOCITY){
					Log.i("Classic", "Fling down");
					for(int x = 0; x < 4; x++){
						int nul = -1;
						boolean stop = false;
						for(int y = 3; y >= 0; y--){
							if(numbers[y][x] == 0 && stop == false){
								nul = y; stop = true;
							}
						}
						for(int y = 3; y >= 0; y--){
							if(y> 0 && nul < 3 &&numbers[y-1][x] == numbers[y][x] && numbers[y][x] > 0 && y < 4){
								numbers[y-1][x] = 0;
								numbers[y][x] *= 2;
								nul = y ;y++; 
								rewrite(getActivity(), getResources(), width);
								place = true;
							}
							else if(nul >= 0 && y < 4 && numbers[y][x] > 0 && y < nul){
								numbers[nul][x] = numbers[y][x];
								numbers[y][x] = 0;
								nul--; y = 4;
								rewrite(getActivity(), getResources(), width);
								place = true;
							}
						}
					}

				}
				if(place){
					placeRandomNumber(getTag(), getResources(), boxes, getActivity(), numbers);
				}
				return true;
			}
		});
		
		rootView.setOnTouchListener(new View.OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				return mGestureDetector.onTouchEvent(event);
			}
		});
		
		return rootView;
	}
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
	}
	
	public static int convertDpToPixel(float dp, Context context){
	    Resources resources = context.getResources();
	    DisplayMetrics metrics = resources.getDisplayMetrics();
	    int px = (int) (dp * (metrics.densityDpi / 160f));
	    return px;
	}
	
	public static int convertPixelsToDp(float px, Context context){
	    Resources resources = context.getResources();
	    DisplayMetrics metrics = resources.getDisplayMetrics();
	    int dp = (int) (px / (metrics.densityDpi / 160f));
	    return dp;
	}
	
	public boolean onTouchEvent(MotionEvent event) {
		return mGestureDetector.onTouchEvent(event);
	}
	
	//Place a number on a random position, it's a 2 or 4, also randomly selected.
	@SuppressLint("NewApi") 
	public static void placeRandomNumber(String tag, Resources res, LinearLayout boxes[][], Context activity,
			int[][] numbers){
		//Code for creating a block...
		int random1 = Math.round((float)(Math.random() *3));
		int random2 = Math.round((float)(Math.random() *3));
		int random3 = Math.random() < 0.9 ? 0 : 1;
		while(numbers[random1][random2] != 0){
			random1 = Math.round((float)(Math.random() *3));
			random2 = Math.round((float)(Math.random() *3));
			Log.i("test", "ran the while loop once");
		}
		Log.i(tag, random1 + " random");
		
		TextView number2 = new TextView(activity);
		new NumberBoxes(res, random1, random2, number2, numbers, random3);

		boxes[random1][random2].addView(number2);
	}
	@SuppressLint("NewApi") @SuppressWarnings("deprecation")
	public static LinearLayout box(Context context, Resources res, int width){
		LinearLayout box = new LinearLayout(context);
		Drawable emptyBox = res.getDrawable(R.drawable.empty_box);
		if(android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.JELLY_BEAN){
			box.setBackgroundDrawable(emptyBox);
		} else box.setBackground(emptyBox);
		LayoutParams params1 = new LinearLayout.LayoutParams(((width-convertDpToPixel(80, context))/4),
				((width-convertDpToPixel(80, context))/4));
		params1.setMargins(convertDpToPixel(5, context), 0,convertDpToPixel(5, context), 0);
		box.setLayoutParams(params1);
		return box;
	}
	
	public static void rewrite(Context context, Resources res, int width){
		for(int i = 0; i < 4; i++){
			LinearLayout parent = (LinearLayout) boxes[i][0].getParent();
			parent.removeAllViews();
			for(int j = 0; j < 4; j++){
				LinearLayout box = box(context, res, width);
				boxes[i][j] = box;
				parent.addView(box);
				if(numbers[i][j] != 0){
					int theNumber = ((int) (Math.log(numbers[i][j]) / Math.log(2)))-1;
					TextView number2 = new TextView(context);
					new NumberBoxes(res, i, j, number2, numbers, theNumber);
					boxes[i][j].addView(number2);
				}
			}
		}
	}
}
