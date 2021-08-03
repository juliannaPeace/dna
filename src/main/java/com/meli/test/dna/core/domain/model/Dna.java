package com.meli.test.dna.core.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class Dna {

    private UUID id;
    private final List<String> sequenceDna;
    private Boolean isSimian;
    private static Integer counterDnaSimian;

    private static final int MIN_LETTER = 4;

    public void verifyIsSimian() {
        counterDnaSimian = 0;
        setIsSimian(false);
        for (int line = 0; line < getSequenceDna().size(); line++) {
            for (int column = 0; column < getSequenceDna().size(); column++) {
                runHorizontally(line, column);
                runVertically(line, column);
                runDiagonalRigth(line, column);
                runDiagonalLeft(line, column);
            }
        }
        if (counterDnaSimian >= 2) {
            setIsSimian(true);
        }
    }

    public int runHorizontally(int linha, int coluna) {
        var letter = getSequenceDna().get(linha).charAt(coluna);

        if (coluna + MIN_LETTER - 1 < getSequenceDna().size()) {
            int ordem = coluna;
            int qtdeRepeticoes = 0;

            while (ordem < getSequenceDna().size()) {
                if (letter != getSequenceDna().get(linha).charAt(ordem)) {
                    break;
                }
                qtdeRepeticoes++;
                ordem++;
            }

            if (qtdeRepeticoes == MIN_LETTER) {
                counterDnaSimian++;
            }
        }

        return counterDnaSimian;
    }

    public int runVertically(int linha, int coluna) {
        var letter = getSequenceDna().get(coluna).charAt(linha);

        if (coluna + MIN_LETTER - 1 < getSequenceDna().size()) {
            int ordem = coluna;
            int qtdeRepeticoes = 0;

            while (ordem < getSequenceDna().size()) {
                if (letter != getSequenceDna().get(ordem).charAt(linha)) {
                    break;
                }
                qtdeRepeticoes++;
                ordem++;
            }

            if (qtdeRepeticoes == MIN_LETTER) {
                counterDnaSimian++;
            }
        }

        return counterDnaSimian;
    }

    public int runDiagonalRigth(int linha, int coluna) {
        var letter = getSequenceDna().get(linha).charAt(coluna);

        if (coluna + MIN_LETTER - 1 < getSequenceDna().size()) {
            int ordemColuna = coluna;
            int ordemLinha = linha;
            int qtdeRepeticoes = 0;

            while ((ordemColuna) < getSequenceDna().size() && (ordemLinha) < getSequenceDna().size()) {
                if (letter != getSequenceDna().get(ordemLinha).charAt(ordemColuna)) {
                    break;
                }
                qtdeRepeticoes++;
                ordemColuna++;
                ordemLinha++;
            }

            if (qtdeRepeticoes == MIN_LETTER) {
                counterDnaSimian++;
            }
        }

        return counterDnaSimian;
    }

    public int runDiagonalLeft(int linha, int coluna) {
        var letter = getSequenceDna().get(linha).charAt(coluna);

        if (linha + MIN_LETTER - 1 < getSequenceDna().size()) {
            int ordemColuna = coluna;
            int ordemLinha = linha;
            int qtdeRepeticoes = 0;

            while ((ordemColuna) >= 0 && (ordemColuna) < getSequenceDna().size() && (ordemLinha) < getSequenceDna().size()) {
                if (letter != getSequenceDna().get(ordemLinha).charAt(ordemColuna)) {
                    break;
                }
                qtdeRepeticoes++;
                ordemColuna--;
                ordemLinha++;
            }

            if (qtdeRepeticoes == MIN_LETTER) {
                counterDnaSimian++;
            }
        }

        return counterDnaSimian;
    }
}
