/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import java.util.Comparator;

/**
 *
 * @author Gustavo
 */
public class ComparaAlunoPorIdadeDecrescente implements Comparator<Aluno>{

    public int compare(Aluno a1, Aluno a2) {
        return Integer.compare(a2.getIdade(), a1.getIdade());
    }
}
