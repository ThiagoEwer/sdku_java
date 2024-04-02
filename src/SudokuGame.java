import java.util.Random;
import java.util.Scanner;

public class SudokuGame {
    private static final int TAMANHO = 9;
    private static final int CELULA_VAZIA = 0;
    private int[][] tabuleiro;

    public SudokuGame() {
        tabuleiro = new int[TAMANHO][TAMANHO];
        inicializarTabuleiro();
    }

    private void inicializarTabuleiro() {
        gerarTabuleiroValido();
    }

    private void gerarTabuleiroValido() {
        // Implementação da geração do tabuleiro inicial válido (pode ser feito posteriormente)
        // Neste exemplo, apenas vamos preencher algumas células aleatoriamente
        Random random = new Random();
        for (int i = 0; i < TAMANHO; i++) {
            for (int j = 0; j < TAMANHO; j++) {
                if (random.nextDouble() < 0.4) { // 40% de chance de preencher a célula
                    tabuleiro[i][j] = random.nextInt(TAMANHO) + 1;
                } else {
                    tabuleiro[i][j] = CELULA_VAZIA; // Célula vazia
                }
            }
        }
    }

    private void exibirTabuleiro() {
        for (int i = 0; i < TAMANHO; i++) {
            if (i % 3 == 0 && i != 0) {
                System.out.println("---------------------");
            }
            for (int j = 0; j < TAMANHO; j++) {
                if (j % 3 == 0 && j != 0) {
                    System.out.print("| ");
                }
                if (tabuleiro[i][j] == CELULA_VAZIA) {
                    System.out.print("_ ");
                } else {
                    System.out.print(tabuleiro[i][j] + " ");
                }
            }
            System.out.println();
        }
    }

    private boolean tabuleiroCompleto() {
        for (int i = 0; i < TAMANHO; i++) {
            for (int j = 0; j < TAMANHO; j++) {
                if (tabuleiro[i][j] == CELULA_VAZIA) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean movimentoValido(int linha, int coluna, int num) {
        // Verifica se a linha, coluna e subgrade não contêm o número repetido
        return !usadoNaLinha(linha, num) && !usadoNaColuna(coluna, num) && !usadoNaSubgrade(linha - linha % 3, coluna - coluna % 3, num);
    }

    private boolean usadoNaLinha(int linha, int num) {
        for (int i = 0; i < TAMANHO; i++) {
            if (tabuleiro[linha][i] == num) {
                return true;
            }
        }
        return false;
    }

    private boolean usadoNaColuna(int coluna, int num) {
        for (int i = 0; i < TAMANHO; i++) {
            if (tabuleiro[i][coluna] == num) {
                return true;
            }
        }
        return false;
    }

    private boolean usadoNaSubgrade(int linhaInicial, int colunaInicial, int num) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (tabuleiro[i + linhaInicial][j + colunaInicial] == num) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean tabuleiroValido() {
        // Verifica se o tabuleiro atual é válido (atende às regras do Sudoku)
        for (int i = 0; i < TAMANHO; i++) {
            for (int j = 0; j < TAMANHO; j++) {
                if (tabuleiro[i][j] != CELULA_VAZIA && !movimentoValido(i, j, tabuleiro[i][j])) {
                    return false;
                }
            }
        }
        return true;
    }

    private void jogar() {
        Scanner scanner = new Scanner(System.in);
        while (!tabuleiroCompleto() || !tabuleiroValido()) {
            exibirTabuleiro();
            System.out.println("Digite a linha, coluna e número (1-9) para preencher (ex.: 1 2 3):");
            int linha = scanner.nextInt();
            int coluna = scanner.nextInt();
            int num = scanner.nextInt();
            if (movimentoValido(linha, coluna, num)) {
                tabuleiro[linha - 1][coluna - 1] = num;
            } else {
                System.out.println("Movimento inválido! Tente novamente.");
            }
        }
        scanner.close();
        exibirTabuleiro();
        System.out.println("Parabéns! Você resolveu o quebra-cabeça Sudoku!");
    }

    public static void play() {
        SudokuGame jogo = new SudokuGame();
        jogo.jogar();
    }
}
