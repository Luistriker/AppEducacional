package com.example.appeducacional.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appeducacional.Classes.InsereQuestoes;
import com.example.appeducacional.Classes.Questoes;
import com.example.appeducacional.R;

import java.util.List;

import static com.example.appeducacional.Activities.MainActivity.usuario;
import static com.example.appeducacional.Classes.InsereQuestoes.questoes;

public class QuizActivity extends AppCompatActivity {



    private int r=0;
    private int t=1;

    private String RespostaAluno;
    private String RespostaQuestao;

    private TextView textMostraQuestao;
    private TextView textMostraPontos;
    private TextView textMostraContQuestao;
    private TextView textMostraTimer;

    private TextView textopcaoA;
    private TextView textopcaoB;
    private TextView textopcaoC;
    private TextView textopcaoD;

    private RadioGroup rbGroup;
    private RadioButton rb1;
    private RadioButton rb2;
    private RadioButton rb3;
    private RadioButton rb4;

    private Button Confirma;

    private TextView resultado;

    private int indice = 1;
    private int x=0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        textMostraQuestao = (TextView)findViewById(R.id.questao_id);
        textMostraPontos = (TextView)findViewById(R.id.pontuacao_id);
        textMostraContQuestao = (TextView)findViewById(R.id.qtd_questoes_id);
        textMostraTimer = (TextView)findViewById(R.id.timer_id);

        textopcaoA = (TextView) findViewById(R.id.letra_a_id);
        textopcaoB = (TextView) findViewById(R.id.letra_b_id);
        textopcaoC = (TextView) findViewById(R.id.letra_c_id);
        textopcaoD = (TextView) findViewById(R.id.letra_d_id);

        rbGroup = (RadioGroup)findViewById(R.id.alternativas_id);
        rb1 = (RadioButton)findViewById(R.id.opcao_a_id);
        rb2 = (RadioButton)findViewById(R.id.opcao_b_id);
        rb3 = (RadioButton)findViewById(R.id.opcao_c_id);
        rb4 = (RadioButton)findViewById(R.id.opcao_d_id);

        Confirma = (Button)findViewById(R.id.confirma_id);

        resultado = (TextView) findViewById(R.id.resposta_id);


            trocaQuestao(questoes,indice,x);


                Confirma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (rb1.isChecked() || rb2.isChecked() || rb3.isChecked() || rb4.isChecked()) {
                    if(rb1.isChecked()){
                        RespostaAluno = "a";
                        RespostaQuestao = exibeResultado(questoes,indice,x);
                        if(RespostaAluno.equals(RespostaQuestao)){
                            r = r + 10;
                            usuario.setScore(r);
                            textMostraPontos.setText(Integer.toString(r));

                        }
                        indice++;
                        x++;
                        t++;
                        trocaQuestao(questoes,indice,x);
                        textMostraContQuestao.setText(Integer.toString(t));

                    }else if(rb2.isChecked()){
                        RespostaAluno = "b";
                        RespostaQuestao = exibeResultado(questoes,indice,x);
                        if(RespostaAluno.equals(RespostaQuestao)){
                            r = r + 10;

                            usuario.setScore(r);
                            textMostraPontos.setText(Integer.toString(r));
                        }
                        indice++;
                        x++;
                        t++;
                        trocaQuestao(questoes,indice,x);
                        textMostraContQuestao.setText(Integer.toString(t));

                    }else if(rb3.isChecked()){
                        RespostaAluno = "c";
                        RespostaQuestao = exibeResultado(questoes,indice,x);
                        if(RespostaAluno.equals(RespostaQuestao)){
                            r = r + 10;

                            usuario.setScore(r);
                            textMostraPontos.setText(Integer.toString(r));
                        }
                        indice++;
                        x++;
                        t++;
                        trocaQuestao(questoes,indice,x);
                        textMostraContQuestao.setText(Integer.toString(t));

                    }else{
                        RespostaAluno = "d";
                        RespostaQuestao = exibeResultado(questoes,indice,x);
                        if(RespostaAluno.equals(RespostaQuestao)){
                            r = r + 10;

                            usuario.setScore(r);
                            textMostraPontos.setText(Integer.toString(r));
                        }
                        indice++;
                        x++;
                        t++;
                        trocaQuestao(questoes,indice,x);
                        textMostraContQuestao.setText(Integer.toString(t));
                    }

                }else {
                    Toast.makeText(QuizActivity.this,"Proxima Questão",Toast.LENGTH_SHORT).show();
                }

                if(t == 5 ){
                    Toast.makeText(QuizActivity.this,"Sua Pontuação final:" +usuario.getScore(), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(QuizActivity.this, MenuAlunoActivity.class);
                    startActivity(intent);
                }


            }
        });


    }





    private void trocaQuestao(List<Questoes> quest,int indicideQuestao,int x ){
        Questoes q;
        int i = x;
        for(i=x;i<indicideQuestao;i++){
            q = quest.get(i);
            textMostraQuestao.setText(q.getQuestao());
            textopcaoA.setText(q.getOpcao1());
            textopcaoB.setText(q.getOpcao2());
            textopcaoC.setText(q.getOpcao3());
            textopcaoD.setText(q.getOpcao4());
        }
    }
    private String exibeResultado(List<Questoes> quest,int indicideQuestao,int x){
        Questoes q;
        String r = null;
        int i = x;
        for(i=x;i<indicideQuestao;i++){
            q = quest.get(i);
            resultado.setText(q.getResposta());
            r = resultado.toString();
        }
        return r;
    }



}



