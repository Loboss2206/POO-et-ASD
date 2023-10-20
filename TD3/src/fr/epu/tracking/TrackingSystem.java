package fr.epu.tracking;

import java.util.ArrayList;

public class TrackingSystem {
    private ArrayList<Trackable> listTrackables;

    public TrackingSystem() {
        listTrackables = new ArrayList<>();
    }

    public void addTrackableObject(Trackable t) {
        listTrackables.add(t);
    }

    public Position getTrackableObjectPosition(int index) {
        if (index < listTrackables.size()) return listTrackables.get(index).getPosition();
        else return null;
    }

    public ArrayList<Position> getAllTrackablePositions() {
        ArrayList<Position> listPositions = new ArrayList<Position>();
        for (Trackable t : listTrackables) {
            listPositions.add(t.getPosition());
        }
        return listPositions;
    }

    public int getNumberOfTrackedObjects() {
        return listTrackables.size();
    }
}
