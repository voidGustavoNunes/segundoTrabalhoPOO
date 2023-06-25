/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

/**
 *
 * @author Gustavo
 */

import java.util.Comparator;

public class ComparatorAlunoPorCodigoCrescente implements Comparator<Aluno>{
    public int compare(Aluno a1, Aluno a2) {
        return a1.getCodigo().compareTo(a2.getCodigo());
    }
}
