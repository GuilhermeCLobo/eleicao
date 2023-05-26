public class Teste implements Runnable {
    private int parametro;

    public Teste(int parametro) {
        this.parametro = parametro;
    }

    @Override
    public void run() {
        // Usar o valor do parâmetro dentro da thread
        System.out.println("Parâmetro: " + parametro);
    }

    public static void main(String[] args) {
        // Criar uma instância da implementação Runnable com um parâmetro
        int meuParametro = 42;
        Teste minhaRunnable = new Teste(meuParametro);

        // Criar e iniciar a nova thread
        Thread minhaThread = new Thread(minhaRunnable);
        minhaThread.start();
    }
}