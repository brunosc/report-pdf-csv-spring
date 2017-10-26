package br.com.generator.genpdfcsv.dao;

import br.com.generator.genpdfcsv.domain.NotaFiscal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NotaFiscalDAO {

    private final static int TOTAL = 200;
    private final static List<NotaFiscal> list;

    static {
        list = new ArrayList<NotaFiscal>();

        for (int i=1; i<=TOTAL; i++)

            list.add(new NotaFiscal(i+100000, "Nota "+i, "Empresa "+i, "Complementooooooooooooooooooooooooo "+i,100.0 * i));
    }

    public static List<NotaFiscal> findAll() {
        return Collections.unmodifiableList(list);
    }

}
