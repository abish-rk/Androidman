package com.example.lesson3;

import android.support.v7.app.ActionBarActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;


public class Lesson extends ActionBarActivity {
	
	private static final String TOTAL_BILL = "TOTAL_BILL";
	private static final String CURRENT_TIP = "CURRENT_TIP";
	private static final String BILL_WITHOUT_TIP = "BILL_WITHOUT_TIP";

	private double billBeforeTip;
	private double tipAmount;
	private double finalBill;
	
	EditText billBeforeTipET;
	EditText tipAmountET;
	EditText finalBillET;
	
	SeekBar tipSeekBarr;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson);
        
        if(savedInstanceState == null){
        	billBeforeTip = 0.0;
        	tipAmount = .15;
        	finalBill = 0.0;
        } else {
        	billBeforeTip = savedInstanceState.getDouble(BILL_WITHOUT_TIP);
        	tipAmount = savedInstanceState.getDouble(CURRENT_TIP);
        	finalBill = savedInstanceState.getDouble(TOTAL_BILL);
        }
        
        billBeforeTipET = (EditText) findViewById(R.id.billEditText);
    	tipAmountET = (EditText) findViewById(R.id.tipEditText);
    	finalBillET = (EditText) findViewById(R.id.finalbillEditText);
    	
    	tipSeekBarr = (SeekBar) findViewById(R.id.changeTipSeekBar);
    	tipSeekBarr.setOnSeekBarChangeListener(tipSeekBarListener);
    	
    	billBeforeTipET.addTextChangedListener(billBeforeTipListener);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.lesson, menu);
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
    
    private TextWatcher billBeforeTipListener = new TextWatcher() {
		
		@Override
		public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
			try{
				billBeforeTip = Double.parseDouble(arg0.toString());
			} catch (NumberFormatException e) {
				billBeforeTip = 0.0;
			}
			
			updateTipAndFinalBill();
		}
		
		@Override
		public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
				int arg3) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void afterTextChanged(Editable arg0) {
			// TODO Auto-generated method stub
			
		}
	};
	
	private void updateTipAndFinalBill() {
		
		double tipAmount = Double.parseDouble(tipAmountET.getText().toString());
		double finallBill = billBeforeTip + (billBeforeTip * tipAmount);
		finalBillET.setText(String.format("%.02f", finallBill));
	}
	
	protected void onSaveInstanceState(Bundle outState){
		
		super.onSaveInstanceState(outState);
		
		outState.putDouble(TOTAL_BILL, finalBill);
		outState.putDouble(CURRENT_TIP, tipAmount);
		outState.putDouble(BILL_WITHOUT_TIP, billBeforeTip);
	}
	
	private OnSeekBarChangeListener tipSeekBarListener = new OnSeekBarChangeListener() {
		
		@Override
		public void onStopTrackingTouch(SeekBar seekBar) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void onStartTrackingTouch(SeekBar seekBar) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void onProgressChanged(SeekBar seekBar, int progress,
				boolean fromUser) {
			tipAmount = ( tipSeekBarr.getProgress() ) * 0.01;
			tipAmountET.setText(String.format("%.02f", tipAmount));
			
			updateTipAndFinalBill();
		}
	};
}
