# Construccion2LauraUribeDanielAlvarez
 Proyecto Veterinaria

se solicita crear un aplicativo para administrar una veterinaria, la veterinaria presta diferentes servicios
a especies pequeñas (perros, gatos, peces, aves).
En la veterinaria se tendrá el registro de todas las personas que intervienen en ella, en los roles de
administrador, medico veterinario, dueño de mascota y vendedor.
De cada persona se conoce:
- cedula: no pueden haber dos personas registradas con la misma cedula.
- nombre
- edad
- rol
Cada administrador, vendedor y medico veterinario cuenta con un usuario y contraseña.
Unicamente los administradores pueden registrar vendedores y médicos veterinarios.
Para poder registrar la atención de una mascota es necesario que este en el sistema asociada a un dueño.
(para este caso no se tomara en cuenta animales sin dueño por simplicidad).
De cada mascota se conoce:
-nombre
-cedula del dueño
-edad
- id (debe ser único, asignado por el sistema, se utilizara para referenciar la historia clínica)
- especie
-raza
- características (color, tamaño)
- peso
Trabajo final construccion de software2
La historia clínica puede ser consultada y editada unicamente por médicos veterinarios.
cada registro de la historia clínica de cada mascota sera otro diccionario que tendrá la siguiente
información:
-fecha (sera la clave del registro particular de la mascota)
- medico veterinario que lo atendió
- motivo de consulta
- sintomatologia
-diagnostico
- procedimiento (en caso de aplicarse, puede ser, desparasitación, fisioterapia, vacunación, examenes,
cirugía, etc)
- medicamento (en caso de recetarse)
- dosis de medicamento
- IDorden (se usara para la autorización de venta de medicamentos, debe ser unico)
- historial de vacunación(se registra nombre de la vacuna en caso de ser un procedimiento de
vacunación, aplica solo a perros y gatos)
-medicamentos a los que presenta alergia (puede variar respecto al tiempo)
- detalle del procedimiento
- anulación orden
cuando un medico veterinario receta medicamentos genera una orden, cada una de estas contiene
-id orden debe ser único
- id mascota
-cedula dueño
-cedula veterinario que ordena
- nombre medicamento dosis
-fecha de generación (fecha en la que se registra en la historia clinica)
tanto los veterinarios como los vendedores pueden acceder al listado de ordenes para su consulta, pero
solo los veterinarios las pueden crear o anular, cuando se anula se deja registro en historia medica de
que ha sido anulada la orden.
Los vendedores solo pueden suministrar medicamentos mediante una orden, pero pueden vender otro
tipo de productos.
Al hacer la venta de cualquier tipo de producto incluyendo medicamentos se genera una factura la cual
debe contener
- id factura (único)
- id mascota
- id dueño
- id orden (en caso de ser venta de medicamento)
- nombre del producto
- valor
- cantidad
- fecha
para el ejercicio actual no se tomara en cuenta inventarios ni flujos de dinero en caja.
El programa debe permitir
1. registro de veterinarios, vendedores, dueños y mascotas. (se recomienda dejar un usuario
administrador ya creado en el código por facilidad)
2. registro de historia clínica por parte de los médicos veterinarios, incluyendo creación y anulación de
ordenes.
3. registro de facturas de venta de medicamentos generando facturas.
4. consultar la historia clínica de una mascota para el caso de los veterinarios.
5. consulta de ordenes para el caso de veterinarios y vendedores.
6. el programa debe permitir moverse en sesión entre roles sin perder la información registrada en
memoria (recuerde que en el momento no se cuenta con base de datos)
Se recomienda utilizar vectores a modo de tablas en la clase que represente la veterinaria para
almacenar la información en memoria.