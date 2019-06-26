package com.example.appeducacional.Classes;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.appeducacional.Activities.CadastroActivity;
import com.example.appeducacional.DAO.ConfiguracaoFireBase;
import com.example.appeducacional.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

import java.util.List;

public class InsereQuestoes extends AppCompatActivity {

    //Objeto do tipo autenticação
    private FirebaseAuth autenticacao;
    //Objetos do tipo banco de dados para armazenar informações
    public static DatabaseReference referenciaQuestoes = CadastroActivity.database.getReference("Questoes");



    public static List<Questoes> questoes;
    private Questoes q1;
    private Questoes q2;
    private Questoes q3;
    private Questoes q4;
    private Questoes q5;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
            criarQuestoes();

    }

    private void insereQuestoes( List<Questoes> q){

        for(Questoes item: q ){

            referenciaQuestoes.push().setValue(item);

        }

    }
   private void criarQuestoes(){
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

       insereQuestoes(questoes);

   }

}
