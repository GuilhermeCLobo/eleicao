import java.rmi.Remote;
import java.rmi.RemoteException;
import java.time.LocalTime;

public interface ProcessosInterface extends Remote 
{
	public void SalvarOutrosProcessos(ProcessosInterface Processo1, ProcessosInterface Processo2, 
	ProcessosInterface Processo3) throws RemoteException;

	public LocalTime RetornarRelogio() throws RemoteException;

	public void IniciarTrabalhos() throws RemoteException;

	public void UpdateRelogio(LocalTime r) throws RemoteException;
	
	public void Comecar(Boolean Inicio) throws RemoteException;

}
