package com.example.appeducacional.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.appeducacional.R;

public class MenuQuizActivity extends AppCompatActivity {
    private Button BotaoComecar;
    private Button Voltar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_quiz);

        BotaoComecar = (Button) findViewById(R.id.acao_iniciar_id);
        Voltar = (Button) findViewById(R.id.acao_voltar_id);

        BotaoComecar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuQuizActivity.this, QuizActivity.class);
                AbrirNovaActivity(intent);


            }
        });

        Voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent intent = new Intent(MenuQuizActivity.this, MenuAlunoActivity.class);
                AbrirNovaActivity(intent);
            }
        });



    }
    //Função genérica para estartar uma nova activity
    public void AbrirNovaActivity(Intent intent){
        startActivity(intent);
    }

}
