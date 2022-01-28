import java.util.*;

public class Graph {
    private int v;
    private ArrayList<ArrayList<Integer>> adj;
    //private ArrayList<ArrayList<Integer>> adjList;
    private ArrayList<Integer> path;
    private ArrayList<ArrayList<Integer>> paths;
    public Graph(int vertices)
    {

        // initialise vertex count
        this.v = vertices;
        adj = new ArrayList();

        path = new ArrayList<>();
        paths = new ArrayList<>();
        // initialise adjacency list
        initAdjList();
    }

    private void initAdjList()
    {

        for (int i = 0; i < v; i++) {
            adj.add(new ArrayList<>());
        }
    }
    public void addEdge(int src, int dest)
    {
        adj.get(src).add(dest);
        adj.get(dest).add(src);
    }
    public double findBetweenCentrality(int vertex){
        int all_paths = 0;
        int count = 0;
        for (int i = 0; i < paths.size(); i++) {
            if(paths.get(i).get(0)!=vertex){
                all_paths++;
                if(paths.get(i).contains(vertex)) count++;
            }
        }

        if(all_paths !=0) return (double)count/ (double)all_paths;
        return count;
    }
    public void getAllPath(){
        for (int i = 0; i < v-1; i++) {
            for (int j = i+1; j < v; j++) {
                findShortestDistance(i,j);
                if(path.size()!=0){ paths.add(path);}
                path=new ArrayList<>();
            }
        }
    }
    public int getV(){
        return v;
    }
    public double findClosenessCentrality(int vertex){
        int count=0;
        int total_length = 0;
        for (int i = 0; i < paths.size(); i++) {
            if(paths.get(i).get(0) == vertex&&paths.get(i).size()!=0){
                count++;
                total_length+=paths.get(i).size();
            }
        }
        if(total_length==0) return 0;
        return (double)count/(double)total_length;
    }
    private void findShortestDistance(int s, int dest)
    {
        // predecessor[i] array stores predecessor of
        // i and distance array stores distance of i
        // from s
        int pred[] = new int[v];
        int dist[] = new int[v];

        if (BFS(s, dest, v, pred, dist) != false) {
            // LinkedList to store path
            LinkedList<Integer> path = new LinkedList<Integer>();
            int crawl = dest;
            path.add(crawl);
            while (pred[crawl] != -1) {
                path.add(pred[crawl]);
                crawl = pred[crawl];
            }

            // Print distance

            // Print path
            for (int i = path.size() - 1; i >= 0; i--) {
                this.path.add(path.get(i));
            }
        }
    }

    // a modified version of BFS that stores predecessor
    // of each vertex in array pred
    // and its distance from source in array dist
    private boolean BFS( int src,
                               int dest, int v, int pred[], int dist[])
    {
        // a queue to maintain queue of vertices whose
        // adjacency list is to be scanned as per normal
        // BFS algorithm using LinkedList of Integer type
        LinkedList<Integer> queue = new LinkedList<Integer>();

        // boolean array visited[] which stores the
        // information whether ith vertex is reached
        // at least once in the Breadth first search
        boolean visited[] = new boolean[v];

        // initially all vertices are unvisited
        // so v[i] for all i is false
        // and as no path is yet constructed
        // dist[i] for all i set to infinity
        for (int i = 0; i < v; i++) {
            visited[i] = false;
            dist[i] = Integer.MAX_VALUE;
            pred[i] = -1;
        }

        // now source is first to be visited and
        // distance from source to itself should be 0
        visited[src] = true;
        dist[src] = 0;
        queue.add(src);

        // bfs Algorithm
        while (!queue.isEmpty()) {
            int u = queue.remove();
            for (int i = 0; i < adj.get(u).size(); i++) {
                if (visited[adj.get(u).get(i)] == false) {
                    visited[adj.get(u).get(i)] = true;
                    dist[adj.get(u).get(i)] = dist[u] + 1;
                    pred[adj.get(u).get(i)] = u;
                    queue.add(adj.get(u).get(i));

                    // stopping condition (when we find
                    // our destination)
                    if (adj.get(u).get(i) == dest)
                        return true;
                }
            }
        }
        return false;
    }
}
