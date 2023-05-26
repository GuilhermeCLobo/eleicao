import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class Client implements Runnable 
{
	private ProcessosInterface I;

    public Client(ProcessosInterface I) {
        this.I = I;
    }

    @Override
    public void run() {
        try
		{
			I.IniciarTrabalhos();
		}
		catch( RemoteException e ) {e.printStackTrace();}
    }
	public static void main( String args[] ) {
		try {
			ProcessosInterface m = (ProcessosInterface) Naming.lookup("rmi://127.0.0.1:5000/Processo" );
			ProcessosInterface n = (ProcessosInterface) Naming.lookup("rmi://127.0.0.1:5001/Processo" );
			ProcessosInterface o = (ProcessosInterface) Naming.lookup("rmi://127.0.0.1:5002/Processo" );
			ProcessosInterface p = (ProcessosInterface) Naming.lookup("rmi://127.0.0.1:5003/Processo" );
			
			m.SalvarOutrosProcessos(n, o, p);
			n.SalvarOutrosProcessos(m, o, p);
			o.SalvarOutrosProcessos(n, m, p);
			p.SalvarOutrosProcessos(n, o, m);

			Client minhaRunnable1 = new Client(m);
			Thread minhaThread1 = new Thread(minhaRunnable1);

			Client minhaRunnable2 = new Client(n);
			Thread minhaThread2 = new Thread(minhaRunnable2);

			Client minhaRunnable3 = new Client(o);
			Thread minhaThread3 = new Thread(minhaRunnable3);

			Client minhaRunnable4 = new Client(p);
			Thread minhaThread4 = new Thread(minhaRunnable4);

			minhaThread1.start();
			minhaThread2.start();
			minhaThread3.start();
			minhaThread4.start();
			
		}
		catch( MalformedURLException e ) {e.printStackTrace();}
		catch( RemoteException e ) {e.printStackTrace();}
		catch( NotBoundException e ) {e.printStackTrace();}
		catch( Exception e ) {e.printStackTrace();}
	}
}