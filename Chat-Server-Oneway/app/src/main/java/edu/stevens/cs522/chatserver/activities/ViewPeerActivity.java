package edu.stevens.cs522.chatserver.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import edu.stevens.cs522.base.DateUtils;
import edu.stevens.cs522.base.InetAddressUtils;
import edu.stevens.cs522.chatserver.R;
import edu.stevens.cs522.chatserver.entities.Peer;

/**
 * Created by dduggan.
 */

public class ViewPeerActivity extends Activity {

    public static final String PEER_KEY = "peer";
    TextView txtUsername, txtTimestamp, txtLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_peer);

        //grabs the peer from the intent
        Peer peer = getIntent().getParcelableExtra(PEER_KEY);
        if (peer == null) {
            throw new IllegalArgumentException("Expected peer as intent extra");
        }

        // TODO Set the fields of the UI

        txtLocation = findViewById(R.id.view_location);
        txtTimestamp = findViewById(R.id.view_timestamp);
        txtUsername = findViewById(R.id.view_user_name);

        String stringFromStringsFile = getString(R.string.view_user_name);
        String userNameText = String.format( stringFromStringsFile , peer.name  );
        txtUsername.setText(userNameText);

        txtLocation.setText(  String.format(  getString(R.string.view_location), peer.latitude, peer.longitude   )   );
        String formattedTime = formatTimestamp(peer.timestamp);
        txtTimestamp.setText(  String.format( getString(R.string.view_timestamp), formattedTime  )  );

    }

    private static String formatTimestamp(Date timestamp) {
        LocalDateTime dateTime = timestamp.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        return dateTime.format(formatter);
    }

}
