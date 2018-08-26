package ejercicio.unidadSiete.main;

import java.util.Scanner;

import ejercicio.unidadSiete.combo.ComboPumperFeliz;
import ejercicio.unidadSiete.factory.CargarProductosComboFactory;
import ejercicio.unidadSiete.service.PumperServiceImpl;

public class Main {

	public static void main(String[] args) {
		PumperServiceImpl service = PumperServiceImpl.getSingletonInstance();
		CargarProductosComboFactory factoryCombo = new CargarProductosComboFactory();
		
		Scanner teclado = new Scanner(System.in);
		System.out.println("Para ingresar tipo de combo: 1");
		System.out.println("Para ver stock parcial: ");
		System.out.println("Reporte Parcial de ventas (Solo Jefe de ventas): ");
		System.out.print("Ingrese opcion: ");
		Integer opcionEntrada = teclado.nextInt();
		
		if (opcionEntrada == 1) {
			System.out.println("Combo Pumper Feliz 1");
			System.out.println("Combo Pumper Mediano 2");
			System.out.println("Combo Pumper Grande 3");
			System.out.println("Combo Pumper Super 4");
			System.out.print("Ingrese opcion: ");
			Integer opcionEntradaCombo = teclado.nextInt();
			
			if (opcionEntradaCombo == 1) {
				ComboPumperFeliz combo = new ComboPumperFeliz();
				System.out.print("Para ingresar genero juguete ingrese 1 Hombre / 2 Mujer: ");
				Integer opcionGeneroJuguete = teclado.nextInt();
				System.out.print("Para ingresar tipo de pan ingrese 1 c/centeno / 2 s/centeno: ");
				Integer opcionTipoPan = teclado.nextInt();
				
				combo.setProductosCombos(factoryCombo.obtenerProductosDelCombo(combo, jugueteHombre, opcionalCenteno, opcionalCondimentos, opcionalIngredientes, opcionalCerveza, conGaseosaGrande, conAgua));
			}else if (opcionEntradaCombo == 2) {
				
			}else if (opcionEntradaCombo == 3) {
				
			}else {
				
			}
			
		}else if (opcionEntrada == 2) {
			service.obtenerStockParcial();
		}else {
			
		}
		
		
		teclado.close();
		
	}

}
