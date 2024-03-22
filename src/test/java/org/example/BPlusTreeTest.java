package org.example;


import org.example.task2.BPlusTree;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class BPlusTreeTest {

    @ParameterizedTest
    @CsvSource({"3", "4", "5"})
    @DisplayName("Общие проверки работы")
    void testBPlusTree(int degree) {
        BPlusTree bPlusTree = new BPlusTree(degree);

        // Вставка элементов
        bPlusTree.insert(1, "A");
        assertEquals("A", bPlusTree.search(1));

        bPlusTree.insert(2, "B");
        assertEquals("B", bPlusTree.search(2));

        bPlusTree.insert(3, "C");
        assertEquals("C", bPlusTree.search(3));

        bPlusTree.insert(4, "D");
        assertEquals("D", bPlusTree.search(4));

        // Поиск элементов
        assertEquals("A", bPlusTree.search(1));
        assertEquals("B", bPlusTree.search(2));
        assertEquals("C", bPlusTree.search(3));
        assertEquals("D", bPlusTree.search(4));
        assertNull(bPlusTree.search(5));

        // Удаление элементов
        bPlusTree.delete(2);
        assertNull(bPlusTree.search(2));
        bPlusTree.delete(4);
        assertNull(bPlusTree.search(4));
    }

    @DisplayName("Проверка удаления из пустого дерева")
    @ParameterizedTest
    @CsvSource({"3", "4", "5"})
    void testEmptyBPlusTree(int degree) {
        BPlusTree bPlusTree = new BPlusTree(degree);

        // Поиск в пустом дереве
        assertNull(bPlusTree.search(1));
        assertNull(bPlusTree.search(2));
        assertNull(bPlusTree.search(3));

        // Удаление из пустого дерева
        bPlusTree.delete(1);
        bPlusTree.delete(2);
        bPlusTree.delete(3);
    }


    @ParameterizedTest
    @CsvSource({"3", "4", "5"})
    @DisplayName("Проверка удаления всех элементов")
    void testUnderflow(int degree) {
        BPlusTree bPlusTree = new BPlusTree(degree);

        // Вставка необходимого количества элементов
        for (int i = 1; i <= degree; i++) {
            bPlusTree.insert(i, String.valueOf(i));
        }

        // Удаление всех элементов
        for (int i = 1; i <= degree; i++) {
            bPlusTree.delete(i);
        }

        // Проверка, что дерево пустое
        assertNull(bPlusTree.search(1));
    }

    @ParameterizedTest
    @CsvSource({"3", "4", "5"})
    @DisplayName("Проверка перестроения дерева после удаления")
    void testDelete(int degree) {
        // Создание дерева с заданной степенью
        BPlusTree bPlusTree = new BPlusTree(degree);

        // Вставка нескольких элементов в дерево
        bPlusTree.insert(1, "A");
        bPlusTree.insert(2, "B");
        bPlusTree.insert(3, "C");
        bPlusTree.insert(4, "D");
        bPlusTree.insert(5, "E");

        // Удаление одного элемента из дерева
        bPlusTree.delete(3);

        // Проверка, что удаление прошло корректно
        assertNull(bPlusTree.search(3)); // Проверяем, что удаленный элемент больше не присутствует в дереве

        // Проверка, что структура дерева соответствует ожидаемой
        assertEquals("A", bPlusTree.search(1));
        assertEquals("B", bPlusTree.search(2));
        assertEquals("D", bPlusTree.search(4));
        assertEquals("E", bPlusTree.search(5));
    }
}

