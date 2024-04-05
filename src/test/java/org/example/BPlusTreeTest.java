package org.example;

import org.example.task2.BPlusTree;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BPlusTreeTest {

    @Test
    public void testInsert() {
        BPlusTree bPlusTree = new BPlusTree();
        bPlusTree.insert(1, "Value1");
        assertEquals("Value1", bPlusTree.search(1));
    }

    @Test
    public void testSearch() {
        BPlusTree bPlusTree = new BPlusTree();
        bPlusTree.insert(1, "Value1");
        assertEquals("Value1", bPlusTree.search(1));
        assertNull(bPlusTree.search(2)); // Проверка поиска отсутствующего ключа
    }

    @Test
    public void testDeleteEmptyNode() {
        BPlusTree bPlusTree = new BPlusTree();
        // Вставляем ключ
        bPlusTree.insert(1, "Value1");

        // Проверяем наличие вставленного ключа
        assertEquals("Value1", bPlusTree.search(1));

        // Удаляем ключ
        bPlusTree.remove(1);

        // Проверяем отсутствие ключа
        assertNull(bPlusTree.search(1));

        // Вставляем еще один ключ, чтобы дерево стало пустым после удаления
        bPlusTree.insert(2, "Value2");

        // Удаляем ключ, что должно привести к удалению пустого узла
        bPlusTree.remove(2);

        // Пытаемся получить доступ к удаленному ключу
        assertNull(bPlusTree.search(2));

        // Проверяем, что корень дерева пуст
        assertNull(bPlusTree.getRoot());
    }
}

