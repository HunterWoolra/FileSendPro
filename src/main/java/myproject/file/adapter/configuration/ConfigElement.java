package myproject.file.adapter.configuration;

import java.util.LinkedList;


public class ConfigElement {
    LinkedList<Node>[] data;

    public ConfigElement(int size) {
        // LinkedList size 3 으로 대입하고 호출
        System.out.println(size);

        this.data = new LinkedList[size];
    }

    public void put(String key, String value) {
        int hashCode = getHashCode(key);
        int index = convertToIndex(hashCode);
        LinkedList<Node> list = data[index];
        if (list == null) {
            list = new LinkedList<Node>();
            data[index] = list;
        }

        Node node = searchKey(list, key);
        if (node == null) {
            list.addLast(new Node(key, value));
        }else {
            node.value = value;
        }
    }

    public String get(String key) {
        int hashCode = getHashCode(key);
        int index = convertToIndex(hashCode);
        LinkedList<Node> list = data[index];
        Node node = searchKey(list, key);
        return node == null ? null : node.value;
    }

    private int getHashCode(String key) {
        int hashcode = 0;
        for (char c : key.toCharArray()) {
            hashcode += c;
        }
        return hashcode;
    }

    private int convertToIndex(int hashCode) {
        return hashCode % data.length;
    }

    private Node searchKey(LinkedList<Node> list, String key) {
        if(list == null) return null;
        for (Node node : list) {
            if (node.key.equals(key)) {
                return node;
            }
        }
        return null;
    }
    
    private static class Node {
        private String key;
        private String value;

        private Node(String key, String value) {
            this.key = key;
            this.value = value;
        }
    }


}
