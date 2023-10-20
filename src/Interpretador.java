import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Interpretador {
    private int AX = 0; // Registrador AX
    private int BX = 0; // Registrador BX

    public void processarArquivo(String caminhoArquivo) {
        List<String> comandos = new ArrayList<>();

        try {
            List<String> linhas = Files.readAllLines(Paths.get(caminhoArquivo));
            comandos.addAll(linhas);
        } catch (IOException e) {
            e.printStackTrace();
        }

        processarComandos(comandos);
    }

    public void processarComandos(List<String> comandos) {
        for (String comando : comandos) {
            String[] partes = comando.split(" ");
            if (partes.length != 3) {
                System.err.println("Comando inválido: " + comando);
                continue;
            }

            String operacao = partes[0];
            String registrador = partes[1];
            String operando = partes[2];

            if (operacao.equals("MOV")) {
                if (registrador.equals("AX")) {
                    try {
                        int valor = Integer.parseInt(operando);
                        AX = valor;
                    } catch (NumberFormatException e) {
                        if (operando.equals("BX")) {
                            AX = BX;
                        } else {
                            System.err.println("Operando inválido: " + operando);
                        }
                    }
                    System.out.println("AX = " + AX);
                } else if (registrador.equals("BX")) {
                    try {
                        int valor = Integer.parseInt(operando);
                        BX = valor;
                    } catch (NumberFormatException e) {
                        if (operando.equals("AX")) {
                            BX = AX;
                        } else {
                            System.err.println("Operando inválido: " + operando);
                        }
                    }
                    System.out.println("BX = " + BX);
                } else {
                    System.err.println("Registrador inválido: " + registrador);
                }
            } else if (operacao.equals("ADD")) {
                if (registrador.equals("AX")) {
                    if (operando.equals("BX")) {
                        AX += BX;
                    } else {
                        try {
                            int valor = Integer.parseInt(operando);
                            AX += valor;
                        } catch (NumberFormatException e) {
                            System.err.println("Operando inválido: " + operando);
                        }
                    }
                    System.out.println("AX = " + AX);
                } else {
                    System.err.println("Registrador inválido: " + registrador);
                }
            } else if (operacao.equals("SUB")) {
                if (registrador.equals("AX")) {
                    if (operando.equals("BX")) {
                        AX -= BX;
                    } else {
                        try {
                            int valor = Integer.parseInt(operando);
                            AX -= valor;
                        } catch (NumberFormatException e) {
                            System.err.println("Operando inválido: " + operando);
                        }
                    }
                    System.out.println("AX = " + AX);
                } else if (registrador.equals("BX")) {
                    try {
                        int valor = Integer.parseInt(operando);
                        BX -= valor;
                    } catch (NumberFormatException e) {
                        System.err.println("Operando inválido: " + operando);
                    }
                    System.out.println("BX = " + BX);
                } else {
                    System.err.println("Registrador inválido: " + registrador);
                }
            } else {
                System.err.println("Operação inválida: " + operacao);
            }
        }
    }

    public static void main(String[] args) {
        Interpretador interpretador = new Interpretador();
        interpretador.processarArquivo("codes.txt");
    }
}
