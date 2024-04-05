package org.example.task2;

import java.util.ArrayList;
import java.util.List;

public class BPlusTree {
    private BPlusTreeNode root;

    public BPlusTree() {
        this.root = null;
    }

    public void insert(int key, Object value) {
        if (root == null) {
            root = new BPlusTreeNode(true, this);
        }
        root.insert(key, value);
    }

    public Object search(int key) {
        return (root != null) ? root.search(key) : null;
    }

    public void remove(int key) {
        if (root != null) {
            root.remove(key);
            if (root.isEmpty()) {
                root = null;
            }
        }
    }

    public BPlusTreeNode getRoot() {
        return root;
    }

    public void setRoot(BPlusTreeNode root) {
        this.root = root;
    }

}
