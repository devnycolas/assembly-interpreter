public class MOV extends Operacao {
    @Override
    public void executar(Registrador registrador1, Registrador registrador2, int valor) {
        registrador1.setValor(valor);
    }
}