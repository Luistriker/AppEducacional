package com.example.appeducacional.DAO;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ConfiguracaoFireBase {

    private static DatabaseReference ReferenciaFirebase;
    private static FirebaseAuth autenticacao;

    public static  DatabaseReference getFirebase(){
        if(ReferenciaFirebase == null){
            ReferenciaFirebase = FirebaseDatabase.getInstance().getReference();
        }
        return ReferenciaFirebase;
    }

    //Verifica se a autenticação está nula
    public static FirebaseAuth getFirebaseAuth(){
        if(autenticacao == null){
            autenticacao = FirebaseAuth.getInstance();
        }
        return autenticacao;
    }

}
