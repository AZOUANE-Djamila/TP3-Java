import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import model.Carre;
import model.Cercle;
import model.Cube;
import model.FormeGeometrique;
import model.Point;
import model.Rectangle;
import model.Sphere;
import model.Triangle;
import model.TriangleEquilateral;
import model.TriangleIsocele;

public class main {

	public static void main(String[] args) {
		try {
			
			Point p1 = new Point(-5,-5);
		}
		catch(Exception e){
			System.out.println("Une erreur de création du point p1 : "+e);
		}
		

		// Créer un deuxième point aux coordonnées (2, 2), changer son abscisse à -10 et faire le nécessaire ;
		Point p2 = new Point(2,2);
		try {
			
			p2.setX(-10);
		}
		catch(Exception e2){
			System.out.println("X est négatif");
		}

		// Créer un troisième point aux coordonnées (3, 3), changer son ordonnée à -20 et faire le nécessaire ;
		Point p3 = new Point(3,3);
		//System.out.println("p3 = "+p3);
		
		try {
			
			p3.setY(-20);
		}
		catch(Exception e3){
			System.out.println("Y n'est pas positif");
		}
		
		// Créer un quatrième point aux coordonnées (4, 4), déplacer-le à (-30,-40) et faire le nécessaire ;
		Point p4 = new Point(4,4);
		try {
			p4.setX(-30);
			p4.setY(-40);
		}
		catch(Exception e4){
			System.out.println("X et Y sont négatifs ");
		}
		
		
		// Afficher le dernière point dans la console ;
		System.out.println("p4 = "+p4);
		
		// Déplacer les point #2 et #3 aux coordonnées (50,60). Comparer-les avec (==) et « equals » ;
		p2.deplacer(50, 60);System.out.println("p2="+p2);
		p3.deplacer(50, 60);System.out.println("p3="+p3);
		
		if (p2==p3) 
			System.out.println("p2=p3");
		else System.out.println("p2!=p3");
		
		if (p2.equals(p3)) 
			System.out.println("p2=p3");
		else System.out.println("p2!=p3");
		
		// Créer un objet de chacune des classes héritant de « FormeGeometrique » en initialisant tous leurs attributs
		FormeGeometrique carre = new Carre(p4,5);
		FormeGeometrique rect = new Rectangle(p4, 5,4);
		FormeGeometrique cercle = new Cercle(p2,5);
		FormeGeometrique triangleEq = new TriangleEquilateral(p2,5);
		FormeGeometrique triangleIs = new TriangleIsocele(p2,6,8);
		FormeGeometrique cube = new Cube(p3,6);
		FormeGeometrique sphere = new Sphere(5);
		
		
		ArrayList<FormeGeometrique> fgArray = new ArrayList<FormeGeometrique>();
		fgArray.add(carre);
		fgArray.add(rect);
		fgArray.add(cercle);
		fgArray.add(triangleEq);
		fgArray.add(triangleIs);
		fgArray.add(cube);
		fgArray.add(sphere);
		
		// Parcourir ce tableau et afficher dans la console la superficie de chaque élément
		for (FormeGeometrique fg : fgArray) {      
            System.out.println("Superficie de "+fg.getNom()+" est : "+fg.superficie());
        } 
		
		
		// Trier le tableau par rapport à la superficie de ses éléments 
		//tri par Superficie
		Comparator<FormeGeometrique> comparerParSuperficie = new Comparator<FormeGeometrique>() {
			@Override
			public int compare(FormeGeometrique fg1, FormeGeometrique fg2) {
				return (int) (fg1.superficie() - fg2.superficie());
			}
			
		};
		//Comparator<FormeGeometrique> c = (fg1, fg2) -> fg1.superficie().
		fgArray.sort(comparerParSuperficie);
		
		System.out.println("\n Superficie aprés sort \n ");
		for (FormeGeometrique fg : fgArray) {      
            System.out.println("Superficie de "+fg.getNom()+" est : "+fg.superficie());
        } 
		
		// Trier le tableau par rapport à la distance avec le point (0,0) 
		System.out.println("Trier le tableau par rapport à la distance avec le point (0,0) \n");
		ArrayList<Object> lDist = fgArray.stream()
			.sorted(Comparator.comparing(o -> Distance((FormeGeometrique) o)))
			.collect(Collectors.toCollection(ArrayList::new));
	
		lDist.forEach(e->System.out.println(((FormeGeometrique) e).getNom()+" = "+Distance((FormeGeometrique) e)));
		
		
		// Définir un tableau qui ne peut contenir que des cubes ou des sphères. Stocker à l’intérieur de ce tableau le cube et la sphère précédemment créés ;
         
		ArrayList<FormeGeometrique> fgCubeSphere = new ArrayList<FormeGeometrique>();
				fgCubeSphere=(ArrayList<FormeGeometrique>) fgArray.stream()
									.filter(c -> c.getClass().equals(Cube.class) || c.getClass().equals(Sphere.class))
									.collect(Collectors.toList());
				fgCubeSphere.forEach(e->System.out.println(((FormeGeometrique) e).getNom()+" = "+(((FormeGeometrique) e).getClass())));
	}

	//Distance entre deux forme geométrique avec le point (0,0) 
		public static double Distance(FormeGeometrique fg) {
			
	       double dx = fg.getOrigine().getX(); 
	       double dy = fg.getOrigine().getY();
	        
	        return Math.sqrt(dx * 2 + dy * 2);
	    }

}
