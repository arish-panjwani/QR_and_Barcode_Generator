package com.example.QR_and_Barcode_Generator;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class QR_Generator extends AppCompatActivity {

    private EditText edit_code;
    private Button save;
    private ImageView barCode, qrCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr_generator);

        init();
    }

    private void init() {

        edit_code = findViewById(R.id.edit_code);
        barCode = findViewById(R.id.bar_code);
        qrCode = findViewById(R.id.qr_code);
        save = findViewById(R.id.save);

        //onclick

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getCode();
            }
        });
    }

    private void getCode() {

        try {
            qrcode();
            barCode();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    //create functions for the qrcode and barcode


    MultiFormatWriter multiFormatWriter = new MultiFormatWriter();

    public void qrcode() throws WriterException {

        BitMatrix bitMatrix = multiFormatWriter.encode(edit_code.getText().toString(), BarcodeFormat.QR_CODE, 350, 300);
        BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
        Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
        //set the generated qrcode to our imageview
        qrCode.setImageBitmap(bitmap);

    }

    public void barCode() throws WriterException {
        BitMatrix bitMatrix = multiFormatWriter.encode(edit_code.getText().toString(), BarcodeFormat.CODE_128, 400, 170, null);
        BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
        Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
        //set the generated barcode to our imageview
        barCode.setImageBitmap(bitmap);
    }
}
