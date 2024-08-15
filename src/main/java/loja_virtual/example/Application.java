package loja_virtual.example;

import loja_virtual.example.DAO.ProductDAO;
import loja_virtual.example.Model.Product;

import java.sql.SQLException;
import java.util.Scanner;

public class Application {
    public static void main(String[] args) {
        menu();
    }

    private static void menu() {
        Scanner scan = new Scanner(System.in);
        System.out.print("""
                SELECIONE A OPÇÃO DESEJADA
                
                1 - INSERT
                2 - DELETE
                3 - SELECT By ID
                4 - LIST ALL PRODUCT
                0 - FINISH
                """);
        int escolha = scan.nextInt();
        switch (escolha) {
            case 1:
                insertProduct();
                break;
            case 2:
                deleteProduct();
                break;
            case 3:
                selectProductById();
                break;
            case 4:
                listAllProduct();
                break;
            case 0:
                System.out.println("Programa finalizado!");
                break;
            default:
                System.out.println("Informação inválida");
                menu();
        }
    }
    public static void insertProduct() {
        Scanner scan = new Scanner(System.in);
        System.out.println("INSERT");
        Product product = new Product();
        try {
            System.out.println("Informe o nome do produto: ");
            product.setNome(scan.nextLine());
            System.out.println("Informe a descrição do produto: ");
            product.setDescricao(scan.nextLine());

            ProductDAO.insert(product);

            System.out.println("Deseja inserir outro produto (s/n)");
            String value = scan.next();
            if (value.equalsIgnoreCase("s")) {
                deleteProduct();
            }else if (value.equalsIgnoreCase("n")) {
                menu();
            }else {
                System.out.println("Resposta inválida!");
                menu();
            }

        }catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    public static void deleteProduct() {
        Scanner scan = new Scanner(System.in);
        System.out.println("DELETE");
        Product product = new Product();
        try {
            System.out.println("Informe o ID do produto que deseja deletar");
            product.setId(scan.nextInt());
            ProductDAO.delete(product.getId());

            System.out.println("Deseja deletar outro produto (s/n)");
            String value = scan.next();
            if (value.equalsIgnoreCase("s")) {
                deleteProduct();
            }else if (value.equalsIgnoreCase("n")) {
                menu();
            }else {
                System.out.println("Resposta inválida!");
                menu();
            }

        }catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    public static void selectProductById() {
        Scanner scan = new Scanner(System.in);
        System.out.println("SELECT By ID");
        Product product = new Product();
        try {
            System.out.println("Informe o ID do produto que deseja selecionar");
            product.setId(scan.nextInt());
            ProductDAO.selectById(product.getId());

            System.out.println("Deseja selecionar outro produto (s/n)");
            String value = scan.next();
            if (value.equalsIgnoreCase("s")) {
                selectProductById();
            }else if (value.equalsIgnoreCase("n")) {
                menu();
            }else {
                System.out.println("Resposta inválida!");
                menu();
            }
        }catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

    }
    public static void listAllProduct() {
        Scanner scan = new Scanner(System.in);
        System.out.println("LIST ALL PRODUCT");
        Product product = new Product();
        try {
            ProductDAO.AllProduct();
        }catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
}