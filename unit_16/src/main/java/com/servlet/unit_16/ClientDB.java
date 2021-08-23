package com.servlet.unit_16;

import java.util.HashMap;

public class ClientDB {
    private HashMap<String, Client> clientDB = new HashMap<>();

    public void addClient(Client client, String ip) {
        clientDB.put(client.getIp(), client);
    }

    public HashMap<String, Client> getClientList() {
        return clientDB;
    }
}
