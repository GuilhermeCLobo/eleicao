import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Random;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.Duration;

public class ProcessosImpl extends UnicastRemoteObject implements ProcessosInterface 
{
	ProcessosInterface[] listaProcessos = new ProcessosInterface[3];

	LocalTime Relogio = LocalTime.now();

	DateTimeFormatter formato = DateTimeFormatter.ofPattern("HH:mm:ss");
        
    String horaFormatada;

	public ProcessosImpl() throws RemoteException 
	{
		super();
	}

	public void SalvarOutrosProcessos(ProcessosInterface Processo1, ProcessosInterface Processo2, 
	ProcessosInterface Processo3) throws RemoteException
	{
		listaProcessos[0] = Processo1;
		listaProcessos[1] = Processo2;
		listaProcessos[2] = Processo3;
	}

	public LocalTime RetornarRelogio() throws RemoteException
	{
		return Relogio;
	}

	public void UpdateRelogio(LocalTime r) throws RemoteException
	{
        String horaFormatadaAntiga = Relogio.format(formato);

		String horaFormatadaNova = r.format(formato);
        
        System.out.println("Atualizando o Relogio Interno de: " + horaFormatadaAntiga + " Para: " + horaFormatadaNova);

		Relogio = r;
	}

	public void IniciarTrabalhos() throws RemoteException
	{
		Random random = new Random();
		int numeroAleatorio, PositivoNegativo, AlterarHorario;
		LocalTime RelogioProvisorio;
		Duration diferenca;
		long segundos, media;

		for(int i = 1; i < 11; i++)
		{
			System.out.println("Iteracao " + i + "\n");

			PositivoNegativo = random.nextInt(2);

			AlterarHorario = random.nextInt(11);

			numeroAleatorio = random.nextInt(3);

			if(PositivoNegativo == 0)
			{
				Duration duracao = Duration.ofSeconds(AlterarHorario);
        
				Relogio = Relogio.plus(duracao);
			}

			else
			{
				Duration duracao = Duration.ofSeconds(-AlterarHorario);
        
				Relogio = Relogio.plus(duracao);
			}

			horaFormatada = Relogio.format(formato);

			System.out.println("Relogio Interno: " + horaFormatada + "\n");

			RelogioProvisorio = listaProcessos[numeroAleatorio].RetornarRelogio();

			horaFormatada = RelogioProvisorio.format(formato);

			System.out.println("Mensagem Recebida: " + horaFormatada + "\n");

			diferenca = Duration.between(Relogio, RelogioProvisorio);

        	segundos = diferenca.getSeconds() % 60;

			System.out.println("Diferenca de tempo: " + segundos + " segundos\n");

			if (Math.abs(segundos) > 4)
			{
				System.out.println("Estou dessincronizado, Iniciando processo de sincronia\n");

				media = 0;

				for(int j = 0; j < 3 ; j++)
				{
					media = media + listaProcessos[j].RetornarRelogio().toSecondOfDay();
				}

				media = media + Relogio.toSecondOfDay();

				media = media / 4;

				LocalTime mediaLocalTime = LocalTime.ofSecondOfDay(media);
        
        		System.out.println("Media dos Relogios: " + mediaLocalTime + "\n");

				for(int j = 0; j < 3 ; j++)
				{
					listaProcessos[j].UpdateRelogio(mediaLocalTime);
				}

				UpdateRelogio(mediaLocalTime);
			}
		}
	}
}