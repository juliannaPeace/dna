package com.meli.test.dna.core.domain.model;

import java.util.List;
import java.util.UUID;

public class Dna {

    private UUID id;
    private List<String> sequenceDna;
    private Boolean isSimian;

    private static final int MIN_LETTER = 4;

    public Dna() {
    }

    public Dna(List<String> sequenceDna) {
        this.sequenceDna = sequenceDna;
    }

    public Dna(UUID id, List<String> sequenceDna, Boolean isSimian) {
        this.id = id;
        this.sequenceDna = sequenceDna;
        this.isSimian = isSimian;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public List<String> getSequenceDna() {
        return sequenceDna;
    }

    public void setSequenceDna(List<String> sequenceDna) {
        this.sequenceDna = sequenceDna;
    }

    public Boolean getSimian() {
        return isSimian;
    }

    public void setSimian(Boolean simian) {
        isSimian = simian;
    }

    public Boolean verificaHorizontal(int linha, int coluna) {
        var letter = getSequenceDna().get(linha).charAt(coluna);

        if (coluna + MIN_LETTER - 1 < getSequenceDna().size()) {
            int ordem = coluna;
            int qtdeRepeticoes = 0;

            while (ordem < getSequenceDna().size()) {
                //System.out.println("(" + letter + ") = (" + getSequenceDna().get(linha).charAt(ordem) + ")");
                if (letter != getSequenceDna().get(linha).charAt(ordem)) {
                    break;
                }
                qtdeRepeticoes++;
                ordem++;
            }

            if(qtdeRepeticoes == MIN_LETTER){
                return true;
            }
        }

        return false;
    }
}
