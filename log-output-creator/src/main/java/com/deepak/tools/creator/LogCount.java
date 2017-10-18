package com.deepak.tools.creator;

public class LogCount {

    private final long id;
    private final String content;

    public LogCount(long id, String content) {
        this.id = id;
        this.content = content;
    }

    public long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }
}
