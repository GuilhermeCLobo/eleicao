import java.time.LocalTime;
import java.time.Duration;

public class Teste {
    public static void main(String[] args) {
        String hora1 = "19:49:07";
        String hora2 = "19:49:05";
        
        // Converte as strings para objetos LocalTime
        LocalTime time1 = LocalTime.parse(hora1);
        LocalTime time2 = LocalTime.parse(hora2);
        
        // Calcula a diferença de tempo entre as duas horas
        Duration diferenca = Duration.between(time1, time2);
        
        // Obtém a diferença em horas, minutos e segundos
        long segundos = diferenca.getSeconds() % 60;
        
        // Imprime a diferença de tempo
        System.out.printf("Diferença de tempo: %d segundos", segundos);
    }
}
