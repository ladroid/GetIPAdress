package com.example.lado.getipadress;

import android.content.Intent;
import android.content.pm.ShortcutInfo;
import android.content.pm.ShortcutManager;
import android.graphics.drawable.Icon;
import android.net.Uri;
import android.net.wifi.WifiManager;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.Formatter;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Arrays;
import java.util.Enumeration;

public class MainActivity extends AppCompatActivity {

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.getIP);
        Button press = (Button) findViewById(R.id.press);

        //make shortcut with ShortcutManager
        ShortcutManager shortcutManager = getSystemService(ShortcutManager.class);

        //making Intent
        Intent intent = new Intent(MainActivity.this, MainActivity.class);
        Uri uri = intent.getData();

        //show information when we pressed long on icon
        ShortcutInfo shortcut = new ShortcutInfo.Builder(this, "id1")
                .setShortLabel("Task")
                .setLongLabel("Get IP Address")
                .setIcon(Icon.createWithResource(MainActivity.this, R.drawable.ic_launcher_round))
                .setIntent(new Intent(Intent.ACTION_VIEW, uri))
                .build();

        //make array with our inforamtion
        shortcutManager.setDynamicShortcuts(Arrays.asList(shortcut));

        press.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WifiManager wifiManager = (WifiManager) getSystemService(WIFI_SERVICE);
                String ipAddress = Formatter.formatIpAddress(wifiManager.getConnectionInfo().getIpAddress());
                textView.setText("Your Device IP Address: " + ipAddress);
            }
        });
    }
}
