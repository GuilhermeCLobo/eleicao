import java.rmi.registry.*;

public class Processo2 {
	public Processo2() {
		try {
			ProcessosInterface m = new ProcessosImpl();
            Registry registry = LocateRegistry.createRegistry(5001);
        	registry.rebind("Processo", m);
			System.out.println("server ok");
		}
		catch( Exception e ) {
			System.out.println( "Erro: " + e.toString() );
		}
	}

	public static void main(String[] args) {
		new Processo2();
	}
}