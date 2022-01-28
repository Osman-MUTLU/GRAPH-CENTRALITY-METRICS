import java.io.*;
import java.util.ArrayList;

class Test {
    public static void main(String[] args) throws IOException {
        ArrayList<String> files = new ArrayList<String>();
        files.add("karate_club_network.txt");
        files.add("facebook_social_network.txt");

        ArrayList<Graph> graphs = new ArrayList();
        for(String fileName :files){
            File file = new File(fileName);

            ArrayList<String> graph = new ArrayList();
            FileReader fileReader = new FileReader(file);
            String line;
            BufferedReader br = new BufferedReader(fileReader);
            int max=0;
            while ((line = br.readLine()) != null) {
                String[] vertices = line.split(" ");
                int source = Integer.valueOf(vertices[0]);
                int destination = Integer.valueOf(vertices[1]);
                if(source>max) max = source;
                if(destination>max) max=destination;
                graph.add(source + "-"+destination);
            }
            br.close();
            Graph g = new Graph(max+1);
            for (String edge:graph) {
                String[] vertices = edge.split("-");
                int destination = Integer.valueOf(vertices[0]);
                int source = Integer.valueOf(vertices[1]);
                g.addEdge(source, destination);
            }
            g.getAllPath();
            graphs.add(g);
        }
        System.out.println("2018510050 Osman MUTLU: ");
        Graph g1= graphs.get(0);
        Graph g2= graphs.get(1);
        double btw = 0;
        double cls = 0;
        int btw_node = 0;
        int cls_node = 0;
        for (int i = 0; i < g1.getV(); i++) {
            double betweenCentrality = g1.findBetweenCentrality(i);
            if(betweenCentrality>btw){
                btw=betweenCentrality;
                btw_node = i;
            }
            double closenessCentrality = g1.findClosenessCentrality(i);
            if(closenessCentrality>cls){
                cls=closenessCentrality;
                cls_node = i;
            }
        }
        System.out.println("Zachary Karate Club Network – The Highest Node for Betweennes is "+btw_node+" and the value "+btw);
        System.out.println("Zachary Karate Club Network – The Highest Node for Closeness is "+cls_node+" and the value "+cls);
        btw = 0;
        cls = 0;
        btw_node = 0;
        cls_node = 0;
        for (int i = 0; i < g2.getV(); i++) {
            double betweenCentrality = g2.findBetweenCentrality(i);
            if(betweenCentrality>btw){
                btw=betweenCentrality;
                btw_node = i;
            }
            double closenessCentrality = g2.findClosenessCentrality(i);
            if(closenessCentrality>cls){
                cls=closenessCentrality;
                cls_node = i;
            }
        }
        System.out.println("Facebook Social Network – The Highest Node for Betweennes is "+btw_node+" and the value "+btw);
        System.out.println("Facebook Social Network – The Highest Node for Closeness is "+cls_node+" and the value "+cls);
    }
}