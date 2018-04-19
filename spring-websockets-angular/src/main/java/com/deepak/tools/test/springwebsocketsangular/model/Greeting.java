package com.deepak.tools.test.springwebsocketsangular.model;

public class Greeting {

    private final long id;
    private final String content;

    public Greeting(long id, String content) {
        this.id = id;
        this.content = content;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Greeting [id=");
        builder.append(id);
        builder.append(", content=");
        builder.append(content);
        builder.append("]");
        return builder.toString();
    }

    public long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }
}
