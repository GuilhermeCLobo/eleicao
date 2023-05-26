import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class Client {
	public Client() {
	}
	public static void main( String args[] ) {
		try {
			ProcessosInterface m = (ProcessosInterface) Naming.lookup("rmi://44.212.5.181:5000/Processo" );
			ProcessosInterface n = (ProcessosInterface) Naming.lookup("rmi://3.95.148.185:5001/Processo" );
			ProcessosInterface o = (ProcessosInterface) Naming.lookup("rmi://3.83.175.131:5002/Processo" );
			ProcessosInterface p = (ProcessosInterface) Naming.lookup("rmi://100.26.97.231:5003/Processo" );
			
			m.SalvarOutrosProcessos(n, o, p);
			n.SalvarOutrosProcessos(m, o, p);
			o.SalvarOutrosProcessos(n, m, p);
			p.SalvarOutrosProcessos(n, o, m);

			m.IniciarTrabalhos();
			n.IniciarTrabalhos();
			o.IniciarTrabalhos();
			p.IniciarTrabalhos();
		}
		catch( MalformedURLException e ) {e.printStackTrace();}
		catch( RemoteException e ) {e.printStackTrace();}
		catch( NotBoundException e ) {e.printStackTrace();}
		catch( Exception e ) {e.printStackTrace();}
	}
}
