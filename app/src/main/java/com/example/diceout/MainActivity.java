package com.example.diceout;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    // Field to hold the roll result text
    TextView rollResult;

    //Field to update score
    TextView scoreView;

    // Field to hold the roll button
    Button rollButton;

    //Field to hold the score
    int score;

    // Field to hold the random seed
    Random rand;

    // Field to hold the die value
    int die1;
    int die2;
    int die3;


    // Array list to hold all dice values
    ArrayList<Integer> dice;

    // ArrayList to hold all dice images
    ArrayList<ImageView> diceImageViews;

    int rollCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        // Set initial score to equal to zero
        score = 0;
        scoreView = (TextView) findViewById(R.id.score);
        rand = new Random();
        rollResult = (TextView) findViewById(R.id.rollResult);
        rollButton = (Button) findViewById(R.id.rollButton);
        rollCount = 0;

       dice = new ArrayList<>();

        ImageView dice1Image =(ImageView) findViewById(R.id.dieImage);
        ImageView dice2Image =(ImageView) findViewById(R.id.die2Image);
        ImageView dice3Image =(ImageView) findViewById(R.id.die3Image);

        diceImageViews = new ArrayList<>();
        diceImageViews.add(dice1Image);
        diceImageViews.add(dice2Image);
        diceImageViews.add(dice3Image);

        // Create greeting
        Toast.makeText(getApplicationContext(), "Welcome to Dice Out !", Toast.LENGTH_LONG).show();

    }

    public void rollDice(View v){
        if (this.rollCount == 0) rollButton.setText("Roll");

        // Roll die
        die1 = rand.nextInt(6) + 1;
        die2 = rand.nextInt(6) + 1;
        die3 = rand.nextInt(6) + 1;

        dice.clear();
        dice.add(die1);
        dice.add(die2);
        dice.add(die3);
        String msg = "You rolled " + die1 + ", " + die2 + " and " + die3;

        for(int dieOfSet = 0; dieOfSet < 3; dieOfSet++){
            String imageName = "die_" + dice.get(dieOfSet) + ".png";

            try{
                InputStream stream = getAssets().open(imageName);
                Drawable d = Drawable.createFromStream(stream, null);
                diceImageViews.get(dieOfSet).setImageDrawable(d);



            }
            catch (IOException e){
                e.printStackTrace();
            }

        }

      // Toast.makeText(getApplicationContext(), "num gen is" + die1, Toast.LENGTH_LONG).show();

        this.score = this.score + die1 + die2 + die3;
        rollCount++;


      scoreView.setText("Score: " + this.score);
        rollResult.setText(msg);

       if(score >= 50){

            scoreView.setText("GAMEOVER! It took you " + rollCount + " rolls to reach 50 points. Ya suck lmao fuck out of here little bitch");
            rollButton.setText("Restart");
            score = 0;
            rollCount = 0;
        }

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
