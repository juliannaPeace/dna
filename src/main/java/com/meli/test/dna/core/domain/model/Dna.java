package com.meli.test.dna.core.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Dna {

    private UUID id;
    private final List<String> sequenceDna;
    private Boolean isSimian;

    private static final int MIN_LETTER = 4;

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

            if (qtdeRepeticoes == MIN_LETTER) {
                return true;
            }
        }

        return false;
    }
}
