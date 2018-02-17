/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jdevsul.servicehandler;

import java.time.LocalTime;
import java.util.ArrayList;

/**
 *
 * @author sulta
 */
public class Session {

    LocalTime startTime;
    ArrayList<Connection> connections;

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public ArrayList<Connection> getConnections() {
        return connections;
    }

    public void setConnections(ArrayList<Connection> connections) {
        this.connections = connections;
    }

    public void addConnection(Connection connection) {
        this.connections.add(connection);
    }

    public void deleteConnection(int connectionID) {

        this.connections.remove(connectionID);
    }
}
