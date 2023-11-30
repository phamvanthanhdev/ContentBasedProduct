/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package contentbasedrecommedation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Pham Van Thanh
 */
public class ContentBasedRecommedation {

    /**
     * @param args the command line arguments
     */
    
    public static void addAtrribute(ArrayList<String> atrributes, String atrribute){
        boolean check = false;
        for(int i=0; i < atrributes.size(); i++){
            if(atrributes.get(i).equals(atrribute) == true){
                check = true;
                break;
            }
        }
        if(!check){
            atrributes.add(atrribute);
        }
    }
    
    // Tính dot product của hai vector
    private static double calculateDotProduct(int[] vectorA, int[] vectorB) {
        double dotProduct = 0;
        for (int i = 0; i < vectorA.length; i++) {
            dotProduct += vectorA[i] * vectorB[i];
        }
        return dotProduct;
    }

    // Tính độ dài Euclidean của vector
    private static double calculateMagnitude(int[] vector) {
        double sumOfSquares = 0;
        for (double value : vector) {
            sumOfSquares += value * value;
        }
        return Math.sqrt(sumOfSquares);
    }
    
    public static void main(String[] args) {
        String[] rams1 = {"4G", "8G", "16G"};
        String[] roms1 = {"128G", "256G", "512G"};
        Product product1 = new Product(1, "Iphone 15", "SmartPhone", "Apple", "Hoc tap", 25000000, 5, rams1, roms1);

        String[] rams2 = {"8G", "16G"};
        String[] roms2 = {"256G", "512G"};
        Product product2 = new Product(2, "Galaxy Fold", "SmartPhone", "Samsung","Gaming", 15000000, 2, rams2, roms2);

        String[] rams3 = {"8G", "16G"};
        String[] roms3 = {"256G", "512G"};
        Product product3 = new Product(3, "Macbook M1", "Laptop", "Apple","Hoc tap", 35000000, 2, rams3, roms3);

        String[] rams4 = {"16G", "32G"};
        String[] roms4 = {"512G"};
        Product product4 = new Product(4, "Lenovo Legion", "Laptop", "Lenovo","Gaming", 25000000, 5, rams4, roms4);

        String[] rams5 = {"8G"};
        String[] roms5 = {"256G", "512G"};
        Product product5 = new Product(5, "Hp 123", "Laptop", "Hp", "Giai tri", 15000000, 5, rams5, roms5);
        
        // San pham ao
        String[] ramsVirtual = {"16G"};
        String[] romsVirtual = {"128G","256G", "512G"};
        Product productVirtual = new Product(6, "Virtual Product", "Laptop", "Apple", "Giai tri", 21000000, 2, ramsVirtual, romsVirtual);

        List<Product> productList = Arrays.asList(product1, product2, product3, product4, product5, productVirtual);
        
        
        ArrayList<String> atrributes_categories = new ArrayList<String>();
        ArrayList<String> atrributes_brands = new ArrayList<String>();
        ArrayList<String> atrributes_demands = new ArrayList<String>();
        ArrayList<String> atrributes_prices = new ArrayList<String>();
        ArrayList<String> atrributes_discounts = new ArrayList<String>();
        ArrayList<String> atrributes_rams = new ArrayList<String>();
        ArrayList<String> atrributes_roms = new ArrayList<String>();
        
        double min_price = productVirtual.getPrice() - 5000000;
        double max_price = productVirtual.getPrice() + 5000000;
        
        addAtrribute(atrributes_prices, min_price+"-"+max_price);
        
        for(Product product : productList){
            //atrributes.add(product.getId()+"");
            //atrributes.add(product.getName());
            addAtrribute(atrributes_categories, product.getCategory());
            addAtrribute(atrributes_brands, product.getBrand());
            addAtrribute(atrributes_demands, product.getDemand());
            addAtrribute(atrributes_discounts, product.getDiscount()+"");
            for(String ram : product.getRams()){
                addAtrribute(atrributes_rams, ram);
            }
            for(String rom : product.getRoms()){
                addAtrribute(atrributes_roms, rom);
            }
        }
        
        
        ArrayList<String> atrributes = new ArrayList<String>();
        atrributes.addAll(atrributes_categories);
        atrributes.addAll(atrributes_brands);
        atrributes.addAll(atrributes_demands);
        atrributes.addAll(atrributes_prices);
        atrributes.addAll(atrributes_discounts);
        atrributes.addAll(atrributes_rams);
        atrributes.addAll(atrributes_roms);
        
        for(String atrr: atrributes){
            System.out.println(atrr);
        }
        
        
        int feature_matrix[][] = new int[productList.size()][atrributes.size()];
        for(int i = 0; i < productList.size(); i++){
            for(int j = 0; j < atrributes.size(); j++){
                feature_matrix[i][j] = 0;
            }
        }
        
        System.out.println("=======================================================");
        for(int i = 0; i < productList.size(); i++){
            for(int j = 0; j < atrributes.size(); j++){
                System.out.print(feature_matrix[i][j] + " ");
            }
            System.out.println();
        }
        
        for(int i = 0; i < productList.size(); i++){
            Product product = productList.get(i);
            for(int j = 0; j < atrributes.size(); j++){
                if(product.getCategory().equals(atrributes.get(j))){feature_matrix[i][j] = 2;} //Trong so duoc thay doi thanh 5 thay vi 1
                if(product.getBrand().equals(atrributes.get(j))){feature_matrix[i][j] = 1;}
                if(product.getDemand().equals(atrributes.get(j))){feature_matrix[i][j] = 1;}
                
                //Kiem tra atrr co phai la gia khong(min-max) va gia san pham phai nam trong khoang gia
                if(atrributes.get(j).equals(atrributes_prices.get(0)) && (product.getPrice() >= min_price && product.getPrice() <= max_price))
                {
                    feature_matrix[i][j] = 1;
                }
                
                if((product.getDiscount()+"").equals(atrributes.get(j))){feature_matrix[i][j] = 1;}
                for(String ram : product.getRams()){
                    if(ram.equals(atrributes.get(j))){feature_matrix[i][j] = 1;}
                }
                for(String rom : product.getRoms()){
                    if(rom.equals(atrributes.get(j))){feature_matrix[i][j] = 1;}
                }
            }
        }
        
        
        System.out.println("=======================================================");
        for(int i = 0; i < productList.size(); i++){
            for(int j = 0; j < atrributes.size(); j++){
                System.out.print(feature_matrix[i][j] + " ");
            }
            System.out.println();
        }
        
        
        ArrayList<int[]> vectors = new ArrayList<int[]>();
        
        for(int i = 0; i < productList.size(); i++){
            int vector[] = new int[atrributes.size()];
            for(int j = 0; j < atrributes.size(); j++){
                vector[j] = feature_matrix[i][j];
            }
            vectors.add(vector);
        }
        
        System.out.println("=======================================================\n");
        System.out.println("Tinh do tuong dong giua vector cuoi cung (Virtual Product) va cac vector con lai\n");
        
        // Mang chua do tuong dong
        double []cosines = new double[vectors.size()];
        // Mang chua id san pham
        int []ids = new int[vectors.size()];
        //Lay vector cuoi
        int []vector0 = vectors.get(vectors.size() - 1);
        for(int k = 0; k < vectors.size(); k++){
            int []vector = vectors.get(k);
            System.out.print("Vector 0: ");
            for(int value: vector0){
                System.out.print(value+ " ");
            }
            System.out.print("\nVector "+k+": ");
            for(int value: vector){
                System.out.print(value+ " ");
            }
            System.out.println();
            
            System.out.println("Khoang cach cosine cua vector cuoi cung (Virtual Product) va vector "+k);

            // Tính dot product
            double dotProduct = calculateDotProduct(vector0, vector);

            // Tính độ dài Euclidean của mỗi vector
            double magnitude0 = calculateMagnitude(vector0);
            double magnitude1 = calculateMagnitude(vector);

            // Tính cosine similarity
            double cosineSimilarity = dotProduct / (magnitude0 * magnitude1);

            // In kết quả
            System.out.println("Cosine Similarity: " + cosineSimilarity);
            System.out.println();
            
            //Them do tuong dong vao mang tuong dong
            cosines[k] = cosineSimilarity;
            //Them id vao mang ids
            ids[k] = productList.get(k).getId();
        }
        
        System.out.println("Mang chua do tuong dong chua sap xep: ");
        for(int i = 0; i < cosines.length; i++){
            System.out.print(cosines[i] + " ");
        }
        System.out.println();
        System.out.println("Mang chua id san pham chua sap xep: ");
        for(int i = 0; i < ids.length; i++){
            System.out.print(ids[i] + " ");
        }
        
        for(int i = 0; i < cosines.length - 1; i++){
            for(int j = i + 1; j < cosines.length; j++){
                if(cosines[i] < cosines[j]){
                    //Hoan doi cosines
                    double temp = cosines[i];
                    cosines[i] = cosines[j];
                    cosines[j] = temp;
                    //Hoan doi ids
                    int temp_id = ids[i];
                    ids[i] = ids[j];
                    ids[j] = temp_id;
                }
            }
        }
        
        System.out.println();
        System.out.println("Mang chua do tuong dong da sap xep: ");
        for(int i = 0; i < cosines.length; i++){
            System.out.print(cosines[i] + " ");
        }
        System.out.println();
        System.out.println("Mang chua id san pham da sap xep: ");
        for(int i = 0; i < ids.length; i++){
            System.out.print(ids[i] + " ");
        }
        
        System.out.println();
        System.out.println("Ket qua: ");
        for(int id : ids){
            for(Product product : productList){
                if(product.getId() == id){
                    System.out.print(product.getName() + "    ");
                    break;
                }
            }  
        }
        
        
    }
    
}
