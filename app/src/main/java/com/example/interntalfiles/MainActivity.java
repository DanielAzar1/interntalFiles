package com.example.interntalfiles;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * @author Daniel Azar
 * @version 1.0
 * @since 2025-03-16
 */
public class MainActivity extends AppCompatActivity {
    TextView tv1;
    EditText textEnter;
    private final String FILENAME = "data.txt";
    String buffer = "";

    /**
     * @param savedInstanceState The saved state
     * Function creates the activity
     * @return void
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv1 = findViewById(R.id.TV1);
        textEnter = findViewById(R.id.textEnter);


        // Reading from the file (if it exists, otherwise create it first)
        FileInputStream fIS = null;
        try {
            File file = new File(getFilesDir(), "data.txt");
            if (file.exists()) {
                fIS = openFileInput("data.txt");
                InputStreamReader iSR = new InputStreamReader(fIS);
                BufferedReader bR = new BufferedReader(iSR);
                String line = bR.readLine();
                while (line != null) {
                    buffer += line;
                    buffer += '\n';
                    line = bR.readLine();
                }
                bR.close();
                iSR.close();
                fIS.close();
            } else {
                // File does not exist, create it
                FileOutputStream fOS = openFileOutput("data.txt", MODE_PRIVATE);  // This will create the file
                OutputStreamWriter oSW = new OutputStreamWriter(fOS);
                BufferedWriter bW = new BufferedWriter(oSW);
                bW.write("");
                bW.close();
                oSW.close();
                fOS.close();
            }
        } catch (IOException e) {
            // Nothing
        }

        // Setting text
        tv1.setText(buffer);
    }

    /**
     * @param view The button pressed
     * @throws IOException If the file is not found
     * Function exits the program and saves into the file
     * @return void
     */
    public void Exit(View view) throws IOException {

        // Writing to file in append mode
        FileOutputStream fOS = openFileOutput(FILENAME,MODE_APPEND);
        OutputStreamWriter oSW = new OutputStreamWriter(fOS);
        BufferedWriter bW = new BufferedWriter(oSW);
        bW.write(textEnter.getText().toString());
        bW.write('\n');
        bW.close();
        oSW.close();
        fOS.close();

        // Closing program
        finish();

    }

    /**
     * @param view The button pressed
     * @throws IOException If the file is not found
     * Function resets the file
     * @return void
     */
    public void Reset(View view) throws IOException {

        // Deleting file content with override mode
        FileOutputStream fOS = openFileOutput(FILENAME,MODE_PRIVATE);
        OutputStreamWriter oSW = new OutputStreamWriter(fOS);
        BufferedWriter bW = new BufferedWriter(oSW);
        bW.write("");
        bW.close();
        oSW.close();
        fOS.close();

    }

    /**
     * @param view The button pressed
     * @throws IOException If the file is not found
     * The function saves the text to the file in append mode
     * @return void
     */
    public void Save(View view) throws IOException {

        // Writing to file in append mode
        FileOutputStream fOS = openFileOutput(FILENAME,MODE_APPEND);
        OutputStreamWriter oSW = new OutputStreamWriter(fOS);
        BufferedWriter bW = new BufferedWriter(oSW);
        bW.write(textEnter.getText().toString());
        bW.write('\n');
        bW.close();
        oSW.close();
        fOS.close();
    }

    /**
     * @param menu The menu
     * @return True if the menu is created
     */
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    /**
     * @param item The item that is clicked
     * @return True if the item is clicked
     */
    public boolean onOptionsItemSelected(@NonNull MenuItem item)
    {
        Intent ti = new Intent(this, Credits.class);
        startActivity(ti);
        return true;
    }
}