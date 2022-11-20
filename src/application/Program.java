package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import entities.Sale;

public class Program {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		Locale.setDefault(Locale.US);
		
		System.out.print("Enter the file path: ");
		String path = sc.nextLine();
		
		List<Sale> list = new ArrayList<>();
		
		try (BufferedReader br = new BufferedReader(new FileReader(path))){
			
			System.out.println("Firts five sales of 2016 with highest average price");
			
			String line = br.readLine();
			while (line != null) {
				String[]fields = line.split(",");
				list.add(new Sale(Integer.parseInt(fields[0]),Integer.parseInt(fields[1]),
						fields[2],Integer.parseInt(fields[3]), Double.parseDouble(fields[4])));
				line = br.readLine();
			}
			
			Comparator<Sale> comp = (x,y) -> x.averagePrice()
					.compareTo(y.averagePrice());

			  		list.stream()
			  		.sorted(comp.reversed())
					.filter(x -> x.getYear() == 2016)		
					.limit(5)
					.forEach(System.out::println);
			  		
			  		String name = "Logan";
			  		
			  		double sum = list.stream().filter(x -> x.getMonth() == 1 || x.getMonth() == 7)
			  				.filter(x -> x.getSeller().equalsIgnoreCase(name))
			  				.map(x -> x.getTotal())
			  				.reduce(0.0, (x,y) -> x + y );
			  		
			  		System.out.println("\nTotal amount sold by seller logan in months 1 and 7 = " + sum);
			
		}catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		}
		sc.close();
	}
}