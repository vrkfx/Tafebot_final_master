package com.example.tafebotappfinalversion;

import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.util.UUID;

@SuppressWarnings("unchecked")

public class ServoControlMain extends AppCompatActivity {

    Button servo1Btn1, servo1Btn2, servo2Btn1, servo2Btn2, servo3Btn1, servo3Btn2, servo4Btn1, servo4Btn2, disconnect;

    String address = null;
    private boolean BtConnected = false;
    BluetoothAdapter btAdapter = null;
    private ProgressDialog progress;
    BluetoothSocket btSocket = null;

    //SPP UUID. Look for it
    static final UUID myUUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_page);

        Intent intent = getIntent();
        address = intent.getStringExtra(BluetoothConnActivity.EXTRA_ADDRESS);


        //button's id and color
        servo1Btn1 = (Button)findViewById(R.id.servo1ButtonOpen);
        servo1Btn1.setBackgroundColor(Color.GRAY);
        servo1Btn1.setTextColor(Color.BLACK);

        servo1Btn2 = (Button)findViewById(R.id.servo1ButtonClose);
        servo1Btn2.setBackgroundColor(Color.GRAY);

        servo2Btn1 = (Button)findViewById(R.id.servo2ButtonUp);
        servo2Btn1.setBackgroundColor(Color.GRAY);
        servo2Btn1.setTextColor(Color.BLACK);

        servo2Btn2 = (Button)findViewById(R.id.servo2ButtonClose);
        servo2Btn2.setBackgroundColor(Color.GRAY);

        servo3Btn1 = (Button)findViewById(R.id.servo3ButtonUp);
        servo3Btn1.setBackgroundColor(Color.GRAY);
        servo3Btn1.setTextColor(Color.BLACK);

        servo3Btn2 = (Button)findViewById(R.id.servo3ButtonDown);
        servo3Btn2.setBackgroundColor(Color.GRAY);

        servo4Btn1 = (Button)findViewById(R.id.servo4ButtonLeft);
        servo4Btn1.setBackgroundColor(Color.GRAY);

        servo4Btn2 = (Button)findViewById(R.id.servo4ButtonRight);
        servo4Btn2.setBackgroundColor(Color.GRAY);
        servo4Btn2.setTextColor(Color.BLACK);

        disconnect = (Button)findViewById(R.id.disconnectButton);

        //Call the class to connect
        new ConnectBT().execute();

        //commands to be sent to bluetooth
        servo1Btn1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                openGripper();
            }
        });

        servo1Btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                closeGripper();
            }
        });

        servo2Btn1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                moveFront();
            }
        });

        servo2Btn2.setOnClickListener (new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {

                moveback();
            }


        });

        servo3Btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                moveUp();
            }
        });

        servo3Btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                moveDown();
            }
        });

        servo4Btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                left();
            }
        });

        servo4Btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                right();
            }
        });

        disconnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                disconnectBluetooth();
            }
        });

    }

    private void disconnectBluetooth()
    {
        //If the btSocket is busy
        if (btSocket!=null)
        {
            try
            {
                //close connection
                btSocket.close();
            }
            catch (IOException e)
            {
                text("Error");
            }
        }
        //return to the first layout
        finish();

    }

    // Show Toast message
    private void text(String text)
    {
        Toast.makeText(getApplicationContext(),text,Toast.LENGTH_LONG).show();
    }

    private void closeGripper()
    {
        if (btSocket!=null)
        {
            try
            {
                btSocket.getOutputStream().write("CLOSE".toString().getBytes());
            }
            catch (IOException e)
            {
                text("Error....failed to close");
            }
        }
    }

    private void openGripper()
    {
        if (btSocket!=null)
        {
            try
            {
                btSocket.getOutputStream().write("OPEN".toString().getBytes());
            }
            catch (IOException e)
            {
                text("Error....failed to open");
            }
        }
    }

    private void moveFront()
    {
        if (btSocket!=null)
        {
            try
            {
                btSocket.getOutputStream().write("BACK".toString().getBytes());
            }
            catch (IOException e)
            {
                text("Error....failed to move front");
            }
        }
    }

    private void moveback()
    {
        if (btSocket!=null)
        {
            try
            {
                btSocket.getOutputStream().write("FRONT".toString().getBytes());
            }
            catch (IOException e)
            {
                text("Error....failed to back");
            }
        }
    }

    private void moveUp()
    {
        if (btSocket!=null)
        {
            try
            {
                btSocket.getOutputStream().write("DOWN".toString().getBytes());
            }
            catch (IOException e)
            {
                text("Error....failed to move up");
            }
        }
    }

    private void moveDown()
    {
        if (btSocket!=null)
        {
            try
            {
                btSocket.getOutputStream().write("UP".toString().getBytes());
            }
            catch (IOException e)
            {
                text("Error....failed to move down");
            }
        }
    }

    private void left()
    {
        if (btSocket!=null)
        {
            try
            {
                btSocket.getOutputStream().write("LEFT".toString().getBytes());
            }
            catch (IOException e)
            {
                text("Error....failed to move left");
            }
        }
    }

    private void right()
    {
        if (btSocket!=null)
        {
            try
            {
                btSocket.getOutputStream().write("RIGHT".toString().getBytes());
            }
            catch (IOException e)
            {
                text("Error....failed to move right");
            }
        }
    }

    private class  ConnectBT extends AsyncTask<Void, Void, Void>  // UI thread
    {
        private boolean ConnectSuccess = true; //if it's here, it's almost connected

        @Override
        protected void onPreExecute()
        {
            progress = ProgressDialog.show(ServoControlMain.this, "Connecting to Bluetooth...", "Please wait!!!");  //show a progress dialog
        }

        //Connection  done in background
        @Override
        protected Void doInBackground(Void... devices)
        {
            try
            {
                if (btSocket == null || !BtConnected)
                {
                    //get the mobile bluetooth device
                    btAdapter = BluetoothAdapter.getDefaultAdapter();
                    //connects to the device's address and checks if it's available
                    BluetoothDevice btconnection = btAdapter.getRemoteDevice(address);
                    //create a connection
                    btSocket = btconnection.createInsecureRfcommSocketToServiceRecord(myUUID);
                    BluetoothAdapter.getDefaultAdapter().cancelDiscovery();
                    //start connection
                    btSocket.connect();
                }
            }
            catch (IOException e)
            {
                //if the try failed, you can check the exception here
                ConnectSuccess = false;
            }
            return null;
        }

        //after the doInBackground, it checks if everything went fine
        @Override
        protected void onPostExecute(Void result)
        {
            super.onPostExecute(result);

            if (!ConnectSuccess)
            {
                text("Bluetooth Connection Failed...Please try again !!");
                finish();
            }
            else
            {
                text(" Bluetooth is now Connected.");
                BtConnected = true;
            }
            progress.dismiss();
        }
    }

}
