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
			ProcessosInterface m = (ProcessosInterface) Naming.lookup("rmi://54.210.172.68:5000/Processo" );
			ProcessosInterface n = (ProcessosInterface) Naming.lookup("rmi://54.152.220.82:5000/Processo" );
			ProcessosInterface o = (ProcessosInterface) Naming.lookup("rmi://44.211.201.228:5000/Processo" );
			ProcessosInterface p = (ProcessosInterface) Naming.lookup("rmi://54.157.228.131:5000/Processo" );
			
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
