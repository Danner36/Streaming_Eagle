package com.example.streamingeagle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;


public class TV_Layout extends AppCompatActivity {

    // Holds the url and channel name. Manually populated. Called during button creation.
    String[][] chan_array = new String[34][2];

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.channel_layout);

        // Populate channel array.
        populate_channel_array();

        // Holds previous left and right button id. Used to place new button underneath
        // the previous on that side.
        int prev_left = 0;
        int prev_right = 0;
        // Looks are url array and creates button for each link.
        // Starts @ 1 to give first button a valid referral id.
        for (int i = 1; i < chan_array.length; i++)
        {
            // Get relative layout
            RelativeLayout layout = (RelativeLayout) findViewById(R.id.relativeLayout);
            // Define its placement parameters
            RelativeLayout.LayoutParams placement = new RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.WRAP_CONTENT,
                    RelativeLayout.LayoutParams.WRAP_CONTENT);

            Button myButton = new Button(this);
            myButton.setText(chan_array[i][0]);
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

            myButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view)
                {
                    Intent intent = new Intent(TV_Layout.this, Video_Player.class);
                    intent.putExtra("video_url", chan_array[myButton.getId()][1]);
                    startActivity(intent);
                }
            });
        }
    }

    // Manually populate channels and urls.
    public void populate_channel_array()
    {
        

    }
}
