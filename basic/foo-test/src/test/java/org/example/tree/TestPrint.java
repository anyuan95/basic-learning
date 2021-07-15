package org.example.tree;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.example.basic.data.structure.model.ProductType;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TestPrint {

    private static List<ProductType> productTypes;

    public static void main(String[] args) {
        productTypes = new ArrayList<>();
        productTypes.add(new ProductType(1, "手机", 0, 1));
        productTypes.add(new ProductType(2, "电脑", 0, 1));
        productTypes.add(new ProductType(3, "电器", 0, 1));
        productTypes.add(new ProductType(4, "游戏手机", 1, 1));
        productTypes.add(new ProductType(5, "5G手机", 1, 1));
        productTypes.add(new ProductType(6, "拍照手机", 1, 1));
        productTypes.add(new ProductType(7, "笔记本", 2, 1));
        productTypes.add(new ProductType(8, "一体机", 2, 1));
        productTypes.add(new ProductType(9, "台式机", 2, 1));
        productTypes.add(new ProductType(10, "华硕笔记本", 7, 1));
        productTypes.add(new ProductType(11, "联想笔记本", 7, 1));
        productTypes.add(new ProductType(12, "华硕i7", 10, 1));
        productTypes.add(new ProductType(13, "华硕i5", 10, 1));
        productTypes.add(new ProductType(14, "联想小新", 11, 1));
        printProduct(productTypes);
    }

    @Setter
    @Getter
    @ToString(callSuper = true)
    private static class ProductTypeWithSub extends ProductType {

        private List<ProductTypeWithSub> subProductTypes;

        public ProductTypeWithSub(Integer id, String name, Integer parentId, Integer status) {
            super(id, name, parentId, status);
            this.subProductTypes = new ArrayList<>(16);
        }

        public ProductTypeWithSub(ProductType productType) {
            super(productType.getId(), productType.getName(), productType.getParentId(), productType.getStatus());
            this.subProductTypes = new ArrayList<>(16);
        }

        public ProductTypeWithSub addSub(ProductTypeWithSub subProductType) {
            this.subProductTypes.add(subProductType);
            return this;
        }

        public void setSubProductTypes(List<ProductTypeWithSub> subProductTypes) {
            this.subProductTypes = subProductTypes;
        }
    }

    private static void printProduct(List<ProductType> productTypes) {
        List<ProductTypeWithSub> productTypeWithSubs = productTypes.stream().map(ProductTypeWithSub::new)
                .collect(Collectors.toList());
        Map<Integer, List<ProductTypeWithSub>> productTypeMap = productTypeWithSubs.stream()
                .collect(Collectors.groupingBy(ProductType::getParentId));
        productTypeWithSubs.forEach(productTypeWithSub -> {
            productTypeWithSub.setSubProductTypes(productTypeMap.get(productTypeWithSub.getId()));
        });
        productTypeWithSubs.stream().filter(productTypeWithSub -> productTypeWithSub.getParentId() == 0)
                .forEach(productTypeWithSub -> printRecursively(productTypeWithSub, 0));
    }

    private static void printRecursively(ProductTypeWithSub productTypeWithSub, int depth) {
        for (int i = 0; i < depth; i++) {
            System.out.print("|");
        }
        System.out.println(productTypeWithSub.getName());
        if (productTypeWithSub.getSubProductTypes() != null) {
            productTypeWithSub.getSubProductTypes().forEach(productTypeWithSub1 -> printRecursively(productTypeWithSub1, depth + 1));
        }
    }

}
