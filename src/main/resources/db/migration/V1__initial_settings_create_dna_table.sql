CREATE TABLE dna
(
    id           uuid PRIMARY KEY,
    sequence_dna jsonb NOT NULL,
    is_simian    boolean   NOT NULL
);

CREATE UNIQUE INDEX sequence_dna_unique_idx
    on public.dna (sequence_dna);