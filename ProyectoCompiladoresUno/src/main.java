import java.util.Scanner;


public class main {

	public static void main(String[] args) {
		
		int n,m,e,sw=0,op;
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Ingrese El # de Filas");
		n = sc.nextInt();
		System.out.println("Ingrese El # de Columnas");
		m = sc.nextInt();		
		System.out.println("Ingrese El # de Estados de Aceptación");
		e = sc.nextInt();		
		
		
		String[][] mat = new String[n][m];
		String[] acep = new String[e];

		for(int i=0;i<n;i++){
			for(int j=0;j<m;j++){
				
					if(i==0 && j==0){
						mat[0][0]="&";
					}else{
						System.out.println("Ingrese La Posición "+"["+i+"]["+j+"]");
						mat[i][j]=sc.next();
					}
			}
		}
		
		for(int h=0;h<e;h++){
			System.out.println("Ingrese El Estado De Aceptación #"+h);
			acep[h]=sc.next();
		}
		
		imprimir(mat,n,m,acep,e);
		
		while(sw!=-99){
			
			System.out.println("|--------------Menu De Opciones-----------|");
			System.out.println("| 1 -> Graficar El Automata               |");
			System.out.println("| 2 -> Buscar Palabra                     |");
			System.out.println("| 3-> Salir                               |");
			System.out.println("|-----------------------------------------|");
			
			
			System.out.println("Ingrese La Opción:  ");
			op = sc.nextInt();		
			
			switch(op){
				case 1:
					System.out.println("Graficandooooo");
				break;
				
				case 2:
					buscar(mat,n,m,acep,e);
				break;
				
				case 3:
					System.out.println("Gracias Por Utilizar Mi Programa !!!!!");
					System.out.println("Autor: Jorge Robles");
					System.out.println("(Derechos Reservados)");
					
					sw=-99;
				break;
				
				default:
					System.out.println("Opción No Valida !!!!!");
				break;	
			} 
			
		}
		
	}
	
	public static void imprimir(String[][] mat,int n,int m,String[] acep,int e){
		
		System.out.println("----------------------------Imprimiendo La Matriz---------------------------");
		
		for(int i=0;i<n;i++){
			for(int j=0;j<m;j++){
				System.out.println("["+i+"]["+j+"]="+mat[i][j]+" <-----> ");
			}
		}
		
		System.out.println("-----------------------------Fin De La Matriz--------------------------------\n");
		System.out.println("-----------------------------Estados De Aceptación--------------------------------\n\n");
		
		
		for(int h=0;h<e;h++){
			System.out.println("["+(h+1)+"]="+acep[h]);
		}
		
		System.out.println("------------------------Fin De Los Estados De Aceptación------------------------\n");
		
	}
	
	public static void buscar(String[][] mat,int n,int m,String[] acep,int e){
		
		Scanner sc = new Scanner(System.in);
		int sw=0,c=0,ex,z=1;
		String estado=mat[1][0];
		System.out.println("Ingrese La Palabra A Buscar");
		String palabra=sc.next();
		String tem=palabra;
		
		do{
			System.out.println("Ingrese El Exponente De La Palabra A Buscar");
			ex=sc.nextInt();
			
		}while(ex<=0);
		
		if(ex>1){
			
			while(z<ex){
				palabra+=tem;
				z++;
			}
			
		}
		
		System.out.println("La Palabra A Buscar Es -------> "+palabra);
		
			
		while(c<palabra.length() && sw==0){
			
			String p=String.valueOf(palabra.charAt(c));
			
			estado =nuevoestado(mat,n,m,p,estado);
			//System.out.println(estado);
			
			if(estado.equalsIgnoreCase("Eroor")){
				
				sw=1;
				
			}else{
			
				System.out.println("para "+p+" el estado es "+estado);
			}
			
			c++;
		}
		
		if(sw==0){
			
			
			if(buscarestadodeaceptacion(estado,acep,e)){
				
				System.out.println("La Palabra "+palabra+" Siiiiii Es Aceptada Por El Automata Y Termino En El Estado "+estado);
			
			}else{
				
				System.out.println("La Palabra "+palabra+" No Es Aceptada Por El Automata");
				
			}
			
		}else{
		
			System.out.println("La Palabra "+palabra+" No Es Aceptada Por El Automata");
			
		}
		
	}
	
	public static String nuevoestado(String[][] mat,int n,int m,String p,String estado){
		
		String resul="";
		int nuevoi=0,nuevoj=0;
		
		
			for(int i=1;i<n;i++){
				if(mat[i][0].equals(estado)){
					nuevoi=i;
				}
			}
			
			for(int j=1;j<m;j++){
				if(mat[0][j].equals(p)){
					nuevoj=j;
				}
			}
			
			if(nuevoi==0 || nuevoj==0){
				resul="Eroor";
				System.out.println("I= "+nuevoi+"    J= "+nuevoj);
			}else{
				resul=mat[nuevoi][nuevoj];
			}
		
		return resul;
	}
	
	public static boolean buscarestadodeaceptacion(String estado,String[] acep,int e){
		
		boolean re = false;
		
			for(int h=0;h<e;h++){
				if(acep[h].equals(estado)){
					re=true;
				}
			}
		
		return re;
	}

}
