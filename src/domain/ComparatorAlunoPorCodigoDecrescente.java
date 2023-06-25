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
public class ComparatorAlunoPorCodigoDecrescente implements Comparator<Aluno>{

    public int compare(Aluno a1, Aluno a2) {
        return (-1) * a1.getCodigo().compareTo(a2.getCodigo());
    }

}
