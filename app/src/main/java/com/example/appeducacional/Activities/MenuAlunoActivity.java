package com.example.appeducacional.Activities;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.example.appeducacional.Classes.Usuarios;
import com.example.appeducacional.R;
import com.google.firebase.auth.FirebaseAuth;

import static com.example.appeducacional.R.menu.activity_menu_aluno;

public class MenuAlunoActivity extends AppCompatActivity {

    // objeto do tipo autenticação
    private FirebaseAuth autenticacao;


    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_menu_aluno);

        //pega a instância do usuário
        autenticacao = FirebaseAuth.getInstance();

    }

    //Ativa a tela de menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(activity_menu_aluno,menu);
        return true;
    }

    //Chama uma nova tela dependendo de qual opção de menu o usuário escolheu
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //pega a o id da opção que o usuário excolheu e verifica
        int id = item.getItemId();
        //chama tela de informações sobre o conteudo
        if(id == R.id.acao_informacao_id){
            Intent intent = new Intent(MenuAlunoActivity.this,InformacoesActivity.class);
            startActivity(intent);
        //chama tela do quiz para os alunos
        }else if(id == R.id.acao_jogar_id){
            Intent intent = new Intent(MenuAlunoActivity.this,QuizActivity.class);
            startActivity(intent);
        //Chama a função para deslogar da conta
        }else if(id == R.id.acao_sair_id){
            Intent intent = new Intent(MenuAlunoActivity.this,MainActivity.class);
            deslogarUsuario(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    //Função para deslogar o usuário
    private void deslogarUsuario(Intent intent){
            autenticacao.signOut();
            AbrirNovaActivity(intent);
            finish();
    }
    //Função genérica para estartar uma nova activity
    public void AbrirNovaActivity(Intent intent){
        startActivity(intent);
    }

}
