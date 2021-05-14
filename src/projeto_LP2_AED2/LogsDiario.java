package projeto_LP2_AED2;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Out;

public class LogsDiario implements GestaoLogs {

    //FIELDS/CAMPOS
    public String mensagem;
    public Date data;

    /**
     * Metodo para fazer logs sobre algo para um ficheiro
     * @param mensagem - a mensagem que vamos escrever no ficheiro
     * @param data - data em que aconteceu
     * @param file - ficheiro em que vamos escrever
     */
    @Override
    public void adicionaLog(String mensagem, Date data, String file) {
        int k = 0, x = 0;
        In infile = new In(file);
        String[] allLines = infile.readAllLines();
        Out outfile = new Out(file);
        while (allLines.length > k) {
            outfile.println(allLines[k]);
            k++;
        }
        outfile.println(mensagem + ", na data de: " + data);
    }
}