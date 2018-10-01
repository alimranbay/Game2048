package com.aliimran.game2048;

import android.annotation.SuppressLint;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.LinearLayout.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;

@SuppressWarnings("deprecation")
public class NumberBoxes {
	private Resources res;
	private int random1, random2;
	private Drawable box2, box4, box8, box16, box32, box64, box128, box256, box512, box1024, box2048;
	private TextView number2;
	private int[][] numbers;
	private static LinearLayout[][] boxes;
	
	public NumberBoxes(Resources res, int random1, int random2, TextView number2, int[][] numbers, int number){
		this.res = res;
		this.random1 = random1;
		this.random2 = random2;
		box2 = res.getDrawable(R.drawable.block_2);
		box4 = res.getDrawable(R.drawable.block_4);
		box8 = res.getDrawable(R.drawable.block_8);
		box16 = res.getDrawable(R.drawable.block_16);
		box32 = res.getDrawable(R.drawable.block_32);
		box64 = res.getDrawable(R.drawable.block_64);
		box128 = res.getDrawable(R.drawable.block_128);
		box256 = res.getDrawable(R.drawable.block_256);
		box512 = res.getDrawable(R.drawable.block_512);
		box1024 = res.getDrawable(R.drawable.block_1024);
		box2048 = res.getDrawable(R.drawable.block_2048);
		this.number2 = number2;
		this.numbers = numbers;
		
		makeBox(number);
	}
	
