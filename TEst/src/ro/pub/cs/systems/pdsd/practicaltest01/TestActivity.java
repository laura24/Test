package ro.pub.cs.systems.pdsd.practicaltest01;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class TestActivity extends Activity {
	
	private ButtonClickListener buttonClickListener = new ButtonClickListener();
	private final static int SECONDARY_ACTIVITY_REQUEST_CODE = 1;
	
	private class ButtonClickListener implements Button.OnClickListener {	

		@Override
		@SuppressWarnings("all")
		public void onClick(View view) {
			EditText text1 = (EditText)findViewById(R.id.text1);
			EditText text2 = (EditText)findViewById(R.id.text2);
			int myNum = 0;
			
			if (getResources().getString(R.string.text1).equals(((Button)view).getText().toString())) {
				try {
				    myNum = Integer.parseInt(text1.getText().toString());
				} catch(NumberFormatException nfe) {
					
				}
				myNum = myNum + 1;
				text1.setText(Integer.toString(myNum));
		}	else
			if (getResources().getString(R.string.text2).equals(((Button)view).getText().toString())) {
				try {
				    myNum = Integer.parseInt(text2.getText().toString());
				} catch(NumberFormatException nfe) {
					
				}
				myNum = myNum + 1;
				text2.setText(Integer.toString(myNum));
		}	else {
			EditText leftEditText = (EditText)TestActivity.this.findViewById(R.id.button1);
		      EditText rightEditText = (EditText)TestActivity.this.findViewById(R.id.button2);
		      switch(view.getId()) {
		        case R.id.navigate_to_secondary_activity_button:
		        	Intent intent = new Intent("ro.pub.cs.systems.pdsd.intent.action.Secondary");
		            intent.putExtra("number_of_clicks",String.valueOf(Integer.parseInt(leftEditText.getText().toString())
		                + Integer.parseInt(rightEditText.getText().toString())
		              ));
		          startActivityForResult(intent, SECONDARY_ACTIVITY_REQUEST_CODE);
		          break;
		          }
		}
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_test);
		
		Button press = (Button)findViewById(R.id.button1);
		press.setOnClickListener(buttonClickListener);
		press = (Button)findViewById(R.id.button2);
		press.setOnClickListener(buttonClickListener);
		press = (Button)findViewById(R.id.navigate_to_secondary_activity_button);
		press.setOnClickListener(buttonClickListener);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.test, menu);
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
	
	@Override
	  public void onActivityResult(int requestCode, int resultCode, Intent intent) {
	    Toast.makeText(this, "The activity returned with result "+resultCode, Toast.LENGTH_LONG).show();
	  }
}
