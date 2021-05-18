import edu.princeton.cs.algs4.Stack;

// Java implementation of the approach
class GFG
{
    public static Stack<Integer> paths = new Stack<>();
    public static String ola = new String();

    public String retStrin(int[][] graph, boolean[] v, int currPos, int n, int count, int cost, int ans, int dist){
        ans = tsp(graph, v, 0, n, 1, 0, ans, dist);
        return ola;
    }

    public static String getOla() {
        return ola;
    }

    public static void setOla(String ola) {
        GFG.ola = ola;
    }

    // Function to find the minimum weight
    // Hamiltonian Cycle
    public static int tsp(int[][] graph, boolean[] v, int currPos, int n, int count, int cost, int ans, int dist)
    {
        // If last node is reached and it has a link
        // to the starting node i.e the source then
        // keep the minimum value out of the total cost
        // of traversal and "ans"
        // Finally return to check for more possible values
        if (count == n && graph[currPos][0] > 0 || dist<cost)
        {
            ans = Math.min(ans, cost + graph[currPos][0]);
            System.out.println(paths);
            ola = ola + "\n" + paths.toString();
            System.out.println(ola);
            return ans;
        }

        // BACKTRACKING STEP
        // Loop to traverse the adjacency list
        // of currPos node and increasing the count
        // by 1 and cost by graph[currPos,i] value
        for (int i = 0; i < n; i++)
        {
            if (v[i] == false && graph[currPos][i] > 0)
            {

                // Mark as visited
                v[i] = true;
                paths.push(i);
                ans = tsp(graph, v, i, n, count + 1, cost + graph[currPos][i], ans, dist);

                // Mark ith node as unvisited
                v[i] = false;
                paths.pop();
            }
        }
        return ans;
    }

    // Driver code
    public static void main(String[] args)
    {

        // n is the number of nodes i.e. V
        int n = 4;
        //                a  b   c   d
        int[][] graph = {{0, 10, 15, 20},//a
                        {10, 0, 35, 25}, //b
                        {15, 35, 0, 30}, //c
                        {20, 25, 30, 0}};//d


        // Boolean array to check if a node
        // has been visited or not
        boolean[] v = new boolean[n];

        // Mark 0th node as visited
        v[0] = true;
        int ans = Integer.MAX_VALUE;
        int dist = 10000;
        // Find the minimum weight Hamiltonian Cycle
        ans = tsp(graph, v, 0, n, 1, 0, ans, dist);

        // ans is the minimum weight Hamiltonian Cycle
        System.out.println(ans);
    }
}

// This code is contributed by Rajput-Ji