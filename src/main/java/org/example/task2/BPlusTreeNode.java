package org.example.task2;

import java.util.ArrayList;
import java.util.List;

// Узел B+ дерева
class BPlusTreeNode {
    List<Integer> keys;
    List<Object> values;
    List<BPlusTreeNode> children;
    boolean isLeaf;

    public BPlusTreeNode() {
        this.keys = new ArrayList<>();
        this.values = new ArrayList<>();
        this.children = new ArrayList<>();
        this.isLeaf = true;
    }

    public void insert(int key, Object value) {
        if (isLeaf) {
            int index = 0;
            while (index < keys.size() && key > keys.get(index)) {
                index++;
            }
            keys.add(index, key);
            values.add(index, value);
        } else {
            int index = 0;
            while (index < keys.size() && key > keys.get(index)) {
                index++;
            }
            children.get(index).insert(key, value);
        }
    }

    public Object search(int key) {
        if (isLeaf) {
            int index = keys.indexOf(key);
            return (index != -1) ? values.get(index) : null;
        } else {
            int index = 0;
            while (index < keys.size() && key > keys.get(index)) {
                index++;
            }
            return children.get(index).search(key);
        }
    }

    public void remove(int key) {
        if (isLeaf) {
            int index = keys.indexOf(key);
            if (index != -1) {
                keys.remove(index);
                values.remove(index);
            }
        } else {
            int index = 0;
            while (index < keys.size() && key > keys.get(index)) {
                index++;
            }
            children.get(index).remove(key);
        }
    }

    public boolean isEmpty() {
        return keys.isEmpty();
    }
}