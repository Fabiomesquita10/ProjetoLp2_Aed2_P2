import edu.princeton.cs.algs4.Stack;

// Java implementation of the approach
class GFG
{
    public static Stack<Integer> paths = new Stack<>();
    public static String stringCaches = new String();

    public String retStrin(int[][] graph, boolean[] v, int currPos, int n, int count, int cost, int ans, int dist, int l){
        ans = tsp(graph, v, 0, n, 1, 0, ans, dist, l);
        return stringCaches;
    }

    public static String getStringCaches() {
        return stringCaches;
    }

    public static void setStringCaches(String stringCaches) {
        GFG.stringCaches = stringCaches;
    }

    // Function to find the minimum weight
    // Hamiltonian Cycle
    public static int tsp(int[][] graph, boolean[] v, int currPos, int n, int count, int cost, int ans, int dist, int l)
    {
        // If last node is reached and it has a link
        // to the starting node i.e the source then
        // keep the minimum value out of the total cost
        // of traversal and "ans"
        // Finally return to check for more possible values
        if (count == n && graph[currPos][0] > 0 || dist<cost)
        {
            ans = Math.min(ans, cost + graph[currPos][0]);
            stringCaches = stringCaches + "\n" + paths.toString();
            return ans;
        }

        // BACKTRACKING STEP
        // Loop to traverse the adjacency list
        // of currPos node and increasing the count
        // by 1 and cost by graph[currPos,i] value
        for (int i = 0; i < n; i++)
        {

            if (!v[i] && graph[currPos][i] > 0)
            {
                // Mark as visited
                if(i == l) {
                    i = 0;
                    if (!v[i] && graph[currPos][i] > 0) {
                        System.out.println("l" + i);
                        v[i] = true;
                        paths.push(0);
                        ans = tsp(graph, v, 0, n, count + 1, cost + graph[currPos][i], ans, dist, l);

                        // Mark ith node as unvisited
                        v[i] = false;
                        paths.pop();
                    }
                    i = l;
                }else if(i == 0){
                    i = l;
                    if (!v[i] && graph[currPos][i] > 0) {
                        System.out.println("0" + i);
                        v[i] = true;
                        paths.push(l);
                        ans = tsp(graph, v, l, n, count + 1, cost + graph[currPos][i], ans, dist, l);

                        // Mark ith node as unvisited
                        v[i] = false;
                        paths.pop();
                    }
                    i = 0;
                }else{
                    //System.out.println("mirosvaldo");
                    v[i] = true;
                    paths.push(i);
                    ans = tsp(graph, v, i, n, count + 1, cost + graph[currPos][i], ans, dist, l);

                    // Mark ith node as unvisited
                    v[i] = false;
                    paths.pop();
                }
            }
        }
        return ans;
    }

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

// This code is contributed by Rajput-Ji