package com.example.hilos2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends Activity {
    private EditText entrada;
    private TextView salida;
    private ProgressBar loading;
    private String salida_str;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        entrada = (EditText) findViewById(R.id.entrada);
        salida = (TextView) findViewById(R.id.salida);
        loading = (ProgressBar) findViewById(R.id.progressBar);

        salida_str = "";
    }


    //Cuando se hace click en el botón ejecuta el método
    public void calcularOperacion(View view) {
        int n = Integer.parseInt(entrada.getText().toString());
        salida_str += n + "! = ";
        salida.setText(salida_str);
        //MiThread thread = new MiThread(n);
        //thread.start();
        MiTarea tarea = new MiTarea();
        tarea.execute(n);
    }


    //Calculaa el factorial de n
    public int factorial(int n) {
        int res=1;
        for (int i=1; i<=n; i++){
            res*=i;
            SystemClock.sleep(500);
        }
        return res;
    }

    //AsyncTask
    class MiTarea extends AsyncTask<Integer, Integer, Integer> {
        //Lo que pasa antes de ejecutar el hilo (inicializaciones
        protected void onPreExecute() {
            salida.setText("Acá pondré el resultado del Factorial");
            loading.setVisibility(View.VISIBLE);
        }

        //Lo que pasa en la ejecución del hilo (Este es el método que ejecuta en el execute()
        protected Integer doInBackground(Integer... n) {
            Integer resultado_factorial;
            resultado_factorial = factorial(n[0]);
            return resultado_factorial;
        }

        //Lo que se debe ejecutar cuando termina la ejecución del hilo
        protected void onPostExecute(Integer res) {
            salida_str += res + "\n";
            salida.setText(salida_str);
            loading.setVisibility(View.GONE);
        }
    }

}