	@SuppressLint("NewApi") 
	public void makeBox(int number){
		if(number==0){
			boxes = ClassicFragment.getBoxes();
			if(android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.JELLY_BEAN){
				boxes[random1][random2].setBackgroundDrawable(box2);
			} else boxes[random1][random2].setBackground(box2);
			ClassicFragment.setBoxes(boxes);
			number2.setText("2");
			number2.setTextColor(res.getColor(R.color.darkbrown));
			number2.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 50);
			LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		    lp.gravity= Gravity.CENTER_HORIZONTAL; 
		    number2.setLayoutParams(lp);
		    number2.setGravity(Gravity.CENTER);
		    numbers[random1][random2] = 2;
		}
		else if(number == 1){
			boxes = ClassicFragment.getBoxes();
			if(android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.JELLY_BEAN){
				boxes[random1][random2].setBackgroundDrawable(box4);
			} else boxes[random1][random2].setBackground(box4);
			ClassicFragment.setBoxes(boxes);
			number2.setText("4");
			number2.setTextColor(res.getColor(R.color.darkbrown));
			number2.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 50);
			LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		    lp.gravity= Gravity.CENTER_HORIZONTAL; 
		    number2.setLayoutParams(lp);
		    number2.setGravity(Gravity.CENTER);
		    numbers[random1][random2] = 4;
		}
		else if(number == 2){
			boxes = ClassicFragment.getBoxes();
			if(android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.JELLY_BEAN){
				boxes[random1][random2].setBackgroundDrawable(box8);
			} else boxes[random1][random2].setBackground(box8);
			ClassicFragment.setBoxes(boxes);
			number2.setText("8");
			number2.setTextColor(res.getColor(R.color.darkbrown));
			number2.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 50);
			LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		    lp.gravity= Gravity.CENTER_HORIZONTAL; 
		    number2.setLayoutParams(lp);
		    number2.setGravity(Gravity.CENTER);
		    numbers[random1][random2] = 8;
		}
		else if(number == 3){
			boxes = ClassicFragment.getBoxes();
			if(android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.JELLY_BEAN){
				boxes[random1][random2].setBackgroundDrawable(box16);
			} else boxes[random1][random2].setBackground(box16);
			ClassicFragment.setBoxes(boxes);
			number2.setText("16");
			number2.setTextColor(res.getColor(R.color.darkbrown));
			number2.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 50);
			LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		    lp.gravity= Gravity.CENTER_HORIZONTAL; 
		    number2.setLayoutParams(lp);
		    number2.setGravity(Gravity.CENTER);
		    numbers[random1][random2] = 16;
		}
		else if(number == 4){
			boxes = ClassicFragment.getBoxes();
			if(android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.JELLY_BEAN){
				boxes[random1][random2].setBackgroundDrawable(box32);
			} else boxes[random1][random2].setBackground(box32);
			ClassicFragment.setBoxes(boxes);
			number2.setText("32");
			number2.setTextColor(res.getColor(R.color.darkbrown));
			number2.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 50);
			LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		    lp.gravity= Gravity.CENTER_HORIZONTAL; 
		    number2.setLayoutParams(lp);
		    number2.setGravity(Gravity.CENTER);
		    numbers[random1][random2] = 32;
		}
		else if(number == 5){
			boxes = ClassicFragment.getBoxes();
			if(android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.JELLY_BEAN){
				boxes[random1][random2].setBackgroundDrawable(box64);
			} else boxes[random1][random2].setBackground(box64);
			ClassicFragment.setBoxes(boxes);
			number2.setText("64");
			number2.setTextColor(res.getColor(R.color.darkbrown));
			number2.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 50);
			LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		    lp.gravity= Gravity.CENTER_HORIZONTAL; 
		    number2.setLayoutParams(lp);
		    number2.setGravity(Gravity.CENTER);
		    numbers[random1][random2] = 64;
		}
		else if(number == 6){
			boxes = ClassicFragment.getBoxes();
			if(android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.JELLY_BEAN){
				boxes[random1][random2].setBackgroundDrawable(box128);
			} else boxes[random1][random2].setBackground(box128);
			ClassicFragment.setBoxes(boxes);
			number2.setText("128");
			number2.setTextColor(res.getColor(R.color.darkbrown));
			number2.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 50);
			LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		    lp.gravity= Gravity.CENTER_HORIZONTAL; 
		    number2.setLayoutParams(lp);
		    number2.setGravity(Gravity.CENTER);
		    numbers[random1][random2] = 128;
		}
		else if(number == 7){
			boxes = ClassicFragment.getBoxes();
			if(android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.JELLY_BEAN){
				boxes[random1][random2].setBackgroundDrawable(box256);
			} else boxes[random1][random2].setBackground(box256);
			ClassicFragment.setBoxes(boxes);
			number2.setText("256");
			number2.setTextColor(res.getColor(R.color.darkbrown));
			number2.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 50);
			LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		    lp.gravity= Gravity.CENTER_HORIZONTAL; 
		    number2.setLayoutParams(lp);
		    number2.setGravity(Gravity.CENTER);
		    numbers[random1][random2] = 256;
		}
		else if(number == 8){
			boxes = ClassicFragment.getBoxes();
			if(android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.JELLY_BEAN){
				boxes[random1][random2].setBackgroundDrawable(box512);
			} else boxes[random1][random2].setBackground(box512);
			ClassicFragment.setBoxes(boxes);
			number2.setText("512");
			number2.setTextColor(res.getColor(R.color.darkbrown));
			number2.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 50);
			LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		    lp.gravity= Gravity.CENTER_HORIZONTAL; 
		    number2.setLayoutParams(lp);
		    number2.setGravity(Gravity.CENTER);
		    numbers[random1][random2] = 512;
		}
		else if(number == 9){
			boxes = ClassicFragment.getBoxes();
			if(android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.JELLY_BEAN){
				boxes[random1][random2].setBackgroundDrawable(box1024);
			} else boxes[random1][random2].setBackground(box1024);
			ClassicFragment.setBoxes(boxes);
			number2.setText("1024");
			number2.setTextColor(res.getColor(R.color.darkbrown));
			number2.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 50);
			LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		    lp.gravity= Gravity.CENTER_HORIZONTAL; 
		    number2.setLayoutParams(lp);
		    number2.setGravity(Gravity.CENTER);
		    numbers[random1][random2] = 1024;
		}
		else if(number == 10){
			boxes = ClassicFragment.getBoxes();
			if(android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.JELLY_BEAN){
				boxes[random1][random2].setBackgroundDrawable(box2048);
			} else boxes[random1][random2].setBackground(box2048);
			ClassicFragment.setBoxes(boxes);
			number2.setText("2048");
			number2.setTextColor(res.getColor(R.color.darkbrown));
			number2.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 50);
			LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		    lp.gravity= Gravity.CENTER_HORIZONTAL; 
		    number2.setLayoutParams(lp);
		    number2.setGravity(Gravity.CENTER);
		    numbers[random1][random2] = 2048;
		}
	}

	public int[][] getNumbers() {
		return numbers;
	}

}
