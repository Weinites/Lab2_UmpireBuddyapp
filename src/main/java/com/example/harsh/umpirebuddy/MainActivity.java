package com.example.harsh.umpirebuddy;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

      Button strikebtn;
      Button ballbtn;
      TextView striketv;
     TextView  balltv;
     int ballCnt;
     int strikeCnt;
    public static final String MyPREFERENCES = "UmpirePrefs" ;
    public static final String BallCount = "BallCount";
    public static final String StrikeCount = "StrikeCount";

    SharedPreferences sharedpreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_FULL_SENSOR);
         sharedpreferences = getApplicationContext().getSharedPreferences("UmpirePrefs", 0);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        ballCnt= sharedpreferences.getInt(BallCount, 0);
        strikeCnt= sharedpreferences.getInt(StrikeCount,0);
        strikebtn=(Button)findViewById(R.id.btnStrike);
        ballbtn=(Button)findViewById(R.id.btnBall);
        balltv=(TextView)findViewById(R.id.tvBallCnt);
        striketv=(TextView)findViewById(R.id.tvStrikeCnt);
        balltv.setText(Integer.toString(ballCnt));
        striketv.setText(Integer.toString(strikeCnt));
        registerForContextMenu(getWindow().getDecorView());
        ballbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               ballCnt= Integer.parseInt(balltv.getText().toString());
                ballCnt=ballCnt+1;
               balltv.setText(String.valueOf(ballCnt));
                strikeCnt= Integer.parseInt(striketv.getText().toString());
                SharedPreferences.Editor editor = MainActivity.this.sharedpreferences.edit();

                editor.putInt(BallCount,ballCnt);
                editor.putInt(StrikeCount, strikeCnt);

                editor.commit();


                if(ballCnt==4 && strikeCnt<4){
                    AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                    alert.setTitle("Message");
                    alert.setMessage("Walk!!");
                    alert.setPositiveButton("OK",null);
                    alert.setCancelable(false);
                    alert.show();
                }
            }
        });
        strikebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                strikeCnt= Integer.parseInt(striketv.getText().toString());
                strikeCnt=strikeCnt+1;
                striketv.setText(String.valueOf(strikeCnt));
                ballCnt= Integer.parseInt(balltv.getText().toString());
                SharedPreferences.Editor editor = MainActivity.this.sharedpreferences.edit();

                editor.putInt(BallCount,ballCnt);
                editor.putInt(StrikeCount, strikeCnt);

                editor.commit();
                if(strikeCnt==3 && ballCnt<3)
                {
                    AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                    alert.setTitle("Message");
                    alert.setMessage("Out!!");
                    alert.setPositiveButton("OK",null);
                    alert.setCancelable(false);
                    alert.show();
                }
            }
        });


    }

    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);

        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {

        //respond to menu item selection
        switch (item.getItemId()) {
            case R.id.about:
                startActivity(new Intent(this, About.class));
                return true;
            case R.id.reset:
                balltv.setText("0");
                striketv.setText("0");
                ballCnt=0;
                strikeCnt=0;
                SharedPreferences.Editor editor = sharedpreferences.edit();

                editor.putInt(BallCount, ballCnt);
                editor.putInt(StrikeCount, strikeCnt);

                editor.commit();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    }

