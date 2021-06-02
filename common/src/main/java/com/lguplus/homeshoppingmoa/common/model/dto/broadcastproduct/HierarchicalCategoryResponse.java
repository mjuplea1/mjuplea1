package com.lguplus.homeshoppingmoa.common.model.dto.broadcastproduct;

import com.lguplus.homeshoppingmoa.common.model.dto.broadcastproduct.CategoryDto;
import com.lguplus.homeshoppingmoa.common.model.dto.broadcastproduct.TotalCategoryRow;
import lombok.Getter;
import lombok.ToString;

import java.util.HashMap;
import java.util.Map;


@Getter
@ToString
public class HierarchicalCategoryResponse {
    public Node root;

    public HierarchicalCategoryResponse() {
        this.root = new Node();
    }

    public void insertRow(TotalCategoryRow row) {
        add(root, 0, row);
    }

    private void add(Node root, int depth, TotalCategoryRow row) {
        if (depth >= 4) {
            return;
        }

        CategoryDto item = row.getRow()[depth];

        if (!root.child.containsKey(item.getCatg_code())) {
            Node newNode = new Node(item);
            root.child.put(item.getCatg_code(), newNode);
        }
        add(root.child.get(item.getCatg_code()), depth + 1, row);
    }

    @SuppressWarnings("rawtypes")
    public Map getCategories(){
        return root.child;
    }

    @Getter
    @ToString
    static class Node {
        CategoryDto data;
        Map<Integer, Node> child;

        Node() {
            this.child = new HashMap<>();
        }

        Node(CategoryDto data) {
            this.data = data;
            this.child = new HashMap<>();
        }
    }
}
