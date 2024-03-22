package org.example.task2;

import java.util.ArrayList;
import java.util.List;

public class BPlusTree {
    private Node root;
    private final int degree;

    public BPlusTree(int degree) {
        this.degree = degree;
        this.root = new LeafNode();
    }

    public void insert(int key, String value) {
        root.insert(key, value);
        if (root.isOverflow()) {
            InternalNode newRoot = new InternalNode();
            newRoot.children.add(root);
            newRoot.splitChild(0);
            root = newRoot;
        }
    }

    public String search(int key) {
        return root.search(key);
    }

    public void delete(int key) {
        root.delete(key);
        if (root.keys.isEmpty() && root instanceof InternalNode) {
            root = ((InternalNode) root).children.get(0);
        }
    }

    private abstract class Node {
        List<Integer> keys;

        Node() {
            keys = new ArrayList<>();
        }

        abstract String search(int key);

        abstract void insert(int key, String value);

        abstract void delete(int key);

        abstract boolean isOverflow();
    }

    private class InternalNode extends Node {
        List<Node> children;

        InternalNode() {
            super();
            children = new ArrayList<>();
        }

        @Override
        String search(int key) {
            int index = 0;
            while (index < keys.size() && key > keys.get(index)) {
                index++;
            }
            return children.get(index).search(key);
        }

        @Override
        void insert(int key, String value) {
            int index = 0;
            while (index < keys.size() && key > keys.get(index)) {
                index++;
            }
            children.get(index).insert(key, value);
            if (children.get(index).isOverflow()) {
                splitChild(index);
            }
        }

        @Override
        void delete(int key) {
            int index = 0;
            while (index < keys.size() && key > keys.get(index)) {
                index++;
            }
            Node child = children.get(index);
            child.delete(key);
            if (child.keys.isEmpty()) {
                keys.remove(index);
                children.remove(index);
                if (keys.isEmpty() && !children.isEmpty()) {
                    // Переносим ключи из следующего узла в текущий
                    InternalNode nextNode = (InternalNode) children.get(0);
                    keys.addAll(0, nextNode.keys);
                    children.remove(0);
                    // Обновляем ссылки на дочерние узлы
                    if (!nextNode.children.isEmpty()) {
                        children.addAll(nextNode.children);
                    }
                }
            }
        }


        @Override
        boolean isOverflow() {
            return keys.size() > degree - 1;
        }

        void splitChild(int index) {
            InternalNode child = (InternalNode) children.get(index);
            InternalNode newChild = new InternalNode();
            newChild.keys.addAll(child.keys.subList(degree / 2 + 1, degree));
            newChild.children.addAll(child.children.subList(degree / 2 + 1, degree + 1));
            child.keys.subList(degree / 2, degree).clear();
            child.children.subList(degree / 2 + 1, degree + 1).clear();
            keys.add(index, child.keys.remove(degree / 2));
            children.add(index + 1, newChild);
        }

    }

    private class LeafNode extends Node {
        List<String> values;
        LeafNode next;

        LeafNode() {
            super();
            values = new ArrayList<>();
        }

        @Override
        String search(int key) {
            int index = 0;
            while (index < keys.size() && key > keys.get(index)) {
                index++;
            }
            if (index < keys.size() && key == keys.get(index)) {
                return values.get(index);
            } else {
                return null;
            }
        }

        @Override
        void insert(int key, String value) {
            int index = 0;
            while (index < keys.size() && key > keys.get(index)) {
                index++;
            }
            keys.add(index, key);
            values.add(index, value);
            if (isOverflow()) {
                splitLeaf();
            }
        }

        @Override
        void delete(int key) {
            int index = keys.indexOf(key);
            if (index != -1) {
                keys.remove(index);
                values.remove(index);
            }
        }

        @Override
        boolean isOverflow() {
            return keys.size() > degree;
        }

        void splitLeaf() {
            LeafNode newLeaf = new LeafNode();
            newLeaf.next = next;
            next = newLeaf;
            newLeaf.keys.addAll(keys.subList(degree / 2, degree));
            newLeaf.values.addAll(values.subList(degree / 2, degree));
            keys.subList(degree / 2, degree).clear();
            values.subList(degree / 2, degree).clear();
            if (next == null) {
                next = newLeaf;
            }
        }
    }
}