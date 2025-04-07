package app.ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import app.domain.models.*;
import app.domain.services.*;

import java.sql.Timestamp;
import java.util.List;
import java.util.Scanner;

@Component
public class ConsoleApplication implements CommandLineRunner{
	@Autowired
    private AdministratorService administratorService;

    @Autowired
    private VeterinarianService veterinarianService;

    @Autowired
    private SellerService sellerService;

    private Scanner scanner = new Scanner(System.in);
    private User currentUser;
    private String currentRole;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("¡Bienvenido al Sistema de Gestión de Veterinaria!");
        
        boolean exit = false;
        while (!exit) {
            showLoginMenu();
            int option = readIntOption();
            
            switch (option) {
                case 1:
                    loginUser();
                    break;
                case 2:
                    createOwner();
                    break;
                case 0:
                    exit = true;
                    System.out.println("¡Gracias por usar el Sistema de Gestión de Veterinaria!");
                    break;
                default:
                    System.out.println("Opción inválida. Intente nuevamente.");
            }
        }
    }

    private void showLoginMenu() {
        System.out.println("\n===== MENÚ PRINCIPAL =====");
        System.out.println("1. Iniciar sesión");
        System.out.println("2. Registrarse como dueño de mascota");
        System.out.println("0. Salir");
        System.out.print("Seleccione una opción: ");
    }

    private void loginUser() {
        System.out.print("Usuario: ");
        String username = scanner.nextLine();
        System.out.print("Contraseña: ");
        String password = scanner.nextLine();
        
        try {
            if (username.equals("admin") && password.equals("admin")) {
                currentRole = "Administrator";
                showAdministratorMenu();
            } else if (username.equals("vet") && password.equals("vet")) {
                currentRole = "Veterinarian";
                showVeterinarianMenu();
            } else if (username.equals("seller") && password.equals("seller")) {
                currentRole = "Seller";
                showSellerMenu();
            } else {
                System.out.println("Credenciales incorrectas");
            }
        } catch (Exception e) {
            System.out.println("Error al iniciar sesión: " + e.getMessage());
        }
    }

    private void createOwner() {
        System.out.println("\n===== REGISTRO DE DUEÑO =====");
        System.out.print("Cédula: ");
        long document = Long.parseLong(scanner.nextLine());
        System.out.print("Nombre: ");
        String name = scanner.nextLine();
        System.out.print("Edad: ");
        int age = Integer.parseInt(scanner.nextLine());
        
        Person owner = new Person(document, name, age, "Owner");
        
        try {
            veterinarianService.createOwner(owner);
            System.out.println("Dueño registrado exitosamente");
        } catch (Exception e) {
            System.out.println("Error al registrar dueño: " + e.getMessage());
        }
    }

    private void showAdministratorMenu() throws Exception {
        boolean back = false;
        while (!back) {
            System.out.println("\n===== MENU ADMINISTRADOR =====");
            System.out.println("1. Registrar vendedor");
            System.out.println("2. Registrar veterinario");
            System.out.println("3. Cambiar rol");
            System.out.println("0. Cerrar sesión");
            System.out.print("Seleccione una opción: ");
            
            int option = readIntOption();
            switch (option) {
                case 1:
                    registerSeller();
                    break;
                case 2:
                    registerVeterinarian();
                    break;
                case 3:
                    changeRole();
                    back = true;
                    break;
                case 0:
                    back = true;
                    break;
                default:
                    System.out.println("Opción inválida. Intente nuevamente.");
            }
        }
    }

    private void showVeterinarianMenu() throws Exception {
        boolean back = false;
        while (!back) {
            System.out.println("\n===== MENU VETERINARIO =====");
            System.out.println("1. Registrar mascota");
            System.out.println("2. Crear historia clínica");
            System.out.println("3. Crear orden médica");
            System.out.println("4. Anular orden médica");
            System.out.println("5. Consultar historia clínica");
            System.out.println("6. Consultar órdenes");
            System.out.println("7. Cambiar rol");
            System.out.println("0. Cerrar sesión");
            System.out.print("Seleccione una opción: ");
            
            int option = readIntOption();
            switch (option) {
                case 1:
                    registerPet();
                    break;
                case 2:
                    createMedicalRecord();
                    break;
                case 3:
                    createOrder();
                    break;
                case 4:
                    cancelOrder();
                    break;
                case 5:
                    viewMedicalRecords();
                    break;
                case 6:
                    consultOrders();
                    break;
                case 7:
                    changeRole();
                    back = true;
                    break;
                case 0:
                    back = true;
                    break;
                default:
                    System.out.println("Opción inválida. Intente nuevamente.");
            }
        }
    }

    private void showSellerMenu() throws Exception {
        boolean back = false;
        while (!back) {
            System.out.println("\n===== MENU VENDEDOR =====");
            System.out.println("1. Vender medicamento por orden");
            System.out.println("2. Vender otro producto");
            System.out.println("3. Consultar órdenes");
            System.out.println("4. Cambiar rol");
            System.out.println("0. Cerrar sesión");
            System.out.print("Seleccione una opción: ");
            
            int option = readIntOption();
            switch (option) {
                case 1:
                    sellMedicine();
                    break;
                case 2:
                    sellOtherProduct();
                    break;
                case 3:
                    consultOrders();
                    break;
                case 4:
                    changeRole();
                    back = true;
                    break;
                case 0:
                    back = true;
                    break;
                default:
                    System.out.println("Opción inválida. Intente nuevamente.");
            }
        }
    }
    
    private void registerSeller() {
        try {
            System.out.println("\n===== REGISTRO DE VENDEDOR =====");
            System.out.print("Cédula: ");
            long document = Long.parseLong(scanner.nextLine());
            System.out.print("Nombre: ");
            String name = scanner.nextLine();
            System.out.print("Edad: ");
            int age = Integer.parseInt(scanner.nextLine());
            System.out.print("Usuario: ");
            String username = scanner.nextLine();
            System.out.print("Contraseña: ");
            String password = scanner.nextLine();
            
            User seller = new User(document, name, age, username, password, 1235,"Seller");
            administratorService.createSeller(seller);
            System.out.println("Vendedor registrado exitosamente");
        } catch (Exception e) {
            System.out.println("Error al registrar vendedor: " + e.getMessage());
        }
    }
    
    private void registerVeterinarian() {
        
    }

    private void registerPet() {
        System.out.println("\n===== REGISTRO DE MASCOTA =====");
        try {
            System.out.print("Nombre: ");
            String name = scanner.nextLine();
            System.out.print("Cédula del dueño: ");
            long ownerDocument = Long.parseLong(scanner.nextLine());
            System.out.print("Edad: ");
            int age = Integer.parseInt(scanner.nextLine());
            System.out.print("Especie (perro/gato/ave/pez): ");
            String species = scanner.nextLine();
            System.out.print("Raza: ");
            String race = scanner.nextLine();
            System.out.print("Peso: ");
            double weight = Double.parseDouble(scanner.nextLine());
            System.out.print("Características (color, tamaño): ");
            String characteristics = scanner.nextLine();
            
            Person owner = new Person();
            owner.setDocument(ownerDocument);
            
            Pet pet = new Pet();
            pet.setName(name);
            pet.setOwner(owner);
            pet.setAge(age);
            pet.setSpecies(species);
            pet.setRace(race);
            pet.setWeight(weight);
            pet.setCaracteristicas(characteristics);
            
            veterinarianService.createPet(pet);
            System.out.println("Mascota registrada exitosamente con ID: " + pet.getPetId());
        } catch (Exception e) {
            System.out.println("Error al registrar mascota: " + e.getMessage());
        }
    }

    private void createMedicalRecord() {
        // creación de historia clínica
    }

    private void createOrder() {
        // creación de orden médica
    }

    private void cancelOrder() {
        // anular orden
    }

    private void viewMedicalRecords() {
        // ver historias clínicas
    }

    private void consultOrders() {
        // consultar órdenes
    }

    private void sellMedicine() {
        // vender medicamentos
    }

    private void sellOtherProduct() {
        // vender otros productos
    }

    private int readIntOption() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1; // Valor inválido
        }
    }

    private void changeRole() {
        System.out.println("\n===== CAMBIO DE ROL =====");
        System.out.println("1. Administrador");
        System.out.println("2. Veterinario");
        System.out.println("3. Vendedor");
        System.out.print("Seleccione un rol: ");
        
        int option = readIntOption();
        try {
            switch (option) {
                case 1:
                    currentRole = "Administrator";
                    showAdministratorMenu();
                    break;
                case 2:
                    currentRole = "Veterinarian";
                    showVeterinarianMenu();
                    break;
                case 3:
                    currentRole = "Seller";
                    showSellerMenu();
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        } catch (Exception e) {
            System.out.println("Error al cambiar de rol: " + e.getMessage());
        }
    }
}
