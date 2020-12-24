package by.dazer;

public class SiteActix {

    private String  nodeB, lat, lon;

    public SiteActix(String nodeB, String lat, String lon) {
        this.nodeB = nodeB;
        this.lat = lat;
        this.lon = lon;
    }

    public String getNodeB() {
        return nodeB;
    }

    public void setNodeB(String nodeB) {
        this.nodeB = nodeB;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }
}
