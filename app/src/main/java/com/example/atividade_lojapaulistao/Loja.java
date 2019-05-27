package com.example.atividade_lojapaulistao;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Loja extends AppCompatActivity {
    //Variaveis de preço da camisa
    Double pCamisa = 0.0;
    Double pMeia = 0.0;
    Double pCalcao = 0.0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loja);
        setValues();
    }


    public void carregarTelaInicial(View v) {
        Intent paginaInicial = new Intent(this, MainActivity.class);
        paginaInicial.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        paginaInicial.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(paginaInicial);
    }
    public void setValues(){
//      pegando o nome do time
        String nomeTime = getIntent().getStringExtra("nomeTime");
        TextView idNome = (TextView) findViewById(R.id.nomeTime);
        idNome.setText(nomeTime);

//      pegando o preço da camisa
        pCamisa = getIntent().getDoubleExtra("precoCamisa", 00.0);
        TextView idCamisa = (TextView) findViewById(R.id.precoCamisa);
        idCamisa.setText("R$ " + Double.toString(pCamisa));

//      pegando o preço da meia
        pMeia = getIntent().getDoubleExtra("precoMeia", 00.0);
        TextView idMeia = (TextView) findViewById(R.id.precoMeia);
        idMeia.setText("R$ " + Double.toString(pMeia));

//      pegando o preço do calção
        pCalcao = getIntent().getDoubleExtra("precoCalcao", 00.0);
        TextView idCalcao = (TextView) findViewById(R.id.precoCalcao);
        idCalcao.setText("R$ " + Double.toString(pCalcao));

    }


    public void calcularCompra(View v){
        Double precoFinal = this.calcularTotal();
        String msg = "Preço total da compra de: \n R$ " + precoFinal.toString();

        AlertDialog.Builder builder = new AlertDialog.Builder(Loja.this);
        LayoutInflater inflater = this.getLayoutInflater();

        View view = inflater.inflate(R.layout.dialog_msg, null);
        final TextView pfinal = view.findViewById(R.id.pFinal);
        pfinal.setText(msg);
        if(precoFinal == 0.0){
            // Botao positivo.
            builder.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(Loja.this, "Selecione a quantidade", Toast.LENGTH_SHORT).show();
                }
            });
        }else{
            // Botao positivo.
            builder.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(Loja.this, "Compra efetuada com seucesso", Toast.LENGTH_SHORT).show();
                }
            });
        }

        //  Cancelar Compra
        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(Loja.this, "Compra cancelada", Toast.LENGTH_SHORT).show();
            }
        });

        builder.setView(view);
        builder.create();
        builder.show();
    }

    public double calcularTotal(){
        EditText txtCamisa = (EditText) findViewById(R.id.qtdCamisa);
        EditText txtMeia = (EditText) findViewById(R.id.qtdMeia);
        EditText txtCalcao = (EditText) findViewById(R.id.qtdCalcao);

        //  Preço camisa
        String auxCamisa = txtCamisa.getText().toString();
        double qtdCamisa;
        double precoCamisa;
        if(txtCamisa.getText().length() == 0){
            auxCamisa = "0.0";
            qtdCamisa = Double.parseDouble(auxCamisa);
            precoCamisa = qtdCamisa * pCamisa;
        }else{
            qtdCamisa = Double.parseDouble(auxCamisa);
            precoCamisa = qtdCamisa * pCamisa;
        }

        //  Preço Meia
        String auxMeia = txtMeia.getText().toString();
        double qtdMeia;
        double precoMeia;
        if(txtMeia.getText().length() == 0){
            auxMeia = "0.0";
            qtdMeia = Double.parseDouble(auxMeia);
            precoMeia = qtdMeia* pMeia;
        }else{
            qtdMeia = Double.parseDouble(auxMeia);
            precoMeia = qtdMeia* pMeia;
        }

        //Preço do calção
        String auxCalcao = txtCalcao.getText().toString();
        double qtdCalcao;
        double precoCalcao;
        if(txtCalcao.getText().length() == 0){
            auxCalcao = "0.0";
            qtdCalcao = Double.parseDouble(auxCalcao);
            precoCalcao = qtdCalcao * pCalcao;
        }else{
            qtdCalcao = Double.parseDouble(auxCalcao);
            precoCalcao = qtdCalcao * pCalcao;
        }


        double precoFinal = precoCalcao + precoCamisa + precoMeia;
        return precoFinal;
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
    }
}
