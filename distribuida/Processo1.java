import java.rmi.registry.*;

public class Processo1 {
	public Processo1() {
		try {
			ProcessosInterface m = new ProcessosImpl();
            Registry registry = LocateRegistry.createRegistry(5000);
        	registry.rebind("Processo", m);
			System.out.println("server ok");
		}
		catch( Exception e ) {
			System.out.println( "Erro: " + e.toString() );
		}
	}

	public static void main(String[] args) {
		new Processo1();
	}
}