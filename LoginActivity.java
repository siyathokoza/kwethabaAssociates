package com.siyathokoza.virtual_classroom;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public abstract class LoginActivity extends ActionBarActivity {
     
	private static final String TAG  = "LoginActivity";
	private static final int REQUEST_SIGNUP = 0;

    @InjectView(R.id.editText1) EditText _emailText;
    @InjectView(R.id.input_password) EditText _passwordText;
    @InjectView(R.id.edButton) Button _loginButton;
    @InjectView(R.id.link_signup) TextView _signupLink;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		_loginButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
			login();	
			}
		});
		_signupLink.setOnClickListener(new View.OnClickListener() {	
			@Override
			public void onClick(View v) {
				// we start the signup activity
				Intent intentObject = new Intent(getApplicationContext(),RegisterActivity.class);
				startActivityForResult(intentObject,REQUEST_SIGNUP);		
			}
		});
	        	_loginButton.setEnabled(false);
	        	
	        	final ProgressDialog progress = new ProgressDialog(LoginActivity.this,R.style.AppTheme_Dark_Dialog);
	        	progress.setIndeterminate(true);
	        	progress.setMessage("Authenication");
	        	progress.show();
	        	String email = _emailText.getText().toString();
	        	String password =_passwordText.getText().toString();
	        	
	} 
	   // IMPLEMENTATION OF AUTHENICATION
	   new  android.os.Handler().postDelayed(
	       new Runnable(){
	    	   public void run(){
	    		// On complete eitheronLoginSuccess or onLoginFailed 
	    		   onLoginSucces();
	    		   ProgressDialog.dismiss();	  
	    	}
	       
			private void onLoginSucces() {
				moveTaskToBack(true);	
				finish();		
			}
	       },3000);
	   
	   public abstract void onBackPressed(); {  
		   moveTaskToBack(true);   
	   }	   
	   protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	        if (requestCode == REQUEST_SIGNUP) {
	            if (resultCode == RESULT_OK) {
	                // TODO: Implement successful signup logic here
	                // By default we just finish the Activity and log them in automatically
	                this.finish();
	            }
	        }
	    }
	
	protected void login() {
        	Log.d(TAG,"login");
        	if(!validate()){
        		onLoginFailed();
        		return;
        	}
	}
	private void onLoginFailed() {
		Toast.makeText(getBaseContext(),"Login failed",Toast.LENGTH_LONG).show();
		_loginButton.setEnabled(true);
		
	}
	private boolean validate() {
		boolean valid = true;
		String email = _emailText.getText().toString();
		String password = _passwordText.getText().toString();
		
		if(email.isEmpty()|| !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()){
			_emailText.setError("Enter a valid email address");
			valid = false;
			
		}
			else { 
				_passwordText.setError(null);
			}
			
		if (password.isEmpty() || password.length()<4 || password.length() > 10){
			_passwordText.setError("Please chose between 4 and 10 alphanumeric charactors");
			
		} else {
			
			_passwordText.setError(null);
		}
		return valid;
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.user_area) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
