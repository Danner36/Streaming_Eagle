package com.example.streamingeagle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;


public class TV_Layout extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.channel_layout);

        // Connect to the github csv file containing known channel urls.
        try
        {

            URL oracle = new URL("https://raw.githubusercontent.com/Danner36/Streaming_Eagle/master/stream_info.csv");
            BufferedReader input = new BufferedReader(new InputStreamReader(oracle.openStream()));

            // Start at index 1 so button "0" has a valid id to reference.
            int i = 1;
            // Holds previous left and right button id. Used to place new button underneath
            // the previous on that side.
            int prev_left = 0;
            int prev_right = 0;
            // Holds the whole row of csv data. Not yet split.
            String csv_line = "";

            // Cycle until csv file has no more data to read.
            while ((csv_line = input.readLine()) != null) {
                // Skips comment lines in the file.
                if(!csv_line.contains("//")){
                    continue;
                }
                // Splits csv into two parts. The channel name (index 0) and url (index 1).
                String[] csv_row = csv_line.split(",");

                // Get relative layout
                RelativeLayout layout = (RelativeLayout) findViewById(R.id.relativeLayout);
                // Define its placement parameters
                RelativeLayout.LayoutParams placement = new RelativeLayout.LayoutParams(
                        RelativeLayout.LayoutParams.WRAP_CONTENT,
                        RelativeLayout.LayoutParams.WRAP_CONTENT);

                Button myButton = new Button(this);
                myButton.setText(csv_row[0]);
                myButton.setId(i);

                // First on left side.
                if (i == 1)
                {
                    placement.addRule(RelativeLayout.LEFT_OF, R.id.strut);
                    placement.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
                    placement.addRule(RelativeLayout.BELOW, prev_left);
                    prev_left = myButton.getId();
                }
                // First on right side.
                else if (i == 2)
                {
                    placement.addRule(RelativeLayout.RIGHT_OF, R.id.strut);
                    placement.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
                    placement.addRule(RelativeLayout.BELOW, prev_right);
                    prev_right = myButton.getId();
                }
                // Not first two buttons. Add below on the left side.
                else if((i > 2) && (i % 2 == 1))
                {
                    placement.addRule(RelativeLayout.LEFT_OF, R.id.strut);
                    placement.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
                    placement.addRule(RelativeLayout.BELOW, prev_left);
                    prev_left = myButton.getId();
                }
                // Not first two buttons. Add below on the right side.
                else if((i > 2) && (i % 2 == 0))
                {
                    placement.addRule(RelativeLayout.RIGHT_OF, R.id.strut);
                    placement.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
                    placement.addRule(RelativeLayout.BELOW, prev_right);
                    prev_right = myButton.getId();
                }
                // Assign layout parameters to button
                myButton.setLayoutParams(placement);
                // Add button to relative layout.
                layout.addView(myButton);

                i++;
                // When button is clicked, a new webview activity will start with the passed in url.
                myButton.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view)
                    {
                        Intent intent = new Intent(TV_Layout.this, Video_Player.class);
                        intent.putExtra("video_url", csv_row[1]);
                        startActivity(intent);
                    }
                });
            }
        }
        // Catch error in loading url.
        catch (Exception e)
        {
            System.out.print(e);
        }
    }
}
