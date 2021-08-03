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

    public int runHorizontally(int line, int column) {
        var letter = getSequenceDna().get(line).charAt(column);

        if (column + MIN_LETTER - 1 < getSequenceDna().size()) {
            int order = column;
            int repetitions = 0;

            while (order < getSequenceDna().size()) {
                if (letter != getSequenceDna().get(line).charAt(order)) {
                    break;
                }
                repetitions++;
                order++;
            }

            if (repetitions == MIN_LETTER) {
                counterDnaSimian++;
            }
        }

        return counterDnaSimian;
    }

    public int runVertically(int line, int column) {
        var letter = getSequenceDna().get(column).charAt(line);

        if (column + MIN_LETTER - 1 < getSequenceDna().size()) {
            int order = column;
            int repetitions = 0;

            while (order < getSequenceDna().size()) {
                if (letter != getSequenceDna().get(order).charAt(line)) {
                    break;
                }
                repetitions++;
                order++;
            }

            if (repetitions == MIN_LETTER) {
                counterDnaSimian++;
            }
        }

        return counterDnaSimian;
    }

    public int runDiagonalRigth(int line, int column) {
        var letter = getSequenceDna().get(line).charAt(column);

        if (column + MIN_LETTER - 1 < getSequenceDna().size()) {
            int orderColumn = column;
            int orderLine = line;
            int repetitions = 0;

            while ((orderColumn) < getSequenceDna().size() && (orderLine) < getSequenceDna().size()) {
                if (letter != getSequenceDna().get(orderLine).charAt(orderColumn)) {
                    break;
                }
                repetitions++;
                orderColumn++;
                orderLine++;
            }

            if (repetitions == MIN_LETTER) {
                counterDnaSimian++;
            }
        }

        return counterDnaSimian;
    }

    public int runDiagonalLeft(int line, int column) {
        var letter = getSequenceDna().get(line).charAt(column);

        if (line + MIN_LETTER - 1 < getSequenceDna().size()) {
            int orderColumn = column;
            int orderLine = line;
            int repetitions = 0;

            while ((orderColumn) >= 0 && (orderColumn) < getSequenceDna().size() && (orderLine) < getSequenceDna().size()) {
                if (letter != getSequenceDna().get(orderLine).charAt(orderColumn)) {
                    break;
                }
                repetitions++;
                orderColumn--;
                orderLine++;
            }

            if (repetitions == MIN_LETTER) {
                counterDnaSimian++;
            }
        }

        return counterDnaSimian;
    }
}
