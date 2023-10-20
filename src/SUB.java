public class SUB extends Operacao {
    @Override
    public void executar(Registrador registrador1, Registrador registrador2, int valor) {
        registrador1.setValor(registrador1.getValor() - (registrador2 != null ? registrador2.getValor() : valor));
    }
}