package com.example.appeducacional.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;


import com.example.appeducacional.Classes.Questoes;


import com.example.appeducacional.R;
import com.google.firebase.database.DatabaseReference;

import java.util.List;

import static com.example.appeducacional.Activities.CadastroActivity.referenciaAluno;
import static com.example.appeducacional.Activities.MainActivity.usuario;


public class QuizActivity extends AppCompatActivity {

    private int indice = 1;
    private int x=0;

    private int r=0;
    private int t=1;

    private DatabaseReference referenciaQuestoes;
    private List<Questoes> questoes = null;

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



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        referenciaQuestoes = CadastroActivity.database.getReference("Questoes");
        criarQuestoes();

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


            trocaQuestao(indice,x);


                Confirma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (rb1.isChecked() || rb2.isChecked() || rb3.isChecked() || rb4.isChecked()) {
                    if(rb1.isChecked()){
                        RespostaAluno = "a";
                        RespostaQuestao = exibeResultado(indice,x);
                        if(RespostaAluno.equals(RespostaQuestao)){
                            r = r + 10;
                            usuario.setScore(r);
                            referenciaAluno.setValue(usuario.getScore());
                            textMostraPontos.setText(Integer.toString(r));

                        }
                        indice++;
                        x++;
                        t++;
                        trocaQuestao(indice,x);
                        textMostraContQuestao.setText(Integer.toString(t));

                    }else if(rb2.isChecked()){
                        RespostaAluno = "b";
                        RespostaQuestao = exibeResultado(indice,x);
                        if(RespostaAluno.equals(RespostaQuestao)){
                            r = r + 10;

                            usuario.setScore(r);
                            referenciaAluno.setValue(usuario.getScore());
                            textMostraPontos.setText(Integer.toString(r));
                        }
                        indice++;
                        x++;
                        t++;
                        trocaQuestao(indice,x);
                        textMostraContQuestao.setText(Integer.toString(t));

                    }else if(rb3.isChecked()){
                        RespostaAluno = "c";
                        RespostaQuestao = exibeResultado(indice,x);
                        if(RespostaAluno.equals(RespostaQuestao)){
                            r = r + 10;

                            usuario.setScore(r);
                            referenciaAluno.setValue(usuario.getScore());
                            textMostraPontos.setText(Integer.toString(r));
                        }
                        indice++;
                        x++;
                        t++;
                        trocaQuestao(indice,x);
                        textMostraContQuestao.setText(Integer.toString(t));

                    }else{
                        RespostaAluno = "d";
                        RespostaQuestao = exibeResultado(indice,x);
                        if(RespostaAluno.equals(RespostaQuestao)){
                            r = r + 10;

                            usuario.setScore(r);
                            referenciaAluno.setValue(usuario.getScore());
                            textMostraPontos.setText(Integer.toString(r));
                        }
                        indice++;
                        x++;
                        t++;
                        trocaQuestao(indice,x);
                        textMostraContQuestao.setText(Integer.toString(t));
                    }

                }else {
                    Toast.makeText(QuizActivity.this,"Escolha uma opção",Toast.LENGTH_SHORT).show();
                }

                if(t == 5 ){
                    Toast.makeText(QuizActivity.this,"Sua Pontuação final:" +usuario.getScore(), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(QuizActivity.this, MenuAlunoActivity.class);
                    startActivity(intent);
                }


            }
        });


    }





    private void trocaQuestao(int indicideQuestao,int x ){
        Questoes q;
        int i = x;
        for(i=x;i<indicideQuestao;i++){
            q = questoes.get(i);
            textMostraQuestao.setText(q.getQuestao());
            textopcaoA.setText(q.getOpcao1());
            textopcaoB.setText(q.getOpcao2());
            textopcaoC.setText(q.getOpcao3());
            textopcaoD.setText(q.getOpcao4());
        }
    }
    private String exibeResultado(int indicideQuestao,int x){
        Questoes q;
        String r = null;
        int i = x;
        for(i=x;i<indicideQuestao;i++){
            q = questoes.get(i);
            resultado.setText(q.getResposta());
            r = resultado.toString();
        }
        return r;
    }

    private void insereQuestoes( ){

        for(Questoes item: questoes ){
            int i = 1;
            referenciaQuestoes.child(Integer.toString(i)).setValue(item);
            i++;
        }

    }
    public void criarQuestoes(){


        Questoes q1;
        Questoes q2;
        Questoes q3;
        Questoes q4;
        Questoes q5;


        q1 = new Questoes();
        q1.setQuestao("1.Dentre os Subfilos de artrópodes abaixo relacionados, assinale a alternativa dos que\n" +
                "possuem indivíduos com cefalotórax e abdome:");
        q1.setOpcao1("a. Hexapoda e Myriapoda;");
        q1.setOpcao2("b. Crustacea e Chelicerata;");
        q1.setOpcao3("c. Myriapoda e Hexapoda;");
        q1.setOpcao4("d. Chelicerata e Myriapoda.");
        q1.setResposta("b");


        q2 = new Questoes();
        q2.setQuestao("2. Representam artrópodes que não possuem mandíbula e antenas:");
        q2.setOpcao1(" a. Arachnida;");
        q2.setOpcao2("b. Insecta;");
        q2.setOpcao3("c. Diplopoda;");
        q2.setOpcao4("d. Crustáceos.");
        q2.setResposta("a");


        q3 = new Questoes();
        q3.setQuestao("3. Sobre artrópodes estão corretas as alternativas:");
        q3.setOpcao1("a. Possuem representantes que não apresentam exoesqueleto;");
        q3.setOpcao2("b. Todos apresentam crescimento através de mudas ou ecdises;");
        q3.setOpcao3("c. Todos os insetos apresentam asas;");
        q3.setOpcao4("d. Chelicerata e Myriapoda.");
        q3.setResposta("b");


        q4 = new Questoes();
        q4.setQuestao("4. Assinale as alternativas que representam artrópodes que apresentam somente respiração traqueal:");
        q4.setOpcao1("a. Hexapoda;");
        q4.setOpcao2("b. Myriapoda;");
        q4.setOpcao3("c. Arachnida;");
        q4.setOpcao4("d. Crustacea.");
        q4.setResposta("a");


        q5 = new Questoes();
        q5.setQuestao("5. Sobre características comuns dos artrópodes assinale a alternativa correta:");
        q5.setOpcao1("a. Celomados, deuterostômios e triblásticos;");
        q5.setOpcao2("b. Pseudocelomados; protostômios e triblásticos;");
        q5.setOpcao3("c. Celomados, protostômios, triblásticos;");
        q5.setOpcao4("d. Simetria bilateral, protostômios e diblásticos.");
        q5.setResposta("c");


        questoes.add(q1);
        questoes.add(q2);
        questoes.add(q3);
        questoes.add(q4);
        questoes.add(q5);

        insereQuestoes();

    }

}
