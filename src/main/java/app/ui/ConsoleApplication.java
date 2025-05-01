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
            // Intentar autenticar usando el servicio
            User authenticatedUser = administratorService.authenticateUser(username, password);
            
            if (authenticatedUser != null) {
                // Usuario autenticado correctamente
                currentUser = authenticatedUser;
                currentRole = authenticatedUser.getRole();
                
                System.out.println("Bienvenido, " + authenticatedUser.getName());
                
                // Mostrar el menú correspondiente según el rol
                switch (currentRole) {
                    case "Administrator":
                        showAdministratorMenu();
                        break;
                    case "Veterinarian":
                        showVeterinarianMenu();
                        break;
                    case "Seller":
                        showSellerMenu();
                        break;
                    default:
                        System.out.println("Rol no reconocido: " + currentRole);
                }
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
            
            // Primero crear la persona
            Person person = new Person(document, name, age, "Seller");
            
            // Luego crear el usuario relacionado con esa persona
            User seller = new User();
            seller.setDocument(document);  // Este es el enlace con la persona
            seller.setName(name);
            seller.setAge(age);
            seller.setUserName(username);
            seller.setPassword(password);
            seller.setRole("Seller");
            
            administratorService.createSeller(seller);
            System.out.println("Vendedor registrado exitosamente");
        } catch (Exception e) {
            System.out.println("Error al registrar vendedor: " + e.getMessage());
        }
    }
    
    private void registerVeterinarian() {
        try {
            System.out.println("\n===== REGISTRO DE VETERINARIO =====");
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
            
            // Primero crear la persona
            Person person = new Person(document, name, age, "Veterinarian");
            
            // Luego crear el usuario relacionado con esa persona
            User veterinarian = new User();
            veterinarian.setDocument(document);
            veterinarian.setName(name);
            veterinarian.setAge(age);
            veterinarian.setUserName(username);
            veterinarian.setPassword(password);
            veterinarian.setRole("Veterinarian");
            
            administratorService.createVeterinarian(veterinarian);
            System.out.println("Veterinario registrado exitosamente");
        } catch (Exception e) {
            System.out.println("Error al registrar veterinario: " + e.getMessage());
        }
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
        try {
            System.out.println("\n===== CREACIÓN DE HISTORIA CLÍNICA =====");
            System.out.print("ID de la mascota: ");
            long petId = Long.parseLong(scanner.nextLine());
    
            // Preguntar si desea asociar una orden existente
            System.out.print("¿Desea asociar esta historia clínica a una orden médica existente? (S/N): ");
            String hasOrder = scanner.nextLine();
            
            // Preparar la orden si el usuario quiere asociarla
            Order order = null;
            if (hasOrder.equalsIgnoreCase("S")) {
                System.out.print("ID de la orden médica: ");
                long orderId = Long.parseLong(scanner.nextLine());
                
                // Verificar que la orden existe
                Order orderQuery = new Order();
                orderQuery.setOrderId(orderId);
                
                try {
                    order = sellerService.ConsultOrder(orderQuery);
                    if (order == null) {
                        System.out.println("No se encontró la orden especificada. Se creará la historia sin asociar a una orden.");
                    } else {
                        System.out.println("Orden encontrada: Medicamento: " + order.getMedicine() + ", Dosis: " + order.getDose());
                    }
                } catch (Exception e) {
                    System.out.println("Error al buscar la orden: " + e.getMessage() + ". Se creará la historia sin asociar a una orden.");
                    order = null;
                }
            }
    
            System.out.print("Razón de la visita: ");
            String reason = scanner.nextLine();
            System.out.print("Sintomatología: ");
            String symptomatology = scanner.nextLine();
            System.out.print("Diagnóstico: ");
            String diagnostic = scanner.nextLine();
            System.out.print("Procedimiento: ");
            String procedure = scanner.nextLine();
            System.out.print("Medicamento: ");
            String medicine = scanner.nextLine();
            System.out.print("Dosis: ");
            String dose = scanner.nextLine();
            System.out.print("Vacunación (si aplica): ");
            String vaccination = scanner.nextLine();
            System.out.print("Alergias a medicamentos: ");
            String allergyMedication = scanner.nextLine();
            System.out.print("Detalles del procedimiento: ");
            String procedureDetails = scanner.nextLine();
            
            // Crear la historia clínica
            MedicalRecord medicalRecord = new MedicalRecord();
            Pet pet = new Pet();
            pet.setPetId(petId);
            medicalRecord.setPet(pet);
            medicalRecord.setDate(new Timestamp(System.currentTimeMillis()));
            medicalRecord.setReason(reason);
            medicalRecord.setSymptomatology(symptomatology);
            medicalRecord.setDiagnostic(diagnostic);
            medicalRecord.setProcedure(procedure);
            medicalRecord.setMedicine(medicine);
            medicalRecord.setDose(dose);
            medicalRecord.setVaccination(vaccination);
            medicalRecord.setAllergyMedication(allergyMedication);
            medicalRecord.setProcedureDetails(procedureDetails);
            medicalRecord.setVeterinarian(currentUser);
            
            // Asociar la orden si existe
            if (order != null) {
                medicalRecord.setOrder(order);
            }
            
            veterinarianService.createMedicalRecord(medicalRecord);
            
            if (order != null) {
                System.out.println("Historia clínica creada exitosamente y asociada a la orden #" + order.getOrderId());
            } else {
                System.out.println("Historia clínica creada exitosamente");
            }
        } catch (Exception e) {
            System.out.println("Error al crear historia clínica: " + e.getMessage());
        }
    }

    private void createOrder() {
        try {
            System.out.println("\n===== CREACIÓN DE ORDEN MÉDICA =====");
            System.out.print("ID de la mascota: ");
            long petId = Long.parseLong(scanner.nextLine());
            System.out.print("Cédula del dueño: ");
            long ownerId = Long.parseLong(scanner.nextLine());
            System.out.print("Medicamento: ");
            String medicine = scanner.nextLine();
            System.out.print("Dosis: ");
            String dose = scanner.nextLine();
            
            Order order = new Order();
            Pet pet = new Pet();
            pet.setPetId(petId);
            order.setPet(pet);
            
            Person owner = new Person();
            owner.setDocument(ownerId);
            order.setOwner(owner);
            
            order.setVeterinarian(currentUser);
            order.setMedicine(medicine);
            order.setDose(dose);
            order.setDate(new Timestamp(System.currentTimeMillis()));
            order.setCancelled(false);
            
            veterinarianService.createOrder(order);
            System.out.println("Orden médica creada exitosamente");
        } catch (Exception e) {
            System.out.println("Error al crear orden médica: " + e.getMessage());
        }
    }

    private void cancelOrder() {
        try {
            System.out.println("\n===== ANULAR ORDEN MÉDICA =====");
            System.out.print("ID de la orden: ");
            long orderId = Long.parseLong(scanner.nextLine());
            
            // Crear un objeto Order con el ID para anular
            Order order = new Order();
            
            // Usar el usuario actualmente logueado como veterinario
            order.setVeterinarian(currentUser);
            order.setOrderId(orderId);
            
            veterinarianService.cancelOrder(order);
            System.out.println("Orden médica anulada exitosamente");
        } catch (Exception e) {
            System.out.println("Error al anular orden: " + e.getMessage());
        }
    }

    private void viewMedicalRecords() {
        try {
            System.out.println("\n===== CONSULTA DE HISTORIAS CLÍNICAS =====");
            System.out.print("ID de la mascota: ");
            long petId = Long.parseLong(scanner.nextLine());
            
            // Crear un objeto Pet para la búsqueda
            Pet pet = new Pet();
            pet.setPetId(petId);
            
            // Usar el método correcto del servicio
            List<MedicalRecord> records = veterinarianService.searchAllMedicalRecordByPetId(pet);
            
            if (records == null || records.isEmpty()) {
                System.out.println("No se encontraron historias clínicas para esta mascota");
                return;
            }
            
            System.out.println("\nHistorias Clínicas:");
            for (MedicalRecord record : records) {
                System.out.println("======================================");
                System.out.println("ID: " + record.getMedicalRecordId());
                System.out.println("Fecha: " + record.getDate());
                System.out.println("Razón: " + record.getReason());
                System.out.println("Sintomatología: " + record.getSymptomatology());
                System.out.println("Diagnóstico: " + record.getDiagnostic());
                System.out.println("Procedimiento: " + record.getProcedure());
                System.out.println("Medicamento: " + record.getMedicine());
                System.out.println("Dosis: " + record.getDose());
                System.out.println("======================================");
            }
        } catch (Exception e) {
            System.out.println("Error al consultar historias clínicas: " + e.getMessage());
        }
    }

    private void consultOrders() {
        try {
            System.out.println("\n===== CONSULTA DE ÓRDENES MÉDICAS =====");
            System.out.print("ID de la orden: ");
            long orderId = Long.parseLong(scanner.nextLine());
            
            // Crear objeto Order para consultar
            Order orderQuery = new Order();
            orderQuery.setOrderId(orderId);
            
            // Consultar la orden usando el método apropiado
            Order order = sellerService.ConsultOrder(orderQuery);
            
            if (order == null) {
                System.out.println("No se encontró la orden especificada.");
                return;
            }
            
            // Mostrar detalles de la orden
            System.out.println("\nDetalle de la orden:");
            System.out.println("======================================");
            System.out.println("ID: " + order.getOrderId());
            if (order.getPet() != null) {
                System.out.println("Mascota: " + order.getPet().getName() + " (ID: " + order.getPet().getPetId() + ")");
            }
            if (order.getOwner() != null) {
                System.out.println("Dueño: " + order.getOwner().getName() + " (Cédula: " + order.getOwner().getDocument() + ")");
            }
            System.out.println("Fecha: " + order.getDate());
            System.out.println("Medicamento: " + order.getMedicine());
            System.out.println("Dosis: " + order.getDose());
            System.out.println("Estado: " + (order.isCancelled() ? "ANULADA" : "ACTIVA"));
            System.out.println("======================================");
            
        } catch (Exception e) {
            System.out.println("Error al consultar órdenes: " + e.getMessage());
        }
    }

    private void sellMedicine() {
        try {
            System.out.println("\n===== VENTA DE MEDICAMENTO POR ORDEN =====");
            System.out.print("ID de la orden médica: ");
            long orderId = Long.parseLong(scanner.nextLine());
            
            // Crear objeto Order para consulta
            Order orderQuery = new Order();
            orderQuery.setOrderId(orderId);
            
            // Consultar orden
            Order order = sellerService.ConsultOrder(orderQuery);
            
            if (order == null) {
                System.out.println("No se encontró la orden médica especificada.");
                return;
            }
            
            if (order.isCancelled()) {
                System.out.println("Esta orden médica ha sido anulada y no se puede procesar.");
                return;
            }
            
            // Mostrar detalles de la orden
            System.out.println("\nDetalle de la orden:");
            if (order.getPet() != null) {
                System.out.println("Mascota: " + order.getPet().getName());
            }
            if (order.getOwner() != null) {
                System.out.println("Dueño: " + order.getOwner().getName());
            }
            System.out.println("Medicamento: " + order.getMedicine());
            System.out.println("Dosis: " + order.getDose());
            
            // Confirmar venta
            System.out.print("\n¿Confirmar venta del medicamento? (S/N): ");
            String confirm = scanner.nextLine();
            
            if (!confirm.equalsIgnoreCase("S")) {
                System.out.println("Venta cancelada.");
                return;
            }
            
            // Solicitar cantidad y valor
            System.out.print("Cantidad: ");
            int amount = Integer.parseInt(scanner.nextLine());
            System.out.print("Valor unitario: ");
            double value = Double.parseDouble(scanner.nextLine());
            
            // Crear la factura
            Bill bill = new Bill();
            bill.setPetId(order.getPet());
            bill.setOrderId(order);
            bill.setProductName(order.getMedicine());
            bill.setValue(value);
            bill.setAmount(amount);
            
            // Vender el medicamento usando la orden y la factura
            sellerService.SellMedicine(order, bill);
            
            // Mostrar resumen
            double total = value * amount;
            System.out.println("\n===== RESUMEN DE VENTA =====");
            System.out.println("Producto: " + order.getMedicine());
            System.out.println("Cantidad: " + amount);
            System.out.println("Valor unitario: $" + value);
            System.out.println("Total: $" + total);
            System.out.println("Venta registrada exitosamente con ID: " + bill.getBillId());
            
        } catch (Exception e) {
            System.out.println("Error al vender medicamento: " + e.getMessage());
        }
    }

    private void sellOtherProduct() {
        try {
            System.out.println("\n===== VENTA DE OTRO PRODUCTO =====");
            
            // Solicitar datos de la mascota
            System.out.print("¿Asociar a una mascota? (S/N): ");
            String hasPet = scanner.nextLine();
            
            Pet pet = null;
            if (hasPet.equalsIgnoreCase("S")) {
                System.out.print("ID de la mascota: ");
                long petId = Long.parseLong(scanner.nextLine());
                
                // Crear objeto Pet para buscar
                pet = new Pet();
                pet.setPetId(petId);
            }
            
            // Solicitar datos del producto
            System.out.print("Nombre del producto: ");
            String productName = scanner.nextLine();
            System.out.print("Cantidad: ");
            int amount = Integer.parseInt(scanner.nextLine());
            System.out.print("Valor unitario: ");
            double value = Double.parseDouble(scanner.nextLine());
            
            // Confirmar venta
            double total = value * amount;
            System.out.println("\nTotal a pagar: $" + total);
            System.out.print("¿Confirmar venta? (S/N): ");
            String confirm = scanner.nextLine();
            
            if (!confirm.equalsIgnoreCase("S")) {
                System.out.println("Venta cancelada.");
                return;
            }
            
            // Crear la factura
            Bill bill = new Bill();
            if (pet != null) {
                bill.setPetId(pet);
            }
            bill.setProductName(productName);
            bill.setValue(value);
            bill.setAmount(amount);
            
            // Usar el método correcto para vender otro producto
            sellerService.sellOtherProduct(bill);
            
            // Mostrar resumen
            System.out.println("\n===== RESUMEN DE VENTA =====");
            System.out.println("Producto: " + productName);
            System.out.println("Cantidad: " + amount);
            System.out.println("Valor unitario: $" + value);
            System.out.println("Total: $" + total);
            System.out.println("Venta registrada exitosamente con ID: " + bill.getBillId());
            
        } catch (Exception e) {
            System.out.println("Error al vender producto: " + e.getMessage());
        }
    }

    private int readIntOption() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1; // Valor inválido
        }
    }
}
