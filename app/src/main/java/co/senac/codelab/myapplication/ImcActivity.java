package co.senac.codelab.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.util.Printer;
import android.view.View;
import android.view.inputmethod.InputMethod;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ImcActivity extends AppCompatActivity {

    private EditText editHeight;
    private EditText editWeight;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imc);

        editHeight = findViewById(R.id.edit_imc_height);
        editWeight = findViewById(R.id.edit_imc_weight);

        Button btnSend = findViewById(R.id.btn_imc_send);

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!validate()){
                    Toast.makeText(ImcActivity.this, R.string.fields_messages, Toast.LENGTH_SHORT).show();
                    return;
                }

                String sHeight = editHeight.getText().toString();
                String sWeight = editWeight.getText().toString();

                int height = Integer.parseInt(sHeight);
                int weight = Integer.parseInt(sWeight);

                double result = calculateImc(height, weight);


                int imcResponseId = imcResult(result);

                AlertDialog dialog = new AlertDialog.Builder(ImcActivity.this)
                        .setTitle(getString(R.string.imc_response,result))
                        .setMessage(imcResponseId)
                        .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .create();
                dialog.show();
           InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
           imm.hideSoftInputFromWindow(editWeight.getWindowToken(),0);
           imm.hideSoftInputFromWindow(editHeight.getWindowToken(),0);
            }
        });
    }

    private int imcResult(double imc){
        if(imc < 15)
            return R.string.imc_severely_low_weight;
        else if ( imc < 16)
            return R.string.imc_very_low_weight;
        else if (imc < 18.5)
            return R.string.imc_low_weight;
        else if(imc < 25)
            return R.string.normal;
        else if(imc < 30)
            return R.string.imc_high_weight;
        else if (imc < 35)
            return R.string.imc_so_high_weight;
        else if (imc < 40)
            return R.string.imc_severely_high_weight;
         else
             return R.string.imc_extreme_weight;
    }

    private double calculateImc(int height, int weight){
        return weight /( ((double) height / 100) * ((double) height / 100) );
    }

    private boolean validate(){
      return (!editHeight.getText().toString().startsWith("0")
              && !editWeight.getText().toString().startsWith("0")
              && !editWeight.getText().toString().isEmpty()
              && !editHeight.getText().toString().isEmpty());

    }

}