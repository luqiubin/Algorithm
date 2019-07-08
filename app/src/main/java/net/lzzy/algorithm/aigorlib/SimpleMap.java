package net.lzzy.algorithm.aigorlib;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by lzzy_gxy on 2019/6/27.
 * Description:
 */
public class SimpleMap {
    int vertexCount;
    List<Edge> edges = new ArrayList<>();
    List<Integer> visited;

    public SimpleMap(int v) {
        vertexCount = v;
    }

    public void addEdge(int source, int target, double distance) {
        edges.add(new Edge(source, target, distance));
    }

    public void addTwoWayEdge(int v1, int v2, double distance) {
        addEdge(v1, v2, distance);
        addEdge(v1, v2, distance);
    }

    List<Edge> getConnectedEdges(int source) {
        List<Edge> result = new ArrayList<>();
        for (Edge e : edges) {
            if (e.getSource() == source) {
                result.add(e);
            }
        }
        return result;
    }

    private void iterateDepth(int v) {
        if (!visited.contains(v)) {
            visited.add(v);
        }
        if (visited.size() == vertexCount) {
            return;
        }
        List<Edge> vEges = getConnectedEdges(v);
        for (Edge edge : vEges) {
            if (visited.contains(edge.getTarhet())) {
                continue;
            }
            iterateDepth(edge.getTarhet());
        }
    }

    public String iterateDepthFirst() {
//        return "015234";
        StringBuilder result = new StringBuilder();
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(0);
        int head = 0, tail = 1;
        while (head < tail) {
            Integer cursor = queue.poll();
            if (cursor == null) {
                break;
            }
            result.append(cursor).append(",");
            List<Edge> vEdges = getConnectedEdges(cursor);
            for (Edge edge : vEdges) {
                if (!queue.contains(edge.getTarhet())) {
                    queue.offer(edge.getTarhet());
                    tail++;
                }
                if (tail > vertexCount) {
                    break;
                }
            }
            head++;
        }
        while (queue.size() > 0) {
            result.append(queue.poll()).append(",");
        }
        return result.toString();
    }
}
    class Edge {
        private int source;
        private int tarhet;
        private double distance;

        public int getSource() {
            return source;
        }

        public int getTarhet() {
            return tarhet;
        }

        public double getDistance() {
            return distance;
        }

        public void setSource(int source) {
            this.source = source;
        }

        public void setTarhet(int tarhet) {
            this.tarhet = tarhet;
        }

        public void setDistance(double distance) {
            this.distance = distance;
        }

        Edge(int source, int tarhet, double distance) {
            this.source = source;
            this.tarhet = tarhet;
            this.distance = distance;

        }
    }

