package com.aliimran.game2048;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.app.Activity;
import android.app.FragmentManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.os.Build;

public class MenuActivity extends ActionBarActivity implements OnClickListener{
//removed "extends ActionBarActivity"
	final String TAG = "MenuActivity";
	private final PlaceholderFragment mPlaceHolderFragment = new PlaceholderFragment();
	android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
    final FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Log.i(TAG, "onCreate");


        
        final LayoutInflater inflater = getLayoutInflater();
        final View fragmentMenu = inflater.inflate(R.layout.fragment_menu, null);
        
        if (savedInstanceState == null) {
        	fragmentTransaction.add(R.id.container, mPlaceHolderFragment);
            //fragmentTransaction.add(R.id.container, new AboutFragment());
            fragmentTransaction.commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    
    /*public void onClick(View v){
    	if(v.getId() == R.id.button_about){
			Log.i(TAG,"About button clicked");
			fragmentTransaction.replace(R.id.container, new AboutFragment());
			fragmentTransaction.addToBackStack(null);
			fragmentTransaction.commit();
    	}
    }*/
    
    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment implements OnClickListener{

        public PlaceholderFragment() {
        }
        
        OnClickListener mListener;
        
        public interface onClickListener {
        	public void onClick(View v);
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            final View rootView = inflater.inflate(R.layout.fragment_menu, null);
            Button aboutButton = (Button) rootView.findViewById(R.id.button_about);
            Button classicButton = (Button) rootView.findViewById(R.id.button_classic);
            aboutButton.setOnClickListener(this);
            classicButton.setOnClickListener(this);
            
            return rootView;
        }

		@Override
		public void onClick(View v) {
			if(v.getId() == R.id.button_about){
				/*fragmentTransaction.replace(R.id.container, new AboutFragment());
				fragmentTransaction.commit();*/
				mListener.onClick(v);
			}
			//De button classic moet nu ook werken
			else if(v.getId() == R.id.button_classic) mListener.onClick(v);
		}
        
		@Override
		public void onAttach(Activity activity){
			super.onAttach(activity);
			
			try{
				mListener = (OnClickListener) activity;
			}catch(ClassCastException e){
				throw new ClassCastException(activity.toString() + " must implement OnArticleSelectedListener");
			}
		}
        
    }

	@Override
	public void onClick(View v) {
		FragmentTransaction fragmentTransaction2 = fragmentManager.beginTransaction();
		Log.i(TAG,"About button clicked");
		if(v.getId() == R.id.button_about){
			fragmentTransaction2.replace(R.id.container, new AboutFragment());
			fragmentTransaction2.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
			Log.i(TAG,"replaced");
			fragmentTransaction2.addToBackStack(null);
			Log.i(TAG,"addtobackstack");
			fragmentTransaction2.commit();
			Log.i(TAG,"commit");
		}
		else if(v.getId() == R.id.button_classic){
			fragmentTransaction2.replace(R.id.container, new ClassicFragment());
			fragmentTransaction2.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
			fragmentTransaction2.addToBackStack(null);
			fragmentTransaction2.commit();
		}
	}

}
