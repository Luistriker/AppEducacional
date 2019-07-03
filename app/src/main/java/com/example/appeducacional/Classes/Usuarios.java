package com.example.appeducacional.Classes;

import com.google.firebase.database.Exclude;

public class Usuarios {


    private String Nome;
    private String Turma;
    private String Email;
    private String Senha;
    private int Score=0;



    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        Nome = nome;
    }

    public String getTurma() {
        return Turma;
    }

    public void setTurma(String turma) {
        Turma = turma;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getSenha() {
        return Senha;
    }

    public void setSenha(String senha) {
        Senha = senha;
    }

    public int getScore() {
        return Score;
    }

    public void setScore(int score) {
        Score = score;
    }










}
