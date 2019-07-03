package com.example.appeducacional.Activities;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.appeducacional.R;
import com.google.firebase.auth.FirebaseAuth;

//import static com.example.appeducacional.Activities.MainActivity.autenticacao;


public class MenuAlunoActivity extends AppCompatActivity {

    private Button Quiz,Info,Sair,Rank;
    private FirebaseAuth autenticacao;

    // objeto do tipo autenticação




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_aluno);

        Quiz = (Button) findViewById(R.id.acao_jogar_id);
        Info = (Button) findViewById(R.id.acao_informacao_id);
        Sair = (Button) findViewById(R.id.acao_sair_id);
        Rank = (Button) findViewById(R.id.acao_ranking_id);

        //pega a instância do usuário
        autenticacao = FirebaseAuth.getInstance();

        Quiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //chama tela do quiz para os alunos
                Intent intent = new Intent(MenuAlunoActivity.this, MenuQuizActivity.class);
                startActivity(intent);
            }
        });
        Info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //chama tela de informações sobre o conteudo
                Intent intent = new Intent(MenuAlunoActivity.this, InformacoesActivity.class);
                startActivity(intent);
            }
        });
        Sair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Chama a função para deslogar da conta e voltar para tela inicial main
                Intent intent = new Intent(MenuAlunoActivity.this, MainActivity.class);
                deslogarUsuario();
                AbrirNovaActivity(intent);
            }
        });
        Rank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //chama tela de Ranking dos alunos
                Intent intent = new Intent(MenuAlunoActivity.this, RankActivity.class);
                startActivity(intent);
            }
        });
    }

    //Função para deslogar o usuário
    private void deslogarUsuario(){
        autenticacao.signOut();
        Toast.makeText(MenuAlunoActivity.this,"Usuário deslogado com sucesso",Toast.LENGTH_SHORT).show();
        finish();
    }

    //Função genérica para estartar uma nova activity
    public void AbrirNovaActivity(Intent intent){
        startActivity(intent);
    }

}
