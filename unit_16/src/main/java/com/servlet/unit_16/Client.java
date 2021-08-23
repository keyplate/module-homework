package com.servlet.unit_16;

public class Client {
    private String ip;
    private String userAgent;

    protected Client(String ip, String userAgent) {
        this.ip = ip;
        this.userAgent = userAgent;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public String getIp() {
        return ip;
    }

    public String getUserAgent() {
        return userAgent;
    }

    @Override
    public String toString() {
        return ip + " " + userAgent;
    }
}
