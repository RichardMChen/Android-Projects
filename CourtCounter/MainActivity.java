package com.example.android.courtcounter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int scoreTeamA = 0;
    int scoreTeamB = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * Displays the given scoreTeamA for Team A.
     */
    public void displayForTeamA(int score) {
        TextView scoreView = (TextView) findViewById(R.id.team_a_score);
        scoreView.setText(String.valueOf(score));
    }

    /**
     * Displays the given score for Team B.
     */
    public void displayForTeamB(int score) {
        TextView scoreView = (TextView) findViewById(R.id.team_b_score);
        scoreView.setText(String.valueOf(score));
    }

    public void addToScoreTeamA(View view)
    {
        switch (view.getId())
        {
            /* Increase score for Team A by 3 points. */
            case R.id.add_3_points_Team_A:
                scoreTeamA += 3;
                displayForTeamA(scoreTeamA);
                break;

            /* Increase score for Team A by 2 points. */
            case R.id.add_2_points_Team_A:
                scoreTeamA += 2;
                displayForTeamA(scoreTeamA);
                break;

            /* Increase score for Team A by 1 point. */
            case R.id.add_1_points_Team_A:
                scoreTeamA += 1;
                displayForTeamA(scoreTeamA);
                break;
        }
    }

    public void addToScoreTeamB(View view)
    {
        switch (view.getId())
        {
            /* Increase score for Team A by 3 points. */
            case R.id.add_3_points_Team_B:
                scoreTeamB += 3;
                displayForTeamB(scoreTeamB);
                break;

            /* Increase score for Team A by 2 points. */
            case R.id.add_2_points_Team_B:
                scoreTeamB += 2;
                displayForTeamB(scoreTeamB);
                break;

            /* Increase score for Team A by 1 point. */
            case R.id.add_1_points_Team_B:
                scoreTeamB += 1;
                displayForTeamB(scoreTeamB);
                break;
        }
    }

    public void resetScore(View view)
    {
        scoreTeamA = 0;
        scoreTeamB = 0;
        displayForTeamA(scoreTeamA);
        displayForTeamB(scoreTeamB);
    }

}
