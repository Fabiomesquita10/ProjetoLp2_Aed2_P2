import edu.princeton.cs.algs4.Stack;

// Java implementation of the approach
class CaixeiroViajante
{
    public static Stack<Integer> paths = new Stack<>();
    public static String stringCaches = new String();

    /**
     * funcao que retorna uma string com as caches que vamos ter de precorrer, retorna o menos caminho
     * @param graph o grafo onde se vai fazer a pesquisa
     * @param v array de booleans para ter marcado as posicoes ja percorridas
     * @param currPos posicao atual na pesquisa
     * @param n
     * @param count caches que ja esteve
     * @param cost costo da viagem
     * @param ans
     * @param dist distancia maxima que ele pode precorrer
     * @param l linha que vamos comecar a procurar
     * @return da return das caches que ele vai passar
     */
    public String retStrin(int[][] graph, boolean[] v, int currPos, int n, int count, int cost, int ans, int dist, int l){
        stringCaches = "";
        ans = tsp(graph, v, 0, n, 1, 0, ans, dist, l);
        return stringCaches;
    }

    public static String getStringCaches() {
        return stringCaches;
    }

    public static void setStringCaches(String stringCaches) {
        CaixeiroViajante.stringCaches = stringCaches;
    }

    /**
     * funcao que calcula o distancia minima
     * @param graph o grafo onde se vai fazer a pesquisa
     * @param v array de booleans para ter marcado as posicoes ja percorridas
     * @param currPos posicao atual na pesquisa
     * @param n de iteracoes
     * @param count caches que ja esteve
     * @param cost costo da viagem
     * @param ans valor de returno que vai aumentando
     * @param dist distancia maxima que ele pode precorrer
     * @param l linha que vamos comecar a procurar
     * @return peso
     */
    public static int tsp(int[][] graph, boolean[] v, int currPos, int n, int count, int cost, int ans, int dist, int l)
    {

        //se tivermos chegado a ultima linha
        //ou se a distancia ja for menor que o custo, a funcao acaba
        if (count == n && graph[currPos][0] > 0 || dist<cost)
        {
            ans = Math.min(ans, cost + graph[currPos][0]);
            stringCaches = stringCaches + "\n" + paths.toString();
            return ans;
        }

        // vai iterando a matriz, pelo caminho mais curto de forma iterativa
        for (int i = 0; i < n; i++)
        {

            if (!v[i] && graph[currPos][i] > 0)
            {
                // Mark as visited
                if(i == l) { // se for a linha que trocamos , temos de a trocar para a que era
                    i = 0;
                    if (!v[i] && graph[currPos][i] > 0) {
                        System.out.println("l" + i);
                        v[i] = true;
                        paths.push(0);
                        ans = tsp(graph, v, 0, n, count + 1, cost + graph[currPos][i], ans, dist, l);
                        v[i] = false;
                        paths.pop();
                    }
                    i = l;
                }else if(i == 0){ // se for a linha 0 troca para a linha que tamnos a iniciar
                    i = l;
                    if (!v[i] && graph[currPos][i] > 0) {
                        System.out.println("0" + i);
                        v[i] = true;
                        paths.push(l);
                        ans = tsp(graph, v, l, n, count + 1, cost + graph[currPos][i], ans, dist, l);
                        v[i] = false;
                        paths.pop();
                    }
                    i = 0;
                }else{
                    v[i] = true;
                    paths.push(i);
                    ans = tsp(graph, v, i, n, count + 1, cost + graph[currPos][i], ans, dist, l);
                    v[i] = false;
                    paths.pop();
                }
            }
        }
        return ans;
    }

    /**
     * funcao para trocar duas linhas da matriz
     * @param matrix a matriz
     * @param K
     * @param L
     * @return a matriz qcom as linhas trocadas
     */
    public int[][] exchangeAnyTwoRows(int[][] matrix, int K, int L)
    {
        for (int i = 0; i < matrix[0].length; i++) {
            // Swap two numbers
            int temp = matrix[K - 1][i];
            matrix[K - 1][i] = matrix[L - 1][i];
            matrix[L - 1][i] = temp;
        }
        return matrix;
    }

    /**
     * funcao para printar a matriz
     * @param matrix
     */
    public void printMatrix(int[][]matrix){
        System.out.println("Matriz de adjacencias do grafo: ");
        for (int i = 0; i < matrix.length; i++) {
            if(i<=10)
                System.out.print(" " + i + "   ");
            if(i>10)
                System.out.print(i + "   ");
        }
        System.out.println("\n ==================================================================");
        for (int i = 0; i < matrix.length; i++) {
            System.out.print(i + "|");
            for (int j = 0; j < matrix.length; j++) {
                if(matrix[i][j]<10)
                    System.out.print(matrix[i][j] + "   |");
                else if(matrix[i][j]>=10 && matrix[i][j]<100)
                    System.out.print(matrix[i][j] + "  |");
                else if(matrix[i][j]>=100 && matrix[i][j]<1000)
                    System.out.print(matrix[i][j] + " |");
                else if(matrix[i][j]>=1000 && matrix[i][j]<10000)
                    System.out.print(matrix[i][j] + "|");
            }
            System.out.println("\n ==================================================================");
        }
    }
}
